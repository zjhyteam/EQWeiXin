package org.earthQuake.course.dao;

import java.util.List;

import org.earthQuake.course.common.bean.CodeMaintenance;

/**
 * 取得代码
 * @author 徐晓亮
 *
 */
public interface CommonDao {
	
	/**
	 * 取得代码值
	 * @param code
	 * @return
	 */
	public String getCodeValue(String code);
	
	/**
	 * 得到所有代码值
	 * @return
	 */
	public List<CodeMaintenance> getCodeValueList();
}
