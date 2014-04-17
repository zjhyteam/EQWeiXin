package org.earthQuake.course.serviceImpl;

import java.util.List;

import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.dao.MapsDetailDao;
import org.earthQuake.course.service.MapsDetailService;

public class MapsDetailServiceImpl implements MapsDetailService {

	private MapsDetailDao mapsDetailDao;
	
	public MapsDetailDao getMapsDetailDao() {
		return mapsDetailDao;
	}

	public void setMapsDetailDao(MapsDetailDao mapsDetailDao) {
		this.mapsDetailDao = mapsDetailDao;
	}

	public List<TabMapsDetail> getMaps(int level, int hours, String rownum) {
		return mapsDetailDao.getMaps(level, hours, rownum);
	}

	public int updateMaps() {
		return mapsDetailDao.updateMaps();
	}

	public int deleteMaps() {
		return mapsDetailDao.deleteMaps();
	}

	public int addMaps(TabMapsDetail mapsDetail) {
		return mapsDetailDao.addMaps(mapsDetail);
	}

	public TabMapsDetail getMaps(String cata_id, String httpImageUrl) {
		return mapsDetailDao.getMaps(cata_id, httpImageUrl);
	}

	public List<TabMapsDetail> getMaps(int m, String newBigEQ) {
		return mapsDetailDao.getMaps(m, newBigEQ);
	}

}
