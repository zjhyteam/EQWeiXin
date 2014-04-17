package org.earthQuake.course.dao.impl;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

import org.earthQuake.course.common.bean.TabMapsDetail;
import org.earthQuake.course.dao.BaseDao;
import org.earthQuake.course.dao.MapsDetailDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapsDetailDaoImpl extends BaseDao implements MapsDetailDao{

	public Logger log = LoggerFactory.getLogger(MapsDetailDaoImpl.class);
	
	/**
	 * 得到地震图片信息
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<TabMapsDetail> getMaps(int level, int hours, String rownum) {
		Session session = getSession();
		Query query = null;
		//全球24小时内地震信息
		if(hours == 24){
			String queryString = 
				"select p.mapid, p.IMAGENAME, p.TIME, p.ADDRESS, p.LONGITUDE, p.LATITUDE, " +
				"p.MAGNITUDE, p.INTENSITY, p.DEPTH from ( select t.mapid, t.IMAGENAME, t.TIME, " +
				"t.ADDRESS, t.LONGITUDE, t.LATITUDE, t.MAGNITUDE, t.INTENSITY, t.DEPTH from TABMAPSDETAIL t, " +
				"TABAREA A where t.mapid = a.cata_id and to_date(time,'yyyy/mm/dd hh24:mi:ss')<sysdate and " +
				"to_date(time,'yyyy/mm/dd hh24:mi:ss')>(sysdate-1) order by time desc ) p where rownum <= " + rownum;
			query = session.createSQLQuery(queryString);
		//全球48小时内地震信息
		}else if(hours == 48){
			String queryString = 
				"select p.mapid, p.IMAGENAME, p.TIME, p.ADDRESS, p.LONGITUDE, p.LATITUDE, p.MAGNITUDE, " +
				"p.INTENSITY, p.DEPTH from ( select t.mapid, t.IMAGENAME, t.TIME, t.ADDRESS, t.LONGITUDE, " +
				"t.LATITUDE, t.MAGNITUDE, t.INTENSITY, t.DEPTH from TABMAPSDETAIL t, TABAREA A where " +
				"t.mapid = a.cata_id and to_date(time,'yyyy/mm/dd hh24:mi:ss')<sysdate and " +
				"to_date(time,'yyyy/mm/dd hh24:mi:ss')>(sysdate-2) order by time desc ) p where rownum <= " + rownum;
			query = session.createSQLQuery(queryString);
		//浙江最新地震信息
		}else if(level == 3){
			String queryString = 
				"select p.mapid, p.IMAGENAME, p.TIME, p.ADDRESS, p.LONGITUDE, p.LATITUDE, p.MAGNITUDE, " +
				"p.INTENSITY, p.DEPTH from ( select t.mapid, t.IMAGENAME, t.TIME, t.ADDRESS, t.LONGITUDE, t.LATITUDE, " +
				"t.MAGNITUDE, t.INTENSITY, t.DEPTH from TABMAPSDETAIL t, TABAREA A where " +
				"t.mapid = a.cata_id and a.level_id = " + level + " order by time desc ) p where rownum <= " + rownum;
			query = session.createSQLQuery(queryString);
		//最新地震信息
		}else{
			String queryString = 
				"select p.mapid, p.IMAGENAME, p.TIME, p.ADDRESS, p.LONGITUDE, p.LATITUDE, p.MAGNITUDE, " +
				"p.INTENSITY, p.DEPTH from ( select t.mapid, t.IMAGENAME, t.TIME, t.ADDRESS, t.LONGITUDE, t.LATITUDE, " +
				"t.MAGNITUDE, t.INTENSITY, t.DEPTH from TABMAPSDETAIL t, TABAREA A where " +
				"t.mapid = a.cata_id order by time desc ) p where rownum <= " + rownum;
			query = session.createSQLQuery(queryString);
		}
		List list = query.list();
		TabMapsDetail tabMapsDetail;
		List<TabMapsDetail> list1 = new LinkedList();
		for(int i = 0; i < list.size(); i++){
			tabMapsDetail = new TabMapsDetail();
			Object[] object = (Object[])list.get(i);
			tabMapsDetail.setMapid(object[0]==null?null:object[0].toString());
			tabMapsDetail.setImageName(object[1]==null?null:object[1].toString());
			tabMapsDetail.setTime(object[2]==null?null:object[2].toString());
			tabMapsDetail.setAddress(object[3]==null?null:object[3].toString());
			tabMapsDetail.setLongitude(object[4]==null?null:Double.parseDouble(object[4].toString()));
			tabMapsDetail.setLatitude(object[5]==null?null:Double.parseDouble(object[5].toString()));
			tabMapsDetail.setMagnitude(object[6]==null?null:Double.parseDouble(object[6].toString()));
			tabMapsDetail.setIntensity(object[7]==null?null:Integer.parseInt(object[7].toString()));
			tabMapsDetail.setDepth(object[8]==null?null:Double.parseDouble(object[8].toString()));
			list1.add(tabMapsDetail);
		}
		close(session);
		return list1;
	}

	@Override
	public int updateMaps() {
		return 0;
	}

	@Override
	public int deleteMaps() {
		return 0;
	}

	/**
	 * 增加地震图片信息
	 */
	public int addMaps(TabMapsDetail mapsDetail) {
		Session session = getSession();
		session.beginTransaction();
		session.save(mapsDetail);
		session.getTransaction().commit();
		close(session);
		return 0;
	}

	/**
	 * 通过ID得到地震信息
	 */
	public TabMapsDetail getMaps(String cata_id, String httpImageUrl) {
		Session session = getSession();
		TabMapsDetail tabMapsDetail = (TabMapsDetail)session.load(TabMapsDetail.class, cata_id);
		close(session);
		DecimalFormat df = new DecimalFormat("#####0.0");
		tabMapsDetail.setImageName(httpImageUrl + tabMapsDetail.getImageName());
		tabMapsDetail.setLongitude(tabMapsDetail.getLongitude()==null?null:Double.parseDouble(df.format(tabMapsDetail.getLongitude())));
		tabMapsDetail.setLatitude(tabMapsDetail.getLatitude()==null?null:Double.parseDouble(df.format(tabMapsDetail.getLatitude())));
		tabMapsDetail.setMagnitude(tabMapsDetail.getMagnitude()==null?null:Double.parseDouble(df.format(tabMapsDetail.getMagnitude())));
		tabMapsDetail.setDepth(tabMapsDetail.getDepth()==null?null:Double.parseDouble(df.format(tabMapsDetail.getDepth())));
		return tabMapsDetail;
	}

	/**
	 * 最新大震
	 */
	public List<TabMapsDetail> getMaps(int m, String newBigEQ) {
		Session session = getSession();
		String queryString = 
			"select p.mapid, p.IMAGENAME, p.TIME, p.ADDRESS, p.LONGITUDE, p.LATITUDE, p.MAGNITUDE, " +
			"p.INTENSITY, p.DEPTH from TABMAPSDETAIL p where p.MAGNITUDE >= " + m + " and rownum <= " + newBigEQ;
		Query query = session.createSQLQuery(queryString);
		List list = query.list();
		TabMapsDetail tabMapsDetail;
		List<TabMapsDetail> list1 = new LinkedList();
		for(int i = 0; i < list.size(); i++){
			tabMapsDetail = new TabMapsDetail();
			Object[] object = (Object[])list.get(i);
			tabMapsDetail.setMapid(object[0]==null?null:object[0].toString());
			tabMapsDetail.setImageName(object[1]==null?null:object[1].toString());
			tabMapsDetail.setTime(object[2]==null?null:object[2].toString());
			tabMapsDetail.setAddress(object[3]==null?null:object[3].toString());
			tabMapsDetail.setLongitude(object[4]==null?null:Double.parseDouble(object[4].toString()));
			tabMapsDetail.setLatitude(object[5]==null?null:Double.parseDouble(object[5].toString()));
			tabMapsDetail.setMagnitude(object[6]==null?null:Double.parseDouble(object[6].toString()));
			tabMapsDetail.setIntensity(object[7]==null?null:Integer.parseInt(object[7].toString()));
			tabMapsDetail.setDepth(object[8]==null?null:Double.parseDouble(object[8].toString()));
			list1.add(tabMapsDetail);
		}
		close(session);
		return list1;
	}

}
