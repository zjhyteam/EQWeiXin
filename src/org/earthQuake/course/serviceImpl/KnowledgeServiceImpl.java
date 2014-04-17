package org.earthQuake.course.serviceImpl;

import java.util.List;

import org.earthQuake.course.common.PubCode;
import org.earthQuake.course.common.ToolUtil;
import org.earthQuake.course.common.bean.Knowledge;
import org.earthQuake.course.dao.KnowledgeDao;
import org.earthQuake.course.service.CommonService;
import org.earthQuake.course.service.KnowledgeService;

public class KnowledgeServiceImpl implements KnowledgeService{

	private KnowledgeDao knowledgeDao;
	
	public KnowledgeDao getKnowledgeDao() {
		return knowledgeDao;
	}

	public void setKnowledgeDao(KnowledgeDao knowledgeDao) {
		this.knowledgeDao = knowledgeDao;
	}

	/**
	 * 通过num得到一个科普知识对象
	 */
	public Knowledge getKnowledgeByNum(int num, String httpImageUrl, String localFileUrl) {
		ToolUtil toolUtil = new ToolUtil();
//		String httpImageUrl = commonService.getCodeValue(PubCode.httpImageUrl);
//		String localFileUrl = commonService.getCodeValue(PubCode.localFileUrl);
		Knowledge knowledge = knowledgeDao.getKnowledgeByNum(num);
		knowledge.setImageName(httpImageUrl + knowledge.getImageName());
		knowledge.setContent(toolUtil.readfile(localFileUrl + knowledge.getContent().toString()));
		return knowledge;
	}

	/**
	 * 得到所有关键字的科普知识列表
	 */
	public List<Knowledge> getKnowledgeKWList() {
		return knowledgeDao.getKnowledgeKWList();
	}

	/**
	 * 随机取得一条数据
	 */
	public Knowledge getKnowledge() {
		return knowledgeDao.getKnowledge();
	}
	
	/**
	 * 根据id得到一条科普知识
	 * @param id
	 * @return
	 */
	public Knowledge getKnowledgeById(int id, String httpImageUrl, String localFileUrl){
//		String httpImageUrl = commonService.getCodeValue(PubCode.httpImageUrl);
//		String localFileUrl = commonService.getCodeValue(PubCode.localFileUrl);
		Knowledge knowledge =knowledgeDao.getKnowledgeById(id);
		knowledge.setImageName(httpImageUrl + knowledge.getImageName());
		knowledge.setContent((new ToolUtil()).readfile(localFileUrl + knowledge.getContent().toString()));
		return knowledge;
	}

	/**
	 * 根据标题，进行模糊查询
	 */
	public List<Knowledge> getKnowledgeByTitle(String title) {
		return knowledgeDao.getKnowledgeByTitle(title);
	}

	/**
	 * 保存科普知识
	 */
	public void save(String updateInfo, String addflag, String imageName, String textname, String knowledgeImage, String localFileUrl, Knowledge knowledge) {
		knowledgeDao.save(updateInfo, addflag, imageName, textname, knowledgeImage, localFileUrl, knowledge);
	}

}
