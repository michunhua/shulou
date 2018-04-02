package com.slloan.entity;

import java.io.Serializable;
/**
 * 二菜单子导航
 * @author asus-
 *
 */
public class SubmenunavigationEntity implements Serializable{
	private Integer id;
	private String submenuid;
	private String menuname;
	private String url;
	private String fatherid;
	public SubmenunavigationEntity(Integer id, String submenuid, String menuname, String url, String fatherid) {
		super();
		this.id = id;
		submenuid = submenuid;
		this.menuname = menuname;
		this.url = url;
		this.fatherid = fatherid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSubmenuid() {
		return submenuid;
	}
	public void setSubmenuid(String submenuid) {
		submenuid = submenuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getFatherid() {
		return fatherid;
	}
	public void setFatherid(String fatherid) {
		this.fatherid = fatherid == null ? null:fatherid.trim();
	}
	@Override
	public String toString() {
		return "SubmenunavigationEntity [id=" + id + ", Submenuid=" + submenuid + ", menuname=" + menuname + ", url="
				+ url + ", fatherid=" + fatherid + "]";
	}
	
}
