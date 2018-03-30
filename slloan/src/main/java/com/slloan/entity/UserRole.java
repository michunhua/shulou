package com.slloan.entity;

import java.io.Serializable;

/**
 * 用户角色表
 * @author Administrator
 *
 */
public class UserRole implements Serializable{

	private Integer id;
	private String user_id;//用户ID
	private String role_id;//角色ID
	public UserRole(){
		
	}
	public UserRole(Integer id, String user_id, String role_id) {
		super();
		this.id = id;
		this.user_id = user_id;
		this.role_id = role_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	@Override
	public String toString() {
		return "UserRole [id=" + id + ", user_id=" + user_id + ", role_id=" + role_id + "]";
	}
	
}
