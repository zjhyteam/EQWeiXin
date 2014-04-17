package org.earthQuake.course.jet.common;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.cglib.beans.BeanGenerator;
import net.sf.cglib.beans.BeanMap;

import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-22
 * Time: 下午4:19
 * To change this template use File | Settings | File Templates.
 */
public class PageModel {
    protected JdbcTemplate jdbcTemplate;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Session session;

    protected void CopyProperties(Object source, Object dest)throws Exception {
        PmPropertyCopyUtil.CopyProperties(source,dest);
    }

    private Object generateBean(Map propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        Set keySet = propertyMap.keySet();
        for (Iterator i = keySet.iterator(); i.hasNext();) {
            String key = (String) i.next();
            generator.addProperty(key,String.class);
        }
        return generator.create();
    }

    /*
    复制map中的数据到对象属性中
     */
    protected Object copyHtmlEleToObj(Map propertyMap){
        Object object=generateBean(propertyMap);
        Set keySet = propertyMap.keySet();
        BeanMap beanMap=BeanMap.create(object);
        for (Iterator i = keySet.iterator(); i.hasNext();) {
            String key = (String) i.next();
            beanMap.put(key,propertyMap.get(key));
        }
        return object;
    }


    protected Map getMap(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException {
        return EntityMapUtil.getMap(entity);
    }

    public void close(Session session) {
        if(session != null) {
            session.close();
        }
    }
}
