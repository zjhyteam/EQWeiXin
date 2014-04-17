package org.earthQuake.course.service;

import java.util.List;

import org.earthQuake.course.common.bean.TabMapsDetail;

/**
 * 地震图片信息
 * @author 徐晓亮
 *
 */
public interface MapsDetailService {

	/**
	 * 得到地震图片信息
	 * @return
	 */
	public List<TabMapsDetail> getMaps(int level, int hours, String rownum);
	
	/**
	 * 修改地震图片信息
	 * @return
	 */
	public int updateMaps();
	
	/**
	 * 删除地震图片信息
	 * @return
	 */
	public int deleteMaps();
	
	/**
	 * 增加地震图片信息
	 * @return
	 */
	public int addMaps(TabMapsDetail mapsDetail);

	/**
	 * 通过ID得到地震信息
	 * @param cata_id
	 * @return
	 */
	public TabMapsDetail getMaps(String cata_id, String httpImageUrl);
	
	/**
	 * 最新大震
	 * @param m
	 * @return
	 */
	public List<TabMapsDetail> getMaps(int m, String newBigEQ);
}
