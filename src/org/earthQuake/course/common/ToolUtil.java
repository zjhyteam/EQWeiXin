package org.earthQuake.course.common;

import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.earthQuake.course.common.bean.CutImg;
import org.earthQuake.course.dao.BaseDao;
import org.earthQuake.course.service.CommonService;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 一般工具类
 * @author xxl32
 *
 */
public class ToolUtil extends BaseDao{
	
	static CutImg imgT = new CutImg();
	private static CommonService commonService;

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	/**
	 * 计算采用utf-8编码方式时字符串所占字节数
	 * 
	 * @param content
	 * @return
	 */
	public static int getByteSize(String content) {
		int size = 0;
		if (null != content) {
			try {
				// 汉字采用utf-8编码时占3个字节
				size = content.getBytes("utf-8").length;
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return size;
	}
	
	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|" +
				"/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|" +
				"/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|" +
				"/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|" +
				"/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||" +
				"/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|" +
				"/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|" +
				"/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|" +
				"/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|" +
				"/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|" +
				"/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}
	
	public void cutImgTool(String url, String path, String fileStyle) throws MalformedURLException, IOException, URISyntaxException, AWTException{
		//1、最直接的方式——使用Robot
		//此方法仅适用于JdK1.6及以上版本
		Desktop.getDesktop().browse(
				new URL(url).toURI());
		Robot robot = new Robot();
//		robot.delay(10000);
		Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
		int width = (int) d.getWidth();
		int height = (int) d.getHeight();
		//最大化浏览器
		robot.keyRelease(KeyEvent.VK_F11);
		robot.delay(6000);
		Image image = robot.createScreenCapture(new Rectangle(80+(width-height)/2, 130, (width-height),
				height));
		width = height;
		BufferedImage bi = new BufferedImage(width, height-170,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		g.drawImage(image, 0, 0, width, height, null);
		//保存图片
		//final String fileName = System.currentTimeMillis() + ".jpg";
		try {
//			ImageIO.write(bi, fileStyle, new File("E:/image/" + fileName));
			ImageIO.write(bi, fileStyle, new File(path));
			
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");  
			Runtime.getRuntime().exec("taskkill /F /IM iexplorer.exe");  
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");  
			Runtime.getRuntime().exec("taskkill /F /IM safari.exe");  
			Runtime.getRuntime().exec("taskkill /F /IM opera.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	/**
	 * 根据开始坐标，宽度和高度切图。
	 * 把图片读入内存缓冲区，然后再
	 * 根据具体坐标切取子图最后把子
	 * 图俺规定的格式存入指定文件
	 */
	public void cutImg(CutImg cutImg, String fileStyle){
		try {
			BufferedImage bufImg = ImageIO.read(new File(cutImg.getSrcPath()));
			bufImg = bufImg.getSubimage(cutImg.getStartX(), cutImg.getStartY(), cutImg.getWidth(), cutImg.getHeight());
			ImageIO.write(bufImg, fileStyle, new File(cutImg.getTarPath()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getPath(){
		String path = (getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).split("WEB-INF")[0].toString();
		path = path.substring(1, path.length());
		return path;
	}
	
	/**
	 * 得到微信url的项目路径
	 * http://qq370273662.oicp.net/EQWeiXin/
	 * @return
	 */
	public String getHttpUrl(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("httpUrl");
	}
	
	/**
	 * 得到微信图片url的项目路径
	 * http://qq370273662.oicp.net/EQWeiXin/images/img/
	 * @return
	 */
	public String getHttpImageUrl(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("httpImageUrl");
	}
	
	/**
	 * 得到图片存储的路径
	 * D:/Workspaces/eclipse/EQWeiXin/WebContent/images/img/
	 * @return
	 */
	public String getLocalImageUrl(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("localImageUrl");
	}
	
	/**
	 * 得到地震科普知识文件存储的路径
	 * D:/Workspaces/eclipse/EQWeiXin/WebContent/data/
	 * @return
	 */
	public String getLocalFileUrl(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("localFileUrl");
	}
	
	/**
	 * 得到群发图片存储的路径
	 * images/groupsend/
	 * @return
	 */
	public String getGroupsendImage(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("groupsendImage");
	}
	
	/**
	 * 得到科普知识图片存储的路径
	 * images/knowledge/
	 * @return
	 */
	public String getKnowledgeImage(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("knowledgeImage");
	}
	
	/**
	 * 得到EQWeb网站（数字地震公众服务网站）的路径
	 * http://61.164.34.9/EQWeb/czCatalog
	 * @return
	 */
	public String getEQWebUrl(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("EQWebUrl");
	}
	
	/**
	 * 得到EQWeb网站（数字地震公众服务网站）的名称
	 * 数字地震公众服务网站
	 * @return
	 */
	public String getEQWebName(){
		Map<String, String> map = new HashMap<String, String>();
		map = this.parseXml();
		return map.get("EQWebName");
	}
	
	/**
	 * 查找节点，并返回第一个符合条件节点
	 * @param express
	 * @param source
	 * @return
	 */
	public static Node selectSingleNode(String express, Object source) {
        Node result=null;
        XPathFactory xpathFactory=XPathFactory.newInstance();
        XPath xpath=xpathFactory.newXPath();
        try {
            result=(Node) xpath.evaluate(express, source, XPathConstants.NODE);
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return result;
	}
	
	/**
	 * 修改NodeValue
	 * @return
	 */
	public void updateNodeValue(String name, String value){
		
	}
	
//	public void updateNodeValue(String name, String value){
//		XmlDocument doc1 = new XmlDocument();
//        doc1.Load(Server.MapPath("~/etao/IncrementIndex.xml"));
//        //修改商品
//        XmlNodeList nodes1 = doc1.GetElementsByTagName("outer_id");
//        bool ismodif = false;
//        foreach (XmlNode xn in nodes1)//遍历任何子节点 
//        {
//            XmlElement xe = (XmlElement)xn;//将子节点类型转换为XmlElement类型 
//            if (xe.InnerText.Equals(pid))
//            {
//                xe.SetAttribute("action", "upload");//则修改该属性为“delete”
//                XmlNodeList modified = doc1.GetElementsByTagName("modified");
//                modified.Item(0).InnerText = DateTime.Now.ToString();
//                doc1.Save(Server.MapPath("~/etao/IncrementIndex.xml"));
//			}
//		}
//	}
	
	public void deleteNode(String node){
		Element theNode=(Element) selectSingleNode("/parameters[@id='" + node + "']", node);
		theNode.getParentNode().removeChild(theNode);
		System.out.println("---  删除节点成功 ----");
	}
	
	/**
	 * 添加节点
	 * @return
	 */
	public void addNode(String name, String value, String node){
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        Element theBook=null, theElem=null, root=null;
        try {
            factory.setIgnoringElementContentWhitespace(true);
            
            DocumentBuilder db=factory.newDocumentBuilder();
//            Document xmldoc=db.parse(new File(this.getHttpUrl() + "/WEB-INF/config.xml"));
            Document xmldoc=db.parse(new File("D:/software/apache-tomcat-7.0.47/webapps/EQWeiXin/" + "/WEB-INF/config.xml"));
            root=xmldoc.getDocumentElement();
            
            //---  新建一本书开始 ----
            theBook=xmldoc.createElement("parameters");
            theBook.setAttribute("id", node);
            theElem=xmldoc.createElement("name");
            theElem.setTextContent(name);
            theBook.appendChild(theElem);
            
            theElem=xmldoc.createElement("value");
            theElem.setTextContent(value);
            theBook.appendChild(theElem);
            
            theElem=xmldoc.createElement("node");
            theElem.setTextContent(node);
            theBook.appendChild(theElem);
            root.appendChild(theBook);
            root.appendChild(theBook);
            System.out.println("---  新增xml开始 ----");
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * 解析xml
	 * @return
	 */
	public Map<String, String> parseXml(){
		Map<String, String> map = new HashMap<String, String>();
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = factory.newDocumentBuilder();
//	        Document doc = db.parse(new File(this.getPath() + "WEB-INF/config.xml"));
	        Document doc=db.parse(new File("D:/software/apache-tomcat-7.0.47/webapps/EQWeiXin/" + "/WEB-INF/config.xml"));
	        Element elmtInfo = doc.getDocumentElement();
	        NodeList nodes = elmtInfo.getChildNodes();
	        String sName = "", sValue = "";
        	for (int i = 0; i < nodes.getLength(); i++)
	        {
	            Node result = nodes.item(i);
	            if (result.getNodeType() == Node.ELEMENT_NODE && result.getNodeName().equals("parameters"))
	            {
	                NodeList ns = result.getChildNodes();
	
	                for (int j = 0; j < ns.getLength(); j++)
	                {
	                    Node record = ns.item(j);
	
	                    if (record.getNodeType() == Node.ELEMENT_NODE && record.getNodeName().equals("name"))
	                    {
	                    	sName = record.getTextContent();
	                    }
	                    if(record.getNodeType() == Node.ELEMENT_NODE && record.getNodeName().equals("value")){
	                    	sValue = record.getTextContent();
	                    }
	                }
	                map.put(sName, sValue);
	            }
	        }
	    }
	    catch (ParserConfigurationException e)
	    {
	        e.printStackTrace();
	    }
	    catch (SAXException e)
	    {
	        e.printStackTrace();
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
		return map;
	}
	
//	/**
//	 * 与接口配置信息中的Token要一致
//	 * EQWeiXin
//	 * @return
//	 */
//	public static String getToken(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("token");
//	}
//	
//	/**
//	 * 微信公众号升级接口服务后得到的OpenId
//	 * gh_eb8d9f857fc1
//	 * @return
//	 */
//	public static String getMasterOpenID(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("masterOpenId");
//	}
//	
//	/**
//	 * 微信公众号升级接口服务后得到的appid
//	 * wxfcc54d74d59aa522
//	 * @return
//	 */
//	public static String getMasterAppid(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("appid");
//	}
//	
//	/**
//	 * 微信公众号升级接口服务后得到的secret
//	 * 45bcd54c0dd0b54485c3180dfb612c6f
//	 * @return
//	 */
//	public static String getMasterSecret(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("secret");
//	}
//	
//	/**
//	 * 发送服务接口
//	 * https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN
//	 * @return
//	 */
//	public static String getSendurl(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("sendurl");
//	}
//	
//	/**
//	 * 获取access_token接口
//	 * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
//	 * @return
//	 */
//	public static String getAccessTokenUrl(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("accessTokenUrl");
//	}
//	
//	/**
//	 * 获取关注者列表接口
//	 * https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
//	 * @return
//	 */
//	public static String getOpenIdUrl(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("openIdUrl");
//	}
//	
//	/**
//	 * 获取关注者基本信息接口
//	 * https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
//	 * @return
//	 */
//	public static String getOpenIdInfoUrl(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("openIdInfoUrl");
//	}
//	
//	/**
//	 * 得到全球24小时内显示的图文信息数
//	 * 5
//	 * @return
//	 */
//	public String getWorld24(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("world24");
//	}
//	
//	/**
//	 * 得到全球48小时内显示的图文信息数
//	 * 5
//	 * @return
//	 */
//	public String getWorld48(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("world48");
//	}
//	
//	/**
//	 * 得到浙江显示的图文信息数
//	 * 5
//	 * @return
//	 */
//	public String getZheJiang(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("zheJiang");
//	}
//	 
//	/**
//	 * 得到最新地震显示的图文信息数
//	 * 5
//	 * @return
//	 */
//	public String getNewEQ(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("newEQ");
//	}
//	
//	/**
//	 * 得到最新大震显示的图文信息数
//	 * 5
//	 * @return
//	 */
//	public String getNewBigEQ(){
//		Map<String, String> map = new HashMap<String, String>();
//		map = toolUtil.parseXml();
//		return map.get("newBigEQ");
//	}
	
	/**
	 * 得到jsonString
	 * @return
	 */
	public static String getJsonString(String url1){
		StringBuffer document = new StringBuffer();
		try{
			URL url = new URL(url1);//远程url
			URLConnection conn = url.openConnection();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String line = null;
		    while ((line = reader.readLine()) != null)
		       document.append(line + " ");
		    reader.close();
		}catch(MalformedURLException e) {
			e.printStackTrace(); 
		}catch(IOException e){
		    e.printStackTrace(); 
		}
		String jsonString = document.toString();//返回值
		return jsonString;
	}
	
	/**
	 * 得到公众账号的access_token
	 * @return
	 */
	public static String getAccess_token(){
		JSONObject jsonObject = null;
		String appid = commonService.getCodeValue(PubCode.appid);
		String secret = commonService.getCodeValue(PubCode.secret);
		String accessTokenUrl = commonService.getCodeValue(PubCode.accessTokenUrl);
		
		String url = accessTokenUrl.replace("APPID", appid).replace("APPSECRET", secret);
		String jsonString = getJsonString(url);
		
//		System.out.println(url);
		
		String access_token = "";
		try {
			jsonObject = new JSONObject(jsonString);
			access_token = jsonObject.get("access_token").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return access_token;
	}
	
	/**
	 * 得到公众账号的access_token的使用期限
	 * @return
	 */
	public static String getExpires_in(){
		JSONObject jsonObject = null;
		String appid = commonService.getCodeValue(PubCode.appid);
		String secret = commonService.getCodeValue(PubCode.secret);
		String accessTokenUrl = commonService.getCodeValue(PubCode.accessTokenUrl);
		
		String url = accessTokenUrl.replace("APPID", appid).replace("APPSECRET", secret);
		String jsonString = getJsonString(url);
		//		System.out.println("2"+jsonObject);
		String expires_in = "";
		try {
			jsonObject = new JSONObject(jsonString);
			expires_in = jsonObject.get("expires_in").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return expires_in;
	}
	
	/**
	 * 得到关注者OpenId的json包
	 * 如：{"total":9,"count":9,"data":{"openid":["occRAuMM0Pm3S05R81wlB7zWuCTY"]},"next_openid":"occRAuH2Q6a9X9ysdobc-j8r6REg"}
	 * @param args
	 */
	public static String getJsonOfUserOpenId() {
		String access_token = getAccess_token();
		String openIdUrl = commonService.getCodeValue(PubCode.openIdUrl);
//		System.out.println(access_token);
		String url = openIdUrl.replace("ACCESS_TOKEN", access_token).replace("NEXT_OPENID", "");
		String jsonString = getJsonString(url);
//		System.out.println(jsonString);
		return jsonString;
	}
	
	/**
	 * 得到关注者openID列表
	 * @return list
	 */
	public List<String> getUserOpenIDList(){
		String userListOpenId = getJsonOfUserOpenId();
		String openid = "";
		org.json.JSONArray jsonArray;
		JSONObject jsonObject;
		List<String> list = new LinkedList<String>();
		try {
			jsonObject = new JSONObject(userListOpenId);
			jsonObject = new JSONObject(jsonObject.getJSONObject("data").toString());
			jsonArray = jsonObject.getJSONArray("openid");
			for(int i = 0;i < jsonArray.length(); i++){
				openid = jsonArray.getString(i);
				list.add(openid);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 得到关注者用户名
	 * @return
	 */
	public static List<String> getUserInfo(){
		String userListOpenId = getJsonOfUserOpenId();
		String access_token = getAccess_token();
		String openid = "";
		String nickname = "";
		org.json.JSONArray jsonArray;
		JSONObject jsonObject;
		List<String> list = new LinkedList<String>();
		try {
			jsonObject = new JSONObject(userListOpenId);
			jsonObject = new JSONObject(jsonObject.getJSONObject("data").toString());
			jsonArray = jsonObject.getJSONArray("openid");
			String openIdInfoUrl = commonService.getCodeValue(PubCode.openIdInfoUrl);
			
			for(int i = 0;i < jsonArray.length(); i++){
				openid = jsonArray.getString(i);
				
				String url = openIdInfoUrl.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
				String jsonString = getJsonString(url);
				try {
					jsonObject = new JSONObject(jsonString);
					nickname = jsonObject.getString("nickname");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				list.add(nickname);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 得到关注者总数
	 * @return
	 */
	public static String getTotal(){
		String userListOpenId = getJsonOfUserOpenId();
		JSONObject jsonObject;
		String total = "";
		try {
			jsonObject = new JSONObject(userListOpenId);
			total = jsonObject.getString("total").toString();
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * 得到拉取的OPENID个数，最大值为10000
	 * @return
	 */
	public static String getCount(){
		String userListOpenId = getJsonOfUserOpenId();
		JSONObject jsonObject;
		String total = "";
		try {
			jsonObject = new JSONObject(userListOpenId);
			total = jsonObject.getString("count").toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	/**
	 * 从文本文件中读取内容
	 * @param path
	 * @return
	 */
	public String readfile(String path)
	{
		String filepath, read, readStr = "";
		BufferedReader bufread;
		try {
			filepath = path; // 得到文本文件的路径 
			File file = new File(filepath);
//			FileReader fileread = new FileReader(file);
			bufread = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
			while ((read = bufread.readLine()) != null ) {
				read = read + " \r\n " ;
				readStr = readStr + read;
			}
		} catch (Exception d) {
			System.out.println(d.getMessage());
		}
		return readStr; // 返回从文本文件中读取内容
	}
	
	/**
	 * 向文本文件中写入内容
	 * @param path 得到文本文件的路径
	 * @param content 需要写入的内容
	 * @param append 通过这个对象来判断是否向文本文件中追加内容
	 */
	public void writefile(String path,String content,boolean append)     
	{
		File writefile;
		BufferedWriter bufwriter;
		String filepath, filecontent, read, readStr = "";
	     try
	     {
		      boolean addStr=append;
		      filepath=path;
		      filecontent=content;
		      writefile=new File(filepath);
		      if(writefile.exists()==false)    //如果文本文件不存在则创建它 
		      {
		          writefile.createNewFile();    
		          writefile=new File(filepath);  //重新实例化
		      }
		      FileWriter filewriter=new FileWriter(writefile,addStr);
		      bufwriter=new BufferedWriter(filewriter);
		      filewriter.write(filecontent);
		      filewriter.flush();
	    }catch(Exception d){
	    	 System.out.println(d.getMessage());
	    }
	}
	
	/**
	 * 判断是否是数字
	 * @param str
	 * @return
	 */
	public boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches())
		{
			return false;
		}
		return true;
	}
	
	/**
	 * 得到数字形式的当前时间的文本名称
	 * @return
	 */
	public String getDateString(){
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		SimpleDateFormat dateformat2=new SimpleDateFormat("yyyyMMddHHmmss");
		final String fileName = dateformat2.format(date) + ".text";
		return fileName;
	}
}
