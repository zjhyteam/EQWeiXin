package org.earthQuake.course.jet.common;

import java.text.ParseException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-22
 * Time: 下午4:17
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class AppException extends Exception{
    public AppException(String message) {
        super(message);
    }

    public AppException() {
    }

    public AppException(String s, AppException localParseException) {

    }

    public AppException(String s, ParseException localParseException) {
    }
}
