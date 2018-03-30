package com.slloan.service.inter;

import com.slloan.entity.AddRole;
import com.slloan.entity.UserLogin;

public interface LoginService {
	
	//添加用户
	boolean Logindelu(UserLogin user);
	
	//添加角色与分配角色
//	boolean addRole(AddRole addrole);
}
