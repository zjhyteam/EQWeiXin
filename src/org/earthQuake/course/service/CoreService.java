package org.earthQuake.course.service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.earthQuake.course.common.MessageUtil;
import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.common.bean.Knowledge;
import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.common.msg.resp.Article;
import org.earthQuake.course.common.msg.resp.RespNewsMessage;
import org.earthQuake.course.common.msg.resp.RespTextMessage;

/**
 * 核心服务类
 * 
 * @author 徐晓亮
 * @date 2014-01-06
 */
public class CoreService {
	
	private static CommonService commonService;
	private static MapsDetailService mapsDetailService;
	private static KnowledgeService knowledgeService;
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public MapsDetailService getMapsDetailService() {
		return mapsDetailService;
	}

	public void setMapsDetailService(MapsDetailService mapsDetailService) {
		this.mapsDetailService = mapsDetailService;
	}

	public KnowledgeService getKnowledgeService() {
		return knowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(HttpServletRequest request, HttpServletResponse response) {
		String httpUrl ="", httpImageUrl="", EQWebUrl="", EQWebName="", newBigEQ="",localFileUrl="";
		List<CodeMaintenance> clist = commonService.getCodeValueList();
		for(int i = 0; i < clist.size(); i++){
			CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
			if(codeMaintenance.getCode().equals(PubCode.httpUrl)){
				httpUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.httpImageUrl)){
				httpImageUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.EQWebUrl)){
				EQWebUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.EQWebName)){
				EQWebName = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.newBigEQ)){
				newBigEQ = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.localFileUrl)){
				localFileUrl = codeMaintenance.getValue();
			}
		}
		
		String respMessage = null;
		HttpSession session = request.getSession();
		try {
			// 默认返回的文本消息内容
			String respContent = "";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 回复文本消息
			RespTextMessage textMessage = new RespTextMessage();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			textMessage.setFuncFlag(0);

			List<Article> articleList = new ArrayList<Article>();
			DecimalFormat df = new DecimalFormat("#####0.0");
			
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 文本消息内容
				String content = requestMap.get("Content");
				
				// 判断用户发送的是否是单个QQ表情
//				if(EarthQuakeUtil.isQqFace(content)) {
//					respContent = content;
//				}
				// 创建图文消息
				RespNewsMessage newsMessage = new RespNewsMessage();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
				newsMessage.setFuncFlag(0);
				
				if(content.length() > 4){
//					respContent = "您输入的内容无法识别！";
//					respContent = "";
				}else if((new ToolUtil()).isNumeric(content)){
					//关键字  科普知识查询
					List<Knowledge> list = knowledgeService.getKnowledgeKWList();
					for(int i = 1; i <= list.size(); i++){
						if (Integer.toString(i).equals(content)){
							Knowledge knowledge = knowledgeService.getKnowledgeByNum(i, httpImageUrl, localFileUrl);
							Article article;
							article = new Article();
							article.setTitle(knowledge.getTitle());
							article.setDescription(knowledge.getSummary());
							article.setPicUrl(httpImageUrl + "title.jpg");
							article.setUrl(httpUrl + "coreServlet/knowledgeByNum.action?method=getKnowledgeByNum&num=" + Integer.toString(i));
							articleList.add(article);
							
							newsMessage.setArticleCount(articleList.size());
							newsMessage.setArticles(articleList);
							return respMessage = MessageUtil.newsMessageToXml(newsMessage);
						}
					}
				}else{
					List<Knowledge> list1 = knowledgeService.getKnowledgeByTitle(content);
					if(list1.size() == 0){
//						respContent = "";
					}else{
						Article article;
						article = new Article();
						article.setTitle("地震科普知识信息");
						article.setDescription("");
						article.setPicUrl(httpImageUrl + "title.jpg");
						article.setUrl(httpUrl + "coreServlet/welCome.action");
						articleList.add(article);
						
						for(int i = 0; i < list1.size(); i++){
							article = new Article();
							Knowledge knowledge = (Knowledge)list1.get(i);
							article.setTitle(knowledge.getTitle());
							article.setDescription("");
							article.setPicUrl(httpImageUrl + "test.jpg");
							article.setUrl(httpUrl + "coreServlet/knowledgeById.action?method=getKnowledgeById&id=" + knowledge.getId());
							articleList.add(article);
						}
						if(list1.size() == 0){
							article = new Article();
							article.setTitle("未能找到您所提供的匹配信息！更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
						}else{
							article = new Article();
							article.setTitle("更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
						}
						
						newsMessage.setArticleCount(articleList.size());
						newsMessage.setArticles(articleList);
						return respMessage = MessageUtil.newsMessageToXml(newsMessage);
					}
				}
			}
//			// 图片消息
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
//				respContent = "您发送的是图片消息！";
//			}
//			// 地理位置消息
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
//				respContent = "您发送的是地理位置消息！";
//			}
//			// 链接消息
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
//				respContent = "您发送的是链接消息！";
//			}
//			// 音频消息
//			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
//				respContent = "您发送的是音频消息！";
//			}
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				// 订阅
				if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					respContent = session.getAttribute("menu").toString();
				}
				// 取消订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应  
                    String eventKey = requestMap.get("EventKey");  
  
                 // 创建图文消息
    				RespNewsMessage newsMessage = new RespNewsMessage();
    				newsMessage.setToUserName(fromUserName);
    				newsMessage.setFromUserName(toUserName);
    				newsMessage.setCreateTime(new Date().getTime());
    				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
    				newsMessage.setFuncFlag(0);
    				
                    if (eventKey.equals("11")) {
                    	String rownum = commonService.getCodeValue(PubCode.zheJiang);
                    	List<TabMapsDetail> list = mapsDetailService.getMaps(3, 0,rownum);
    					Article article;
    					article = new Article();
    					article.setTitle("浙江地震信息");
    					article.setDescription("");
    					article.setPicUrl(httpImageUrl + "title.jpg");
    					article.setUrl(httpUrl + "coreServlet/welCome.action");
    					articleList.add(article);
    					
    					for(int i = 0; i < list.size(); i++){
    						article = new Article();
    						TabMapsDetail tabMapsDetail = (TabMapsDetail)list.get(i);
    						article.setTitle(tabMapsDetail.getTime() + " " + tabMapsDetail.getAddress() + "(" + 
    								df.format(tabMapsDetail.getLongitude()) + "," + df.format(tabMapsDetail.getLatitude()) + ")震级：" + 
    								df.format(tabMapsDetail.getMagnitude()) + "级 深度：" + df.format(tabMapsDetail.getDepth()) + "公里");
    						article.setDescription("");
    						article.setPicUrl(httpImageUrl + "test.jpg");
    						article.setUrl(httpUrl + "coreServlet/mapsDetail.action?method=showDetail&cata_id=" + tabMapsDetail.getMapid());
    						articleList.add(article);
    					}
    					
    					if(list.size() == 0){
    						article = new Article();
							article.setTitle("暂时没有地震信息！\n更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
    					}else{
    						article = new Article();
        					article.setTitle("更多地震信息请登录：" + EQWebName);
        					article.setDescription("");
        					// 将图片置为空
        					article.setPicUrl("");
        					article.setUrl(EQWebUrl);
        					articleList.add(article);
    					}
    					
    					newsMessage.setArticleCount(articleList.size());
    					newsMessage.setArticles(articleList);
    					return respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    } else if (eventKey.equals("12")) {
                    	String rownum = commonService.getCodeValue(PubCode.newEQ);
                    	List<TabMapsDetail> list = mapsDetailService.getMaps(1, 0, rownum);
    					Article article;
    					article = new Article();
    					article.setTitle("最新地震信息");
    					article.setDescription("");
    					article.setPicUrl(httpImageUrl + "title.jpg");
    					article.setUrl(httpUrl + "coreServlet/welCome.action");
    					articleList.add(article);
    					
    					for(int i = 0; i < list.size(); i++){
    						article = new Article();
    						TabMapsDetail tabMapsDetail = (TabMapsDetail)list.get(i);
    						article.setTitle(tabMapsDetail.getTime() + " " + tabMapsDetail.getAddress() + "(" + 
    								df.format(tabMapsDetail.getLongitude()) + "," + df.format(tabMapsDetail.getLatitude()) + ")震级：" + 
    								df.format(tabMapsDetail.getMagnitude()) + "级 深度：" + df.format(tabMapsDetail.getDepth()) + "公里");
    						article.setDescription("");
    						article.setPicUrl(httpImageUrl + "test.jpg");
    						article.setUrl(httpUrl + "coreServlet/mapsDetail.action?method=showDetail&cata_id=" + tabMapsDetail.getMapid());
    						articleList.add(article);
    					}
    					
    					if(list.size() == 0){
    						article = new Article();
							article.setTitle("暂时没有地震信息！\n更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
    					}else{
    						article = new Article();
        					article.setTitle("更多地震信息请登录：" + EQWebName);
        					article.setDescription("");
        					// 将图片置为空
        					article.setPicUrl("");
        					article.setUrl(EQWebUrl);
        					articleList.add(article);
    					}
    					
    					newsMessage.setArticleCount(articleList.size());
    					newsMessage.setArticles(articleList);
    					return respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    } else if (eventKey.equals("13")) {
                    	String rownum = commonService.getCodeValue(PubCode.world24);
                    	List<TabMapsDetail> list = mapsDetailService.getMaps(1, 24, rownum);
    					Article article;
    					
    					article = new Article();
    					article.setTitle("全球24小时内的地震信息");
    					article.setDescription("");
    					article.setPicUrl(httpImageUrl + "title.jpg");
    					article.setUrl(httpUrl + "coreServlet/welCome.action");
    					articleList.add(article);
    					
    					for(int i = 0; i < list.size(); i++){
    						article = new Article();
    						TabMapsDetail tabMapsDetail = (TabMapsDetail)list.get(i);
    						article.setTitle(tabMapsDetail.getTime() + " " + tabMapsDetail.getAddress() + "(" + 
    								df.format(tabMapsDetail.getLongitude()) + "," + df.format(tabMapsDetail.getLatitude()) + ")震级：" + 
    								df.format(tabMapsDetail.getMagnitude()) + "级 深度：" + df.format(tabMapsDetail.getDepth()) + "公里");
    						article.setDescription("");
    						article.setPicUrl(httpImageUrl + "test.jpg");
    						article.setUrl(httpUrl + "coreServlet/mapsDetail.action?method=showDetail&cata_id=" + tabMapsDetail.getMapid());
    						articleList.add(article);
//    						System.out.println(article.getUrl().toString());
//    						System.out.println(article.getPicUrl().toString());
    					}
    					
    					if(list.size() == 0){
    						article = new Article();
							article.setTitle("暂时没有地震信息！\n更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
    					}else{
    						article = new Article();
        					article.setTitle("更多地震信息请登录：" + EQWebName);
        					article.setDescription("");
        					// 将图片置为空
        					article.setPicUrl("");
        					article.setUrl(EQWebUrl);
        					articleList.add(article);
    					}
    					
//    					System.out.println(EQWebUrl);
    					
    					newsMessage.setArticleCount(articleList.size());
    					newsMessage.setArticles(articleList);
    					return respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    } else if (eventKey.equals("14")) {
                    	String rownum = commonService.getCodeValue(PubCode.world48);
                    	List<TabMapsDetail> list = mapsDetailService.getMaps(1, 48, rownum);
    					Article article;
    					article = new Article();
    					article.setTitle("全球48小时内的地震信息");
    					article.setDescription("");
    					article.setPicUrl(httpImageUrl + "title.jpg");
    					article.setUrl(httpUrl + "coreServlet/welCome.action");
    					articleList.add(article);
    					
    					for(int i = 0; i < list.size(); i++){
    						article = new Article();
    						TabMapsDetail tabMapsDetail = (TabMapsDetail)list.get(i);
    						article.setTitle(tabMapsDetail.getTime() + " " + tabMapsDetail.getAddress() + "(" + 
    								df.format(tabMapsDetail.getLongitude()) + "," + df.format(tabMapsDetail.getLatitude()) + ")震级：" + 
    								df.format(tabMapsDetail.getMagnitude()) + "级 深度：" + df.format(tabMapsDetail.getDepth()) + "公里");
    						article.setDescription("");
    						article.setPicUrl(httpImageUrl + "test.jpg");
    						article.setUrl(httpUrl + "coreServlet/mapsDetail.action?method=showDetail&cata_id=" + tabMapsDetail.getMapid());
    						articleList.add(article);
    					}
    					
    					if(list.size() == 0){
    						article = new Article();
							article.setTitle("暂时没有地震信息！\n更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
    					}else{
    						article = new Article();
        					article.setTitle("更多地震信息请登录：" + EQWebName);
        					article.setDescription("");
        					// 将图片置为空
        					article.setPicUrl("");
        					article.setUrl(EQWebUrl);
        					articleList.add(article);
    					}
    					
    					newsMessage.setArticleCount(articleList.size());
    					newsMessage.setArticles(articleList);
    					return respMessage = MessageUtil.newsMessageToXml(newsMessage);
    				//关键字查询
                    } else if (eventKey.equals("21")) {
                    	List<Knowledge> list = knowledgeService.getKnowledgeKWList();
                    	StringBuffer sbf = new StringBuffer();
                    	sbf.append("关键字查询:").append("\n");
                    	for(int i = 0; i < list.size(); i++){
                    		Knowledge knowledge = list.get(i);
                    		sbf.append(knowledge.getNum() + "、" + knowledge.getKeywords()).append("\n");
                    	}
    					respContent = sbf.toString();
    					//自定义查询
                    } else if (eventKey.equals("22")) {
                    	StringBuffer sbf = new StringBuffer();
                    	sbf.append("请输入关键词，关键词不超过4个汉字！");
    					respContent = sbf.toString();
    				//地震小常识
                    } else if (eventKey.equals("23")) {
        				Knowledge knowledge = knowledgeService.getKnowledge();
        				Article article;
        				article = new Article();
        				article.setTitle(knowledge.getTitle());
        				article.setDescription(knowledge.getSummary());
        				article.setPicUrl(httpImageUrl + "title.jpg");
        				article.setUrl(httpUrl + "coreServlet/knowledgeById.action?method=getKnowledgeById&id=" + knowledge.getId());
        				articleList.add(article);
        				
        				newsMessage.setArticleCount(articleList.size());
        				newsMessage.setArticles(articleList);
        				return respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    //最新大震
                    } else if (eventKey.equals("31")) {
                    	String m = commonService.getCodeValue(PubCode.m);
                    	List<TabMapsDetail> list = mapsDetailService.getMaps(Integer.parseInt(m), newBigEQ);
    					Article article;
    					article = new Article();
    					article.setTitle("全球最新大地震信息");
    					article.setDescription("");
    					article.setPicUrl(httpImageUrl + "title.jpg");
    					article.setUrl(httpUrl + "coreServlet/welCome.action");
    					articleList.add(article);
    					
    					for(int i = 0; i < list.size(); i++){
    						article = new Article();
    						TabMapsDetail tabMapsDetail = (TabMapsDetail)list.get(i);
    						article.setTitle(tabMapsDetail.getTime() + " " + tabMapsDetail.getAddress() + "(" + 
    								df.format(tabMapsDetail.getLongitude()) + "," + df.format(tabMapsDetail.getLatitude()) + ")震级：" + 
    								df.format(tabMapsDetail.getMagnitude()) + "级 深度：" + df.format(tabMapsDetail.getDepth()) + "公里");
    						article.setDescription("");
    						article.setPicUrl(httpImageUrl + "test.jpg");
    						article.setUrl(httpUrl + "coreServlet/mapsDetail.action?method=showDetail&cata_id=" + tabMapsDetail.getMapid());
    						articleList.add(article);
    					}
    					
    					if(list.size() == 0){
    						article = new Article();
							article.setTitle("暂时没有地震信息！\n更多地震信息请登录：" + EQWebName);
							article.setDescription("");
							// 将图片置为空
							article.setPicUrl("");
							article.setUrl(EQWebUrl);
							articleList.add(article);
    					}else{
    						article = new Article();
        					article.setTitle("更多地震信息请登录：" + EQWebName);
        					article.setDescription("");
        					// 将图片置为空
        					article.setPicUrl("");
        					article.setUrl(EQWebUrl);
        					articleList.add(article);
    					}
    					
    					newsMessage.setArticleCount(articleList.size());
    					newsMessage.setArticles(articleList);
    					return respMessage = MessageUtil.newsMessageToXml(newsMessage);
                    } 
				}
			}

			textMessage.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}
}
