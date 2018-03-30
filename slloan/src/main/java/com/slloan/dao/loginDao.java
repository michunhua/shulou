package com.slloan.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * 用户管理登录添加
 * @author Administrator
 *
 */

@Repository(value="logindao")
public interface loginDao {
	//登录
	boolean Logindelu(Map<String, String> param);
	
//	boolean addrole(Map<String, String> param);
}
