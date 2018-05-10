package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.UserLogin;

/**
 * 用户列表服务层
 * @author Administrator
 *
 */
public interface UserService {
	/**
     * 列出所有记录
     *
     * @return
     */
    public List<UserLogin> list();
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
    public boolean deleteById(Integer id);

    /**
     * 更新一条记录
     *
     * @param t
     */
    public boolean updateaddUser(UserLogin  id);
    //更新查询
 	 public UserLogin updateselectId(Integer sno);
 	 
 	 /**
      * 根据Id登录用户名
      *
      * @param id
      * @return
      */
     public UserLogin selectById(String id);
     
     /***
  	  * 登录
  	  * @param param
  	  * @return
  	  */
  	 public UserLogin logindelu(UserLogin userlogin);
  	 
  	 /**
  	  * 更改密码
  	  * @param username
  	  * @param password
  	  * @param newpassword
  	  * @return
  	  */
  	 public boolean updatePassWord(String username,String oldpassword,String newpassword);

//  	 public Page queryUserInfo(Page pagefy);//分页
  	 
 	 
 	 public int getCount();//统计
 	 
 	/**
 	 * 根据角色名得到所属城市
 	 */
 	public AddRole accordingtoroleCity(AddRole param);
 	/**
     * 分页操作，调用getRolePage limit分页方法
     * @param map
     * @return
     */
 	Page<UserLogin> getUserByPage(int currentPage,String parentid);
 	/**
     * 分页操作，调用getRolePage limit分页方法
     * @param map
     * @return
     */
 	Page<UserLogin> getUserByPage(int currentPage);
 	
 	/**
     * 系统管理员分页操作，调用getRolePage limit分页方法
     * @param map
     * @return
     */
// 	Page<UserLogin> getUserByPage(int currentPage,String parentid);
 	
 	/**
     * 查询用户记录总数
     * @return
     */
	int selectCount();
	
	/**
     * 分页操作，调用getRolePage limit分页方法
     * @param map
     * @return
     */
//	Page<UserLogin> getRolePage(int currentPage);
	
	/**
     * 根据Id查询一条记录
     *
     * @param id
     * @return
     */
    public UserLogin selectUserById(int id);
    
    /**
     * 查询角色名在不在
     * @param map
     * @return
     */
    public UserLogin selectroleUserName(Map<Object,Object>map);
	
}
