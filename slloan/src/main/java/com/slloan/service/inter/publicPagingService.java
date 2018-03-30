package com.slloan.service.inter;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.slloan.entity.Page;
import com.slloan.entity.UserRightsManagement;


/**
 * 用户列表分页
 * @author Administrator
 *
 */
public interface publicPagingService {
	public Integer searchTotalCount();//统计总条数
	public List<UserRightsManagement> searchInvList(Page page);//分页查询
	public Integer deleteUserId(Integer id);//根据id删除帖子
	public boolean updateUserById(UserRightsManagement id);//修改
	public Object findUserById(Integer id);//根据ID查看返回对象
	
}
