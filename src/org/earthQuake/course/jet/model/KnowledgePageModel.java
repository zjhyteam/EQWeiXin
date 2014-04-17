package org.earthQuake.course.jet.model;

import java.beans.IntrospectionException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.common.bean.Knowledge;
import org.earthQuake.course.jet.common.PageModel;
import org.earthQuake.course.jet.common.ParameterUtil;
import org.earthQuake.course.jet.common.PmRtnType;
import org.earthQuake.course.service.CommonService;
import org.earthQuake.course.serviceImpl.CommonServiceImpl;
import org.hibernate.Query;

/**
 * 科普知识管理
 * @author 徐晓亮
 *
 */
public class KnowledgePageModel extends PageModel {
	
	private Knowledge knowledge = new Knowledge();
	private CommonService commonService;
//	private String localFileUrl = "";
//	private String httpUrl = "";
//	private String knowledgeImage = "";
	
	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	
	public KnowledgePageModel() {
		super();
		commonService = new CommonServiceImpl();
	}

	/**
     * 保存科普知识
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public String save() throws Exception {
		
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
    	
    	Map map= ParameterUtil.toMap(request);
    	this.CopyProperties(this.copyHtmlEleToObj(map), knowledge);
    	
		if(null != map.get("addflag") && map.get("addflag").toString().equals("1")){
			Query query = session.createSQLQuery("select KNOWLEDGE_SEQ.nextval from dual");
	        List list = query.list();
			int id = Integer.parseInt(list.get(0).toString());
			
			ToolUtil toolUtil = new ToolUtil();
			String content = toolUtil.getDateString();
			String path = localFileUrl + content;
			toolUtil.writefile(path, knowledge.getContent(), false);
			
			if(null == knowledge.getKeywordsflag()){
				knowledge.setKeywordsflag(0);
			}
			
			knowledge.setId(id);
			knowledge.setContent(content);
			session.save(knowledge);
		}else{
			ToolUtil toolUtil = new ToolUtil();
			//String content = toolUtil.getDateString();
			//String path = toolUtil.getLocalFileUrl() + content;
			String content = knowledge.getContent();
			if(content.substring(content.length()-5,content.length()).equals(".text")){
				session.update(knowledge);
			}else{
				String[] imgName;
				imgName = map.get("imageName").toString().split("/");
				knowledge.setImageName(knowledgeImage + imgName[imgName.length-1]);
				
				if(null == knowledge.getKeywordsflag()){
					knowledge.setKeywordsflag(0);
				}
				
				String textname = map.get("textname").toString();
				String path = localFileUrl + textname;
				toolUtil.writefile(path, knowledge.getContent(), false);
				knowledge.setContent(textname);
				session.update(knowledge);
			}
			
		}
    	return PmRtnType.JSONNORMAL_SUCCESS;
    }
    
	/**
	 * 删除科普知识
	 * @return
	 * @throws Exception 
	 */
	public String delete() throws Exception{
		Map map= ParameterUtil.toMap(request);
        this.CopyProperties(this.copyHtmlEleToObj(map), knowledge);
        String id = map.get("id").toString();
        if(!id.equals("")){
            session.delete(knowledge);
        }
    	return PmRtnType.JSONNORMAL_SUCCESS;
	}
	
