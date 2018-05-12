package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.AddRole;
import com.slloan.entity.UserLogin;

/***
 * 用户列表dao
 * @author Administrator
 *
 */
@Repository(value="userdao")
public interface UserDao extends BaseDao<UserLogin>{
	 /**
     * 插入一条记录
     *
     * @param t
     */
    public boolean addUser(UserLogin t);

    /**
     * 根据Id删除一条记录
     *
     * @param id
     */
    public boolean deleteId(Integer id);

    /**
     * 更新一条记录
     *
     * @param t
     */
    public boolean updateUser(UserLogin id);
    /**
     * 更新查询
     *
     * @param t
     */
  	 public UserLogin updateselectId(Integer sno);
  	 
  	 /***
  	  * 登录
  	  * @param param
  	  * @return
  	  */
  	 public UserLogin logindelu(Map<String,String> param);
  	 
  	 /**
  	  * 更改密码
  	  * @param username
  	  * @param password
  	  * @param newpassword
  	  * @return
  	  */
  	 public boolean updatePassWord(String username,String passWord,String newpassword);

  	 /**
  	  * 分页
  	  * @param param 参数
  	  * @return
  	  */
 	public List<UserLogin> getUserByPage(Map<String,Object>param);
 	 /**
 	  * 分页
 	  * @param param 参数
 	  * @return
 	  */
	public List<UserLogin> getUserByPage2(Map<String,Object>param);
 	
  	 
 	/**
 	 * 统计
 	 * @return
 	 */
  	 public int getCount();//统计
  	public int getCount2(Integer spare);//统计
  	 
  	/**
	     * 分页操作，调用findByPage limit分页方法
	     * @param map
	     * @return
	     */
	 	public List<UserLogin> getRolePage(Map<String,Object> map);
	 	/**
	     * 查询用户记录总数
	     * @return
	     */
	 	int getRoleCount();
	 	
	 	/**
	     * 根据Id查询一条记录
	     *
	     * @param id
	     * @return
	     */
	    public UserLogin selectUserById(int id);
	    public List<UserLogin> selectUserById2(int id);
	    
	    /**
	     * 查询角色名在不在
	     * @param map
	     * @return
	     */
	    public UserLogin selectroleUserName(Map<Object,Object>map);
	    
	    public boolean updateUserCity (Map<String,Object>map);
}
