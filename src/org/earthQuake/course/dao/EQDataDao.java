package org.earthQuake.course.dao;

import org.earthQuake.course.common.bean.TabCzCatalogAutoEx;
import org.earthQuake.course.common.bean.TabArea;
import org.earthQuake.course.common.bean.TabMapsDetail;

public interface EQDataDao {
	/**
	 * 增加地震信息
	 * @return
	 */
	public void addEQData(TabCzCatalogAutoEx tabCzCatalogAutoEx, TabMapsDetail tabMapsDetail, TabArea tabArea);
}