	/**
	 * 根据条件得到科普知识
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 * @throws UnsupportedEncodingException 
	 */
	public String queryForCondition() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException, UnsupportedEncodingException{
		Object limit=request.getParameter("limit");
        Object keywords=request.getParameter("keywords");
        Object summary=request.getParameter("summary");
        Object title=request.getParameter("title");
        Object keywordsflag=request.getParameter("keywordsflag");
        Object id=request.getParameter("id");
        if(null==limit){
            return  PmRtnType.JSONFAILD;
        }
        int ipage=1;
        int ilimit=Integer.parseInt(limit.toString());

        StringBuffer sqlStr = new StringBuffer();
        sqlStr.append(" select p.ID, p.NUM, p.KEYWORDSFLAG, p.KEYWORDS, p.TITLE, p.SUMMARY, p.CONTENT, p.IMAGENAME ");
        sqlStr.append(" from KNOWLEDGE p ");
        
        if((null != keywords && !"".equals(keywords))||(null != summary && !"".equals(summary)) || 
        		(null != keywordsflag && !"".equals(keywordsflag)) || (null != title && !"".equals(title)) || 
        		(null != id && !"".equals(id))){
        	sqlStr.append(" where ");
        }
        //关键字
        if(null != keywords && !"".equals(keywords)){
        	sqlStr.append(" p.KEYWORDS = '" + keywords + "'");
        }
        //摘要
        if(null != summary && !"".equals(summary)){
        	if(null != keywords && !"".equals(keywords)){
        		sqlStr.append(" and ");
        	}
        	sqlStr.append(" (p.SUMMARY like '%" + summary + "%' or " +
				"p.SUMMARY like '%" + summary + "' or p.SUMMARY like '" + summary + "%') ");
        }
        //标题
        if(null != title && !"".equals(title)){
        	if((null != keywords && !"".equals(keywords)) || (null != summary && !"".equals(summary))){
        		sqlStr.append(" and ");
        	}
        	sqlStr.append(" (p.TITLE like '%" + title + "%' or " +
				"p.TITLE like '%" + title + "' or p.TITLE like '" + title + "%') ");
        }
        //关键字标记
        if(null != keywordsflag && !"".equals(keywordsflag)){
        	if((null != keywords && !"".equals(keywords))||(null != summary && !"".equals(summary)) || 
        			(null != title && !"".equals(title))){
        		sqlStr.append(" and ");
        	}
        	sqlStr.append(" p.KEYWORDSFLAG = " + keywordsflag);
        }
        //科普知识表ID
        if(null != id && !"".equals(id)){
        	sqlStr.append(" p.ID = " + id);
        }
        List querylist =session.createSQLQuery(sqlStr.toString())
		        .setFirstResult((ipage-1)*ilimit)    //分页
		        .setMaxResults(ilimit)
		        .list();
        ArrayList list=new ArrayList();
        for(int i=0; i<querylist.size(); i++){
        	Knowledge knowledge = new Knowledge();
        	Object[] object = (Object[])querylist.get(i);
        	knowledge.setId((null == object[0])?null:Integer.parseInt(object[0].toString()));
        	knowledge.setNum((null == object[1])?null:Integer.parseInt(object[1].toString()));
        	knowledge.setKeywordsflag((null == object[2])?null:Integer.parseInt(object[2].toString()));
        	knowledge.setKeywords((null == object[3])?null:object[3].toString());
        	knowledge.setTitle((null == object[4])?null:object[4].toString());
        	knowledge.setSummary((null == object[5])?null:object[5].toString());
        	knowledge.setContent((null == object[6])?null:object[6].toString());
        	knowledge.setImageName((null == object[7])?null:object[7].toString());
        	list.add((knowledge));
        }
        
        Map<String,Object>res=new HashMap<String, Object>();
        res.put("totalCount",session.createSQLQuery(sqlStr.toString()).list().size());
        res.put("success",true);
        res.put("results",list);
        return JSONObject.fromObject(res).toString();
	}
	
	/**
	 * 得到文本文件内的科普知识和图片路径
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IntrospectionException
	 */
	public String getContentAndImgPath() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException{
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
		
		ArrayList list=new ArrayList();
		Knowledge knowledge = new Knowledge();
		knowledge.setContent(scontent);
		knowledge.setImageName(simageName);
		list.add(knowledge);
		
        Map<String,Object>res=new HashMap<String, Object>();
        res.put("totalCount",1);
        res.put("success",true);
        res.put("results",list);
        return JSONObject.fromObject(res).toString();
	}
	
	/**
	 * 得到科普知识
	 */
	public String query() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException{
		Object page=request.getParameter("page");
        Object limit=request.getParameter("limit");
        if(null==page||null==limit){
            return  PmRtnType.JSONFAILD;
        }
        int ipage=Integer.parseInt(page.toString());
        int ilimit=Integer.parseInt(limit.toString());

        List querylist =session.createCriteria(Knowledge.class)
                .setFirstResult((ipage-1)*ilimit)    //分页
                .setMaxResults(ilimit)
                .list();
        ArrayList<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Iterator iterator=querylist.iterator();
        while(iterator.hasNext()){
        	Knowledge j=(Knowledge)iterator.next();
        	/*Map map=new HashMap();
        	map.put("id", getMap(j).get("id"));*/
        		list.add(getMap(j));
            
        }
        
        Map<String,Object>res=new HashMap<String, Object>();
        res.put("totalCount",session.createCriteria(Knowledge.class).list().size());
        res.put("success",true);
        res.put("results",list);
        return JSONObject.fromObject(res).toString();
	}
}
