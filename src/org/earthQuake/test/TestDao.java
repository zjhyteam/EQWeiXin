package org.earthQuake.test;


public class TestDao {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		MenuDao user = new MenuDaoImpl();
//		MenuService us = new MenuServiceImpl();
//        
//        List<MenuBean> list = us.getMenus();
//        int i;
//        StringBuffer sbf = new StringBuffer();
//        for(i = 0; i < list.size(); i++){
//        	MenuBean menu = (MenuBean)list.get(i);
//        	if(null != menu.getMenuexplain()){
//        		sbf.append(menu.getMenuexplain());
//        	}else{
//        		sbf.append(menu.getMenuContent());
//        	}
//        }
		//spring 测试
//		ApplicationContext act=new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		MenuDaoImpl MenuDaoImpl=(MenuDaoImpl)act.getBean("menuDao"); //这里放你spring配置文件中的配置的类的id
//		List<TabMenuDetail> menu = MenuDaoImpl.getMenus();
//		TabMenuDetail m = menu.get(1);
//		System.out.println(m.getMenuContent());
		
		System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
		
		//hibernate测试
//		Configuration cfg = new Configuration().configure();
//		  SchemaExport se = new SchemaExport(cfg);
//		  se.create(true, true);
	}

}
