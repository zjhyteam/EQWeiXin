package org.earthQuake.course.service;

import java.util.List;

import org.earthQuake.course.common.bean.GroupSend;

/**
 * 群发接口
 * @author 徐晓亮
 *
 */
public interface GroupSendService {

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
