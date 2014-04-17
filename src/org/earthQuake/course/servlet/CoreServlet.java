package org.earthQuake.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.earthQuake.course.common.MessageUtil;
import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.SignUtil;
import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.common.bean.TabMenuDetail;
import org.earthQuake.course.common.menu.AccessToken;
import org.earthQuake.course.common.menu.WeixinUtil;
import org.earthQuake.course.common.msg.resp.Article;
import org.earthQuake.course.jet.common.ParameterUtil;
import org.earthQuake.course.service.CommonService;
import org.earthQuake.course.service.CoreService;
import org.earthQuake.course.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 核心请求处理类
 * 
 * @author 徐晓亮
 * @date 2014-1-5
 */
public class CoreServlet extends HttpServlet {
	private static final long serialVersionUID = 4440739483644821986L;
	private static Logger log = LoggerFactory.getLogger(CoreServlet.class);
	
	private static CommonService commonService;
	private static MenuService menuService;
	private static ToolUtil toolutil;
	
	public ToolUtil getToolutil() {
		return toolutil;
	}

	public void setToolutil(ToolUtil toolutil) {
		this.toolutil = toolutil;
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public MenuService getMenuService() {
		return menuService;
	}

	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}

	public static void setCommonService(CommonService commonService) {
		CoreServlet.commonService = commonService;
	}

	/**
	 * 确认请求来自微信服务器
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 微信加密签名
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");
		
		PrintWriter out = response.getWriter();
		// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
		if (SignUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}
		out.close();
		out = null;
	}

	/**
	 * 处理微信服务器发来的消息
	 */
	@SuppressWarnings("unused")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 消息的接收、处理、响应
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8"); 
        //得到菜单
        StringBuffer sbf = new StringBuffer();
        
        
        HttpSession session = request.getSession();
        if(null == session.getAttribute("menu")){
            List<TabMenuDetail> list = menuService.getMenus();
            
            for(int i = 0; i < list.size(); i++){
            	TabMenuDetail menu = (TabMenuDetail)list.get(i);
            	if(null != menu.getMenuexplain()){
            		sbf.append(menu.getMenuexplain()).append("\n");
            	}else{
            		sbf.append(menu.getMenuContent()).append("\n");
            	}
            }
            session.setAttribute("menu", sbf.toString());
        }
        
        String respMessage = "";
        int result = 0;
        
        
        
		Map map= ParameterUtil.toMap(request);
		if(null != map.get("content")){
			//得到用户openID
	        List<String> openIDList = toolutil.getUserOpenIDList();
			
	        String sendurl ="", httpUrl="", appid="", secret="";
			List<CodeMaintenance> clist = commonService.getCodeValueList();
			for(int i = 0; i < clist.size(); i++){
				CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
				if(codeMaintenance.getCode().equals(PubCode.sendurl)){
					sendurl = codeMaintenance.getValue();
				}else if(codeMaintenance.getCode().equals(PubCode.httpUrl)){
					httpUrl = codeMaintenance.getValue();
				}else if(codeMaintenance.getCode().equals(PubCode.appid)){
					appid = codeMaintenance.getValue();
				}else if(codeMaintenance.getCode().equals(PubCode.secret)){
					secret = codeMaintenance.getValue();
				}
			}
			// 文本消息内容
			String respContent = map.get("content").toString();
			//图片名称
			Object resImage = map.get("imageName");
			String id = map.get("id").toString();
			String url = "";
			WeixinUtil weixinUtil =new WeixinUtil();
			// 调用接口获取access_token
			AccessToken at = WeixinUtil.getAccessToken(appid, secret);
			
			// 文本消息
			if (null == resImage || "".equals(resImage)) {
				for(int j=0; j<openIDList.size(); j++){
						String userName = openIDList.get(j).toString();
						Map<String,Object> res =new HashMap<String, Object>();
						Map<String,Object> type =new HashMap<String, Object>();
						type.put("content", respContent);
						// 回复文本消息
						res.put("touser", userName);
						res.put("msgtype", MessageUtil.REQ_MESSAGE_TYPE_TEXT);
						res.put("text", type);
						respMessage = JSONObject.fromObject(res).toString();
						
						if (null != at) {
							url = sendurl.replace("ACCESS_TOKEN", at.getToken());
							// 调用接口发送客服信息
							JSONObject jsonObject = weixinUtil.httpRequest(url, "POST", respMessage);
							if (null != jsonObject) {
								if (0 != jsonObject.getInt("errcode")) {
									result = jsonObject.getInt("errcode");
									log.error("发送客服信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
								}
							}
							// 判断菜单创建结果
							if (0 == result){
								log.info("发送客服信息成功！");
								System.out.println("发送客服信息成功！");
							}
							else {
								log.info("发送客服信息失败，错误码：" + result);
								System.out.println("发送客服信息失败，错误码：" + result);
							}
						}
				}
			}else{
				for(int j=0; j<openIDList.size(); j++){
					String fromUserName = openIDList.get(j).toString();
					// 创建图文消息
					List<Article> articleList = new ArrayList<Article>();
					Article article;
					article = new Article();
					article.setTitle("推送信息");
					article.setDescription(respContent);
					article.setPicurl(httpUrl + resImage.toString());
					article.setUrl(httpUrl + "coreServlet/groupSend.action?method=getGroupSendById&id=" + id);
					articleList.add(article);
					
//					System.out.println(article.getPicUrl());
//					System.out.println(article.getUrl());
					Map<String,Object> res =new HashMap<String, Object>();
					Map<String,Object> type =new HashMap<String, Object>();
					
					type.put("articles", articleList);
					res.put("msgtype", MessageUtil.RESP_MESSAGE_TYPE_NEWS);
					res.put("touser", fromUserName);
					res.put("news", type);
					respMessage = JSONObject.fromObject(res).toString();
//					System.out.println(respMessage);
					
					if (null != at) {
						url = sendurl.replace("ACCESS_TOKEN", at.getToken());
						// 调用接口发送客服信息
						JSONObject jsonObject = weixinUtil.httpRequest(url, "POST", respMessage);
						if (null != jsonObject) {
							if (0 != jsonObject.getInt("errcode")) {
								result = jsonObject.getInt("errcode");
								log.error("发送客服信息失败 errcode:{} errmsg:{}", jsonObject.getInt("errcode"), jsonObject.getString("errmsg"));
							}
						}
						// 判断菜单创建结果
						if (0 == result){
							log.info("发送客服信息成功！");
							System.out.println("发送客服信息成功！");
						}
						else {
							log.info("发送客服信息失败，错误码：" + result);
							System.out.println("发送客服信息失败，错误码：" + result);
						}
					}
				}
			}
			
		}else{
	        // 调用核心业务类接收消息、处理消息  
	        respMessage = CoreService.processRequest(request, response);  
	        // 响应消息  
	        PrintWriter out = response.getWriter();  
	        out.print(respMessage);  
	        out.close();
		}
	}
}
