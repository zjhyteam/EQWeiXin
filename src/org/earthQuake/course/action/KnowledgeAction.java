package org.earthQuake.course.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.common.bean.Knowledge;
import org.earthQuake.course.jet.common.PmRtnType;
import org.earthQuake.course.service.CommonService;
import org.earthQuake.course.service.KnowledgeService;

/**
 * 地震科普知识
 * @author 徐晓亮
 *
 */
@SuppressWarnings("serial")
public class KnowledgeAction extends BaseAction{
	
	private KnowledgeService knowledgeService;
	private CommonService commonService;
	private int num;
	private String id;
	private Knowledge knowledge;
	private Map<String, Object> dataMap;
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public KnowledgeService getKnowledgeService() {
		return knowledgeService;
	}

	public void setKnowledgeService(KnowledgeService knowledgeService) {
		this.knowledgeService = knowledgeService;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Knowledge getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(Knowledge knowledge) {
		this.knowledge = knowledge;
	}

	public KnowledgeAction() {
		super();
		dataMap = new HashMap<String, Object>();
	}
	public String getKnowledgeByNum(){
		String localFileUrl ="", httpImageUrl="";
		List<CodeMaintenance> clist = commonService.getCodeValueList();
		for(int i = 0; i < clist.size(); i++){
			CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
			if(codeMaintenance.getCode().equals(PubCode.localFileUrl)){
				localFileUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.httpImageUrl)){
				httpImageUrl = codeMaintenance.getValue();
			}
		}
		knowledge = knowledgeService.getKnowledgeByNum(num, httpImageUrl, localFileUrl);
		return SUCCESS;
	}

	public String getKnowledgeById(){
		String localFileUrl ="", httpImageUrl="";
		List<CodeMaintenance> clist = commonService.getCodeValueList();
		for(int i = 0; i < clist.size(); i++){
			CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
			if(codeMaintenance.getCode().equals(PubCode.localFileUrl)){
				localFileUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.httpImageUrl)){
				httpImageUrl = codeMaintenance.getValue();
			}
		}
		knowledge = knowledgeService.getKnowledgeById(Integer.parseInt(id), httpImageUrl, localFileUrl);
		return SUCCESS;
	}
	
	public String saveKnowledge(){
		String localFileUrl ="", knowledgeImage="";
		List<CodeMaintenance> clist = commonService.getCodeValueList();
		for(int i = 0; i < clist.size(); i++){
			CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
			if(codeMaintenance.getCode().equals(PubCode.localFileUrl)){
				localFileUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.knowledgeImage)){
				knowledgeImage = codeMaintenance.getValue();
			}
		}
		
		Knowledge knowledge = new Knowledge();
		String addflag = request.getParameter("addflag");
		String imageName = request.getParameter("imageName");
		String textname = request.getParameter("textname");
		
		knowledge.setContent(request.getParameter("content"));
		if(null != imageName && !"".equals(imageName)){
			knowledge.setImageName(imageName);
		}
		
		knowledge.setKeywords(request.getParameter("keywords"));
		if(null == request.getParameter("keywordsflag")){
			knowledge.setKeywordsflag(0);
		}else{
			knowledge.setKeywordsflag(Integer.parseInt(request.getParameter("keywordsflag").toString()));
		}
		
		knowledge.setNum(Integer.parseInt(request.getParameter("num").toString()));
		knowledge.setSummary(request.getParameter("summary"));
		knowledge.setTitle(request.getParameter("title"));
		
		//新增 
		if(null != addflag && addflag.toString().equals("1")){
			knowledgeService.save("", addflag, imageName, textname, knowledgeImage, localFileUrl, knowledge);
		//详细修改、部分修改
		}else{
			String updateInfo = request.getParameter("updateInfo");
			knowledge.setId(Integer.parseInt(request.getParameter("id").toString()));
			knowledgeService.save(updateInfo, addflag, imageName, textname, knowledgeImage, localFileUrl, knowledge);
		}
		dataMap.put("success", true);
        return SUCCESS;
	}
	
	public String getContentAndImgPath(){
		Object content=request.getParameter("content");
		Object imageName=request.getParameter("imageName");
		if(null == content || null == imageName){
			return PmRtnType.JSONFAILD;
		}
		
		String localFileUrl ="", httpUrl="";
		List<CodeMaintenance> clist = commonService.getCodeValueList();
		for(int i = 0; i < clist.size(); i++){
			CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
			if(codeMaintenance.getCode().equals(PubCode.localFileUrl)){
				localFileUrl = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.httpUrl)){
				httpUrl = codeMaintenance.getValue();
			}
		}
		
		String scontent = content.toString();
		String simageName = imageName.toString();
		
		ToolUtil toolUtil = new ToolUtil();
		String path = localFileUrl + scontent;
		scontent = toolUtil.readfile(path);
		simageName = httpUrl + imageName;
		
		Knowledge knowledge = new Knowledge();
		knowledge.setContent(scontent);
		knowledge.setImageName(simageName);
		
		dataMap.put("knowledge",knowledge);
		dataMap.put("success", true);
        return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
