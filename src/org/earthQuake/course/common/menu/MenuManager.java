package org.earthQuake.course.common.menu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


/**
 * 菜单管理器类
 * 
 * @author 徐晓亮
 */
public class MenuManager {
	public static void main(String[] args) {
		ApplicationContext act=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		MenuInitService menuInitService=(MenuInitService)act.getBean("menuinitservice");
		menuInitService.excute();
	}
}