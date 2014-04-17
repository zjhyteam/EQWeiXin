package org.earthQuake.course.jet.common;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-23
 * Time: 下午2:28
 * To change this template use File | Settings | File Templates.
 */
public class PmPropertyCopyUtil {
    public static void CopyProperties(Object source, Object dest)throws Exception {
        //获取属性
        BeanInfo sourceBean = Introspector.getBeanInfo(source.getClass(), java.lang.Object.class);
        PropertyDescriptor[] sourceProperty = sourceBean.getPropertyDescriptors();

        BeanInfo destBean = Introspector.getBeanInfo(dest.getClass(), java.lang.Object.class);
        PropertyDescriptor[] destProperty = destBean.getPropertyDescriptors();

        try{
            for(int i=0;i<destProperty.length;i++){
                Class localClass = destProperty[i].getPropertyType();
                for(int j=0;j<sourceProperty.length;j++){

                    if(destProperty[i].getName().equals(sourceProperty[j].getName())){
                        //调用source的getter方法和dest的setter方法
                        /*destProperty[j].getWriteMethod().invoke(dest,
                                sourceProperty[i].getReadMethod().invoke(source));*/
                        copyByType(localClass, destProperty[i],dest, sourceProperty[j].getReadMethod().invoke(source)+"");
                        break;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new Exception("属性复制失败:"+e.getMessage());
        }
    }


    private static void copyByType(Class localClass,PropertyDescriptor pd,Object dest,String str)
            throws InvocationTargetException, IllegalAccessException, AppException {

        if (localClass.equals(Date.class))
        {
            if (str.length() <= 10)
                pd.getWriteMethod().invoke(dest, DateUtil.parseDate(str));
            else
                pd.getWriteMethod().invoke(dest, DateUtil.parseDateTime(str) );
        }
        else if (localClass.equals(String.class))
            pd.getWriteMethod().invoke(dest, str);
        else if ((localClass == Integer.class) || (localClass == Integer.TYPE))
            pd.getWriteMethod().invoke(dest, str.trim().equals("") ? null : Integer.valueOf(Integer.parseInt(str)) );
        else if ((localClass == Short.class) || (localClass == Short.TYPE))
            pd.getWriteMethod().invoke(dest, str.trim().equals("") ? null : Short.valueOf(Short.parseShort(str)));
        else if ((localClass == Long.class) || (localClass == Long.TYPE))
            pd.getWriteMethod().invoke(dest, str.trim().equals("") ? null : Long.valueOf(Long.parseLong(str)) );
        else if ((localClass == Float.class) || (localClass == Float.TYPE))
            pd.getWriteMethod().invoke(dest, str.trim().equals("") ? null : Float.valueOf(Float.parseFloat(str)) );
        else if ((localClass == Double.class) || (localClass == Double.TYPE))
            pd.getWriteMethod().invoke(dest, str.trim().equals("") ? null : Double.valueOf(Double.parseDouble(str)) );
    }
}
