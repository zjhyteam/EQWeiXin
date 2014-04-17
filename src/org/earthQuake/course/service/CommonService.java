package org.earthQuake.course.service;

import java.util.List;

import org.earthQuake.course.common.bean.CodeMaintenance;

public interface CommonService {
	
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
