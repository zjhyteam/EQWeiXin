package org.earthQuake.course.serviceImpl;

import org.earthQuake.course.common.bean.TabCzCatalogAutoEx;
import org.earthQuake.course.common.bean.TabArea;
import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.dao.EQDataDao;
import org.earthQuake.course.service.EQDataService;

public class EQDataServiceImpl implements EQDataService {

	private EQDataDao eQDataDao;
	
	public EQDataDao geteQDataDao() {
		return eQDataDao;
	}

	public void seteQDataDao(EQDataDao eQDataDao) {
		this.eQDataDao = eQDataDao;
	}

	public void addEQData(TabCzCatalogAutoEx tabCzCatalogAutoEx, TabMapsDetail tabMapsDetail, TabArea tabArea) {
		eQDataDao.addEQData(tabCzCatalogAutoEx, tabMapsDetail, tabArea);
	}

}
