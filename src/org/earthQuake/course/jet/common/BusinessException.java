package org.earthQuake.course.jet.common;

import java.lang.reflect.InvocationTargetException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14-1-22
 * Time: 下午4:18
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
public class BusinessException extends InvocationTargetException {
    public BusinessException() {
    }

    public BusinessException(Throwable target) {
        super(target);
    }

    public BusinessException(Throwable target, String s) {
        super(target, s);
    }
}
