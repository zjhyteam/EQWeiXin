package org.earthQuake.course.jet.common;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-23
 * Time: 下午1:47
 * To change this template use File | Settings | File Templates.
 */
public class PmRtnType {
    public static int FAILD = 0;
    public static int NORMAL_SUCCESS = 1;
    public static int SPE_SUCCESS = 2;
    public static int XML_SUCCESS = 3;
    public static String JSONNORMAL_SUCCESS="{success:"+true+"}";
    public static String JSONFAILD="{success:"+false+"}";
}
