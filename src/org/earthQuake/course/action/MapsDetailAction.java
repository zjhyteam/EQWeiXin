package org.earthQuake.course.action;

import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.service.CommonService;
import org.earthQuake.course.service.MapsDetailService;

/**
 * 地图信息
 * @author 徐晓亮
 *
 */
@SuppressWarnings("serial")
public class MapsDetailAction extends BaseAction{
	
	private MapsDetailService mapsDetailService;
	private CommonService commonService;
	private String cata_id;
	private TabMapsDetail tabMapsDetail;

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public TabMapsDetail getTabMapsDetail() {
		return tabMapsDetail;
	}

	public void setTabMapsDetail(TabMapsDetail tabMapsDetail) {
		this.tabMapsDetail = tabMapsDetail;
	}

	public String getCata_id() {
		return cata_id;
	}

	public void setCata_id(String cata_id) {
		this.cata_id = cata_id;
	}

	public MapsDetailService getMapsDetailService() {
		return mapsDetailService;
	}

	public void setMapsDetailService(MapsDetailService mapsDetailService) {
		this.mapsDetailService = mapsDetailService;
	}
	
	public String showDetail(){
		String httpImageUrl = commonService.getCodeValue(PubCode.httpImageUrl);
		tabMapsDetail = mapsDetailService.getMaps(cata_id, httpImageUrl);
		request.setAttribute("lon",tabMapsDetail.getLongitude());
		request.setAttribute("lat",tabMapsDetail.getLatitude());
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}
	
}
