package org.earthQuake.course.service;

import java.util.List;

import org.earthQuake.course.common.bean.TabMenuDetail;

/**
 * 菜单信息
 * @author 徐晓亮
 *
 */
public interface MenuService {
	
	/**
	 * 修改菜单
	 * @return
	 */
	public int updateMenus();
	
	/**
	 * 删除菜单
	 * @return
	 */
	public int deleteMenu();
	
	/**
	 * 得到菜单
	 * @return
	 */
	public List<TabMenuDetail> getMenus();
}
