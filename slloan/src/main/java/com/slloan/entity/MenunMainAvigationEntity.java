package com.slloan.entity;

import java.io.Serializable;
/**
 * 菜单导航
 * @author asus-
 *
 */
public class MenunMainAvigationEntity implements Serializable{
	private Integer id;
	private String mainMenuID;
	private String menuName;
	private String url;
	public MenunMainAvigationEntity(Integer id, String mainMenuID, String menuName, String url) {
		super();
		this.id = id;
		this.mainMenuID = mainMenuID;
		this.menuName = menuName;
		this.url = url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMainMenuID() {
		return mainMenuID;
	}
	public void setMainMenuID(String mainMenuID) {
		this.mainMenuID = mainMenuID;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "MenunMainAvigationEntity [id=" + id + ", mainMenuID=" + mainMenuID + ", menuName=" + menuName + ", url="
				+ url + "]";
	}
}
