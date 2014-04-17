package org.earthQuake.course.jet.model;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.jet.common.PageModel;
import org.earthQuake.course.jet.common.ParameterUtil;
import org.earthQuake.course.jet.common.PmRtnType;

/**
 * 可维护性代码
 * @author 徐晓亮
 *
 */
public class JetSelectPageModel extends PageModel {
    private CodeMaintenance codeMaintenance=new CodeMaintenance();

    /**
     * 保存可维护性代码
     * @return
     * @throws Exception
     */
    public String saveCodeMaintenance() throws Exception {
    	Map map= ParameterUtil.toMap(request);
    	this.CopyProperties(this.copyHtmlEleToObj(map), codeMaintenance);
        if(map.get("flag").toString().equals("1")){
        	map.put("flag", "0");
        	this.CopyProperties(this.copyHtmlEleToObj(map), codeMaintenance);
            session.save(codeMaintenance);
        }else{
            session.update(codeMaintenance);
        }
    	return PmRtnType.JSONNORMAL_SUCCESS;
    }

    /**
     * 删除可维护性代码
     * @return
     * @throws Exception 
     */
    @SuppressWarnings("rawtypes")
	public String deleteMaintenance() throws Exception{
    	Map map= ParameterUtil.toMap(request);
        this.CopyProperties(this.copyHtmlEleToObj(map), codeMaintenance);
        String id = map.get("id").toString();
        if(!id.equals("")){
            session.delete(codeMaintenance);
        }
    	return PmRtnType.JSONNORMAL_SUCCESS;
    }
    
    /**
     * 检索可维护性代码
     * @return
     * @throws NoSuchMethodException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public String queryCodeMaintenance() throws NoSuchMethodException, IntrospectionException, IllegalAccessException, InvocationTargetException {
        Object page=request.getParameter("page");
        Object limit=request.getParameter("limit");
        if(null==page||null==limit){
            return  PmRtnType.JSONFAILD;
        }
        int ipage=Integer.parseInt(page.toString());
        int ilimit=Integer.parseInt(limit.toString());

        List querylist =session.createCriteria(CodeMaintenance.class)
                .setFirstResult((ipage-1)*ilimit)    //分页
                .setMaxResults(ilimit)
                .list();
        ArrayList<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Iterator iterator=querylist.iterator();
        while(iterator.hasNext()){
        	CodeMaintenance j=(CodeMaintenance)iterator.next();
            list.add(getMap(j));
        }
        
        Map<String,Object>res=new HashMap<String, Object>();
        res.put("totalCount",session.createCriteria(CodeMaintenance.class).list().size());
        res.put("success",true);
        res.put("results",list);
        return JSONObject.fromObject(res).toString();
    }
}
