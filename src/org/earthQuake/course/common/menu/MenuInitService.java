package org.earthQuake.course.common.menu;

import java.util.List;
import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuInitService {

	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	private CommonService commonService;

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public void excute(){
		String appid="", secret="";
		List<CodeMaintenance> clist = commonService.getCodeValueList();
		for(int i = 0; i < clist.size(); i++){
			CodeMaintenance codeMaintenance = (CodeMaintenance)clist.get(i);
			if(codeMaintenance.getCode().equals(PubCode.appid)){
				appid = codeMaintenance.getValue();
			}else if(codeMaintenance.getCode().equals(PubCode.secret)){
				secret = codeMaintenance.getValue();
			}
		}
		
		// 调用接口获取access_token
		AccessToken at = WeixinUtil.getAccessToken(appid, secret);

		if (null != at) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(getMenu(), at.getToken());

			// 判断菜单创建结果
			if (0 == result){
				log.info("菜单创建成功！");
				System.out.println("菜单创建成功！");
			}
			else {
				log.info("菜单创建失败，错误码：" + result);
			}
		}
	}
	
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		
		CommonButton btn11 = new CommonButton();
		btn11.setName("浙江地震");
		btn11.setType("click");
		btn11.setKey("11");
		
		CommonButton btn12 = new CommonButton();
		btn12.setName("最新地震");
		btn12.setType("click");
		btn12.setKey("12");

		CommonButton btn13 = new CommonButton();
		btn13.setName("24小时内");
		btn13.setType("click");
		btn13.setKey("13");

		CommonButton btn14 = new CommonButton();
		btn14.setName("48小时内");
		btn14.setType("click");
		btn14.setKey("14");

		CommonButton btn21 = new CommonButton();
		btn21.setName("关键字查询");
		btn21.setType("click");
		btn21.setKey("21");

		CommonButton btn22 = new CommonButton();
		btn22.setName("自定义查询");
		btn22.setType("click");
		btn22.setKey("22");
		
		CommonButton btn23 = new CommonButton();
		btn23.setName("地震小常识");
		btn23.setType("click");
		btn23.setKey("23");

		CommonButton btn31 = new CommonButton();
		btn31.setName("最新大震");
		btn31.setType("click");
		btn31.setKey("31");

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("地震信息");
		mainBtn1.setSub_button(new CommonButton[] { btn11, btn12, btn13, btn14 });

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("科普知识");
		mainBtn2.setSub_button(new CommonButton[] { btn21, btn22, btn23});

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("大震专题");
		mainBtn3.setSub_button(new CommonButton[] { btn31});

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

		return menu;
	}
}
