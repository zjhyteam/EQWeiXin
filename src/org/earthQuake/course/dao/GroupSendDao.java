package org.earthQuake.course.dao;

import java.util.List;

import org.earthQuake.course.common.bean.GroupSend;

/**
 * 短信群发类
 * @author 徐晓亮
 *
 */
public interface GroupSendDao {

	/**
	 * 短信群发信息保存到数据库
	 * @return
	 */
	public GroupSend saveGroupSend(GroupSend groupSend);
	
	/**
	 * 得到群发信息的id
	 * @param id
	 * @return
	 */
	public GroupSend getGroupSendById(String id, String httpUrl);
	
	/**
	 * 查询发送信息
	 * @return
	 */
	public List query(int page, int limit);
}
