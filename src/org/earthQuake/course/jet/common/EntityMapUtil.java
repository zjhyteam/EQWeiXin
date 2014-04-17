package org.earthQuake.course.jet.common;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-24
 * Time: 上午9:55
 * To change this template use File | Settings | File Templates.
 */
public class EntityMapUtil {
    public static Map getMap(Object entity) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IntrospectionException {
        Map map=new HashMap();
        Class c=entity.getClass();
        Field[] fs=c.getDeclaredFields();
        for(Field f:fs){
            PropertyDescriptor pd = new PropertyDescriptor(f.getName(),c);
            Method getMethod = pd.getReadMethod();//获得get方法
            String k=f.getName().toLowerCase();
            Object obj=getMethod.invoke(entity);
            if(null!=obj&&"java.sql.Timestamp".equals(obj.getClass().getName())){
                map.put(k,obj.toString());
            }else{

                map.put(k,obj);
            }
        }
        return map;
    }

}
