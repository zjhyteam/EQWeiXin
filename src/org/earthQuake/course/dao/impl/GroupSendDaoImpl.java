package org.earthQuake.course.dao.impl;

import java.util.List;

import org.earthQuake.course.common.bean.GroupSend;
import org.earthQuake.course.dao.BaseDao;
import org.earthQuake.course.dao.GroupSendDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 群发接口实现类
 * @author 徐晓亮
 *
 */
public class GroupSendDaoImpl extends BaseDao implements GroupSendDao{
	
	public Logger log = LoggerFactory.getLogger(GroupSendDaoImpl.class);
	
	/**
	 * 短信群发信息保存到数据库
	 */
	public GroupSend saveGroupSend(GroupSend groupSend) {
		Session session = getSession();
		session.beginTransaction();
        Query query = session.createSQLQuery("select GROUPSEND_SEQ.nextval from dual");
        List list = query.list();
		int id = Integer.parseInt(list.get(0).toString());
		groupSend.setId(id);
		session.save(groupSend);
		session.getTransaction().commit();
		close(session);
        return groupSend;
	}

	/**
	 * 得到群发信息的id
	 */
	public GroupSend getGroupSendById(String id, String httpUrl) {
		Session session = getSession();
		Query query = session.createQuery("from GroupSend where id = " + id);
		List<GroupSend> list = query.list();
		close(session);
		
		GroupSend groupSend = new GroupSend();
		groupSend.setContent(((GroupSend)list.get(0)).getContent().toString());
		groupSend.setId(((GroupSend)list.get(0)).getId());
		groupSend.setImageName(httpUrl + ((GroupSend)list.get(0)).getImageName().toString());
		groupSend.setS_time(((GroupSend)list.get(0)).getS_time().toString());
		groupSend.setTitle(((GroupSend)list.get(0)).getTitle().toString());
		return groupSend;
	}

	public List query(int page, int limit) {
		Session session = getSession();
		List querylist =session.createCriteria(GroupSend.class)
        .addOrder( Order.desc("id") )
        .setFirstResult((page-1)*limit)    //分页
        .setMaxResults(limit)
        .list();
		return querylist;
	}
}
