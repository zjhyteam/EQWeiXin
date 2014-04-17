package org.earthQuake.course.serviceImpl;

import java.util.List;

import org.earthQuake.course.common.bean.GroupSend;
import org.earthQuake.course.dao.GroupSendDao;
import org.earthQuake.course.service.GroupSendService;

public class GroupSendServiceImpl implements GroupSendService{

	private GroupSendDao groupSendDao;
	
	public GroupSendDao getGroupSendDao() {
		return groupSendDao;
	}

	public void setGroupSendDao(GroupSendDao groupSendDao) {
		this.groupSendDao = groupSendDao;
	}

	@Override
	public GroupSend saveGroupSend(GroupSend groupSend) {
		return groupSendDao.saveGroupSend(groupSend);
	}

	@Override
	public GroupSend getGroupSendById(String id, String httpUrl) {
		return groupSendDao.getGroupSendById(id, httpUrl);
	}

	@Override
	public List query(int page, int limit) {
		return groupSendDao.query(page, limit);
	}

}
