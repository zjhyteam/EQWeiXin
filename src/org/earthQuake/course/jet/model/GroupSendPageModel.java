package org.earthQuake.course.jet.model;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONObject;
import org.earthQuake.course.common.bean.GroupSend;
import org.earthQuake.course.jet.common.PageModel;
import org.earthQuake.course.jet.common.ParameterUtil;
import org.earthQuake.course.jet.common.PmRtnType;
import org.hibernate.Query;
import org.hibernate.criterion.Order;

/**
 * 后台微信群发功能
 */
public class GroupSendPageModel extends PageModel {

    private GroupSend groupSend=new GroupSend();
    
    /**
     * 保存发送信息
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
	public String saveGroupSend() throws Exception {
        Map map= ParameterUtil.toMap(request);
        this.CopyProperties(this.copyHtmlEleToObj(map),groupSend);
        Query query = session.createSQLQuery("select GROUPSEND_SEQ.nextval from dual");
        List list = query.list();
		
		int id = Integer.parseInt(list.get(0).toString());
		groupSend.setId(id);
		session.save(groupSend);
		
		Map<String,Object>res=new HashMap<String, Object>();
		res.put("success",true);
		res.put("id",list);
        return JSONObject.fromObject(res).toString();
    }

    /**
     * 查询发送信息
     * @return
     * @throws NoSuchMethodException
     * @throws IntrospectionException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public String query() throws NoSuchMethodException, IntrospectionException, IllegalAccessException, InvocationTargetException {
        Object page=request.getParameter("page");
        Object limit=request.getParameter("limit");
        if(null==page||null==limit){
            return  PmRtnType.JSONFAILD;
        }
        int ipage=Integer.parseInt(page.toString());
        int ilimit=Integer.parseInt(limit.toString());
        List querylist =session.createCriteria(GroupSend.class)
                .addOrder( Order.desc("id") )
                .setFirstResult((ipage-1)*ilimit)    //分页
                .setMaxResults(ilimit)
                .list();
        ArrayList<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        Iterator iterator=querylist.iterator();
        while(iterator.hasNext()){
        	GroupSend j=(GroupSend)iterator.next();
            list.add(getMap(j));
        }

        Map<String,Object>res=new HashMap<String, Object>();
        res.put("totalCount",session.createCriteria(GroupSend.class).list().size());
        res.put("success",true);
        res.put("results",list);
        return JSONObject.fromObject(res).toString();
    }
}
