package org.earthQuake.course.service;

import org.earthQuake.course.common.bean.TabCzCatalogAutoEx;
import org.earthQuake.course.common.bean.TabArea;
import org.earthQuake.course.common.bean.TabMapsDetail;

/**
 * 获取地震信息
 * @author 徐晓亮
 *
 */
public interface EQDataService {
	
	/**
	 * 增加地震信息
	 * @return
	 */
	public void addEQData(TabCzCatalogAutoEx tabCzCatalogAutoEx, TabMapsDetail tabMapsDetail, TabArea tabArea);
}
