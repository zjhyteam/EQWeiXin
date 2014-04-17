package org.earthQuake.course.serviceImpl;

import java.util.List;

import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.dao.CommonDao;
import org.earthQuake.course.service.CommonService;

public class CommonServiceImpl implements CommonService{

	private CommonDao commonDao;
	
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	@Override
	public String getCodeValue(String code) {
		return commonDao.getCodeValue(code);
	}

	@Override
	public List<CodeMaintenance> getCodeValueList() {
		return commonDao.getCodeValueList();
	}

}
