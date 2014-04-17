package org.earthQuake.course.dao.impl;

import org.earthQuake.course.common.bean.TabCzCatalogAutoEx;
import org.earthQuake.course.common.bean.TabArea;
import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.dao.BaseDao;
import org.earthQuake.course.dao.EQDataDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EQDataDaoImpl extends BaseDao implements EQDataDao{

	public Logger log = LoggerFactory.getLogger(EQDataDaoImpl.class);
	
	public void addEQData(TabCzCatalogAutoEx tabCzCatalogAutoEx, TabMapsDetail tabMapsDetail, TabArea tabArea) {
		Session session = getSession();
		session.beginTransaction();
		session.save(tabCzCatalogAutoEx);
		session.save(tabMapsDetail);
		session.save(tabArea);
		session.getTransaction().commit();
		close(session);
	}

}
