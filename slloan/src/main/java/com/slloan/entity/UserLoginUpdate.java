package com.slloan.entity;

import java.io.Serializable;

/**
 * 添加用户
 * @author Administrator
 *
 */
		
public class UserLoginUpdate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;//用户名
	private String passWord;//密码
	private String newpassWord;//新密码
	public UserLoginUpdate(){
		
	}
	
	public UserLoginUpdate( String userName, String passWord, String newpassWord) {
		super();
		this.userName = userName;
		this.passWord = passWord;
		this.newpassWord = newpassWord;
	}

	public String getNewpassWord() {
		return newpassWord;
	}

	public void setNewpassWord(String newpassWord) {
		this.newpassWord = newpassWord;
	}

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "UserLoginUpdate [id=" + id + ", userName=" + userName + ", passWord=" + passWord + ", newpassWord="
				+ newpassWord + "]";
	}
	
}
