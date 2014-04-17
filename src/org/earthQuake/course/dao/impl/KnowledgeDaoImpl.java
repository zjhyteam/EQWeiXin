package org.earthQuake.course.dao.impl;

import java.util.List;

import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.Knowledge;
import org.earthQuake.course.dao.BaseDao;
import org.earthQuake.course.dao.KnowledgeDao;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 科普知识实现类
 * @author 徐晓亮
 *
 */
public class KnowledgeDaoImpl extends BaseDao implements KnowledgeDao{

	public Logger log = LoggerFactory.getLogger(MapsDetailDaoImpl.class);
	public Knowledge getKnowledgeByNum(int num) {
		Session session = getSession();
		Query query = session.createQuery("from Knowledge where keywordsflag = 1 and num = " + num);
		List<Knowledge> list = query.list();
		close(session);
		return (Knowledge)list.get(0);
	}

	public List<Knowledge> getKnowledgeKWList() {
		Session session = getSession();
		Query query = session.createQuery("from Knowledge where keywordsflag = 1 order by num");
		List<Knowledge> list = query.list();
		close(session);
		return list;
	}

	public Knowledge getKnowledge() {
		Session session = getSession();
		Query query = session.createSQLQuery("select p.ID, p.NUM, p.KEYWORDSFLAG, p.KEYWORDS, p.TITLE, " +
				"p.SUMMARY, p.CONTENT, p.IMAGENAME from (select t.ID, t.NUM, t.KEYWORDSFLAG, t.KEYWORDS, t.TITLE, " +
				"t.SUMMARY, t.CONTENT, t.IMAGENAME from KNOWLEDGE t order by dbms_random.value) p where rownum = 1 ");
		List list = query.list();
		Knowledge knowledge;
		knowledge = new Knowledge();
		Object[] object = (Object[])list.get(0);
		knowledge.setId(Integer.parseInt(object[0].toString()));
		knowledge.setNum(Integer.parseInt(object[1].toString()));
		knowledge.setKeywordsflag(Integer.parseInt(object[2].toString()));
		knowledge.setKeywords(object[3].toString());
		knowledge.setTitle(object[4].toString());
		knowledge.setSummary(object[5].toString());
		knowledge.setContent(object[6].toString());
		knowledge.setImageName(object[7].toString());
		close(session);
		return knowledge;
	}
	
	public Knowledge getKnowledgeById(int id) {
		Session session = getSession();
		Query query = session.createQuery("from Knowledge where id = " + id);
		List<Knowledge> list = query.list();
		close(session);
		return (Knowledge)list.get(0);
	}

	public List<Knowledge> getKnowledgeByTitle(String title) {
		Session session = getSession();
		Query query = session.createQuery("from Knowledge where title like '%" + title + "%' or " +
				"title like '%" + title + "' or title like '" + title + "%'  order by id");
		List<Knowledge> list = query.list();
		close(session);
		return list;
	}

	public void save(String updateInfo, String addflag, String imageName, String textname, String knowledgeImage, String localFileUrl, Knowledge knowledge) {
		Session session = getSession();
		session.beginTransaction();
		//新增
		if(null != addflag && addflag.toString().equals("1")){
			Query query = session.createSQLQuery("select KNOWLEDGE_SEQ.nextval from dual");
	        List list = query.list();
			int id = Integer.parseInt(list.get(0).toString());
			
			ToolUtil toolUtil = new ToolUtil();
			String content = toolUtil.getDateString();
			String path = localFileUrl + content;
			toolUtil.writefile(path, knowledge.getContent(), false);
			
			if(null == knowledge.getKeywordsflag()){
				knowledge.setKeywordsflag(0);
			}
			
			knowledge.setId(id);
			knowledge.setContent(content);
			session.save(knowledge);
		}else{
			ToolUtil toolUtil = new ToolUtil();
			//String content = toolUtil.getDateString();
			//String path = toolUtil.getLocalFileUrl() + content;
			String content = knowledge.getContent();
			//不是详细页面修改
			if("".equals(updateInfo) || null == updateInfo){
				session.update(knowledge);
			//详细页面修改
			}else{
				String[] imgName;
				imgName = imageName.split("/");
				knowledge.setImageName(knowledgeImage + imgName[imgName.length-1]);
				
				if(null == knowledge.getKeywordsflag()){
					knowledge.setKeywordsflag(0);
				}
				
				String path = localFileUrl + textname;
				toolUtil.writefile(path, knowledge.getContent(), false);
				knowledge.setContent(textname);
				session.update(knowledge);
				
			}
			
		}
		session.getTransaction().commit();
		close(session);
	}
}
