package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slloan.dao.pagingDao;
import com.slloan.entity.Page;
import com.slloan.entity.UserRightsManagement;
import com.slloan.service.inter.publicPagingService;

/***
 * 添加用户权限管理&用户列表服务层
 * @author Administrator
 *
 */
@Service("usermanagement")
@Transactional
public class UserManagement implements publicPagingService{

	 @Autowired
	   public pagingDao userInfoMapper;

	@Override
	public Integer searchTotalCount() {
		return userInfoMapper.searchTotalCount();
	}

	@Override
	public List<UserRightsManagement> searchInvList(Page page) {
		return userInfoMapper.queryUserInfo(page);
	}

	@Override
	public Integer deleteUserId(Integer id) {
		return userInfoMapper.deleteInvition(id);
	}

	@Override
	public boolean updateUserById(UserRightsManagement id) {
		return userInfoMapper.updateUserById(id);
	}

	@Override
	public Object findUserById(Integer id) {
		return userInfoMapper.findUserById(id);
	}
	 
	 

}
