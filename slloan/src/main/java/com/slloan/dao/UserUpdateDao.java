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
@Repository(value="userupdateuao")
public interface UserUpdateDao {
	 /**
 	  * 更改密码
 	  * @param username
 	  * @param password
 	  * @param newpassword
 	  * @return
 	  */
 	 public boolean updatePassWord(String userName,String passWord,String newpassWord);

}
