package com.slloan.entity;

import java.io.Serializable;

/**
 * 权限
 * @author Administrator
 *
 */
public class permission implements Serializable{
	private Integer id;
	private String permissionname;//增删改查
	private String role_id;//角色ID
	private String url;//地址
	private String name;//名称
	private String checkboxID;//名称
	public permission(){
		
	}
	
	
	public permission(String role_id, String url, String checkboxID) {
		super();
		this.role_id = role_id;
		this.url = url;
		this.checkboxID = checkboxID;
	}


	public permission(Integer id, String permissionname, String role_id, String url, String name, String checkboxID) {
		super();
		this.id = id;
		this.permissionname = permissionname;
		this.role_id = role_id;
		this.url = url;
		this.name = name;
		this.checkboxID = checkboxID;
	}


	public String getCheckboxID() {
		return checkboxID;
	}
	public void setCheckboxID(String checkboxID) {
		this.checkboxID = checkboxID;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPermissionname() {
		return permissionname;
	}
	public void setPermissionname(String permissionname) {
		this.permissionname = permissionname;
	}
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "permission [id=" + id + ", permissionname=" + permissionname + ", role_id=" + role_id + ", url=" + url
				+ ", name=" + name + ", checkboxID=" + checkboxID + "]";
	}
	
}
