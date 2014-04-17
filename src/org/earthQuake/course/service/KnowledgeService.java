package org.earthQuake.course.service;

import java.util.List;

import org.earthQuake.course.common.bean.Knowledge;

public interface KnowledgeService {

	/**
	 * 通过num得到一个科普知识对象
	 * @return
	 */
	public Knowledge getKnowledgeByNum(int num, String httpImageUrl, String localFileUrl);
	
	/**
	 * 得到所有关键字的科普知识列表
	 * @return
	 */
	public List<Knowledge> getKnowledgeKWList();
	
	/**
	 * 随机取得一条数据
	 * @return
	 */
	public Knowledge getKnowledge();
	
	/**
	 * 根据id得到一条科普知识
	 * @param id
	 * @return
	 */
	public Knowledge getKnowledgeById(int id, String httpImageUrl, String localFileUrl);
	
	/**
	 * 根据标题，进行模糊查询
	 * @param title
	 * @return
	 */
	public List<Knowledge> getKnowledgeByTitle(String title);
	/**
	 * 保存科普知识
	 */
	public void save(String updateInfo, String addflag, String imageName, String textname, String knowledgeImage, 
			String localFileUrl, Knowledge knowledge);
}
