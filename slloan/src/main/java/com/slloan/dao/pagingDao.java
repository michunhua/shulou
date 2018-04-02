package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.slloan.entity.Page;
import com.slloan.entity.UserRightsManagement;

@Repository(value="paging")
public interface pagingDao {
	public List<UserRightsManagement> queryUserInfo(Page page);//查询帖子列表
	public Integer searchTotalCount();//统计总数量
	public Integer deleteInvition(Integer id);//根据id删除帖子
	public boolean updateUserById(UserRightsManagement id);//修改
	public Object findUserById(Integer id);//根据ID查看返回对象
	//登录
		String Logindelu(Map<String, String> param);
}