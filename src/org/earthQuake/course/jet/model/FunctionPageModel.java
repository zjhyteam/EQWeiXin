package org.earthQuake.course.jet.model;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.earthQuake.course.common.bean.JetFunction;
import org.earthQuake.course.jet.common.PageModel;
import org.hibernate.Query;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13-12-28
 * Time: 下午8:53
 * To change this template use File | Settings | File Templates.
 */
public class FunctionPageModel extends PageModel {

    private JetFunction jetFunction=new JetFunction();
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public String queryFunctionTree() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IntrospectionException {
        String node=request.getParameter("node");

        String sql="from JetFunction";
        if(null==node||"".equals(node)||"root".equals(node)){
            //业务菜单
        	node="1";
            sql+=" where parent='"+node+"'";
        }else{
            sql+=" where parent='"+node+"'";
        }

        Query query=session.createQuery(sql);
        ArrayList<Map<String,Object>> list=new ArrayList<Map<String, Object>>();
        List querylist=query.list();
        Iterator iterator=querylist.iterator();
        while(iterator.hasNext()){
            JetFunction j=(JetFunction)iterator.next();
            Map map=getMap(j);
            map.put("id",j.getFunctionid());
            map.put("text",j.getTitle());
            map.put("leaf",j.getNodetype().equals("1")?false:true);
            map.put("value",j.getLocation());
            list.add(map);
        }

        String json= JSONArray.fromObject(list).toString();
        return json;
    }
}
