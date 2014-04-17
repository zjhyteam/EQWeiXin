package org.earthQuake.course.dao.impl;

import java.util.List;

import org.earthQuake.course.common.bean.CodeMaintenance;
import org.earthQuake.course.dao.BaseDao;
import org.earthQuake.course.dao.CommonDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonDaoImpl extends BaseDao implements CommonDao{

	public Logger log = LoggerFactory.getLogger(CommonDaoImpl.class);

	/**
	 * 取得代码值
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getCodeValue(String code){
	    Session session = getSession();
	    Query query = session.createSQLQuery("select p.ID, p.NAME, p.VALUE, p.VALID, p.CODE, p.DESCRIPTION, " +
	    		" p.FLAG from CodeMaintenance p where CODE = " + code);
		List list = query.list();
		close(session);
		CodeMaintenance codeMaintenance = new CodeMaintenance();
		Object[] object = (Object[])list.get(0);
		codeMaintenance.setValue(object[2].toString());
		return codeMaintenance.getValue().toString();
	}

	/**
	 * 得到所有代码值
	 */
	@Override
	public List<CodeMaintenance> getCodeValueList() {
		Session session = getSession();
	    Query query = session.createQuery(" from CodeMaintenance ");
		List<CodeMaintenance> list = query.list();
		close(session);
		return list;
	}
}
