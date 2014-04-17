/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earthQuake.course.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 *
 * @author jack
 */
public class StringHelper {

    public static String urlformat(String pattern, Map<String, Object> arguments) {
        String formatedStr = pattern;
        for (String key : arguments.keySet()) {
            formatedStr = formatedStr.replaceAll("\\$\\{" + key + "\\}", arguments.get(key).toString());
        }
        return formatedStr;
    }
    public static String getTimeStr(){
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }
    public static String getTimeStrFormat(String format){
        DateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }
}
