package org.earthQuake.course.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.bean.GroupSend;
import org.earthQuake.course.jet.common.PmRtnType;
import org.earthQuake.course.service.CommonService;
import org.earthQuake.course.service.GroupSendService;

/**
 * 群发信息控制类
 * @author 徐晓亮
 *
 */
public class GroupSendAction extends BaseAction{
	
	private String id;
	private GroupSendService groupSendService;
	private CommonService commonService;
	private GroupSend groupSend;
	private Map<String, Object> dataMap;
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public GroupSend getGroupSend() {
		return groupSend;
	}
	public void setGroupSend(GroupSend groupSend) {
		this.groupSend = groupSend;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setGroupSendService(GroupSendService groupSendService) {
		this.groupSendService = groupSendService;
	}
	public GroupSendAction() {
		super();
		dataMap = new HashMap<String, Object>();
	}
	
	public String getGroupSendById(){
		String httpUrl = commonService.getCodeValue(PubCode.httpUrl);
		groupSend = groupSendService.getGroupSendById(id, httpUrl);
		return SUCCESS;
	}
	
	public String saveGroupSend(){
		GroupSend groupSend = new GroupSend();
		groupSend.setContent(request.getParameter("content"));
		groupSend.setImageName(request.getParameter("imageName"));
		groupSend.setS_time(request.getParameter("s_time"));
		groupSend.setTitle(request.getParameter("title"));
		GroupSend gs = groupSendService.saveGroupSend(groupSend);
		dataMap.put("groupSend", gs);
		dataMap.put("success", true);
        return SUCCESS;
	}
	
	public String query(){
		Object page=request.getParameter("page");
        Object limit=request.getParameter("limit");
        if(null==page||null==limit){
            return  PmRtnType.JSONFAILD;
        }
        int ipage=Integer.parseInt(page.toString());
        int ilimit=Integer.parseInt(limit.toString());
        List list = groupSendService.query(ipage, ilimit);
        dataMap.put("groupSendList", list);
		dataMap.put("success", true);
        return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
}
