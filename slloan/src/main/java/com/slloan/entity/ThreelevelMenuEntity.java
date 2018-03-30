package com.slloan.entity;

import java.io.Serializable;
/**
 * 菜单三级导航
 * @author asus-
 *
 */
public class ThreelevelMenuEntity implements Serializable{
	private Integer id;
	private String threeSubmenuid;
	private String menuname;
	private String url;
	private String fatherid;
	public ThreelevelMenuEntity(Integer id, String threeSubmenuid, String menuname, String url, String fatherid) {
		super();
		this.id = id;
		this.threeSubmenuid = threeSubmenuid;
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
	public String getThreeSubmenuid() {
		return threeSubmenuid;
	}
	public void setThreeSubmenuid(String threeSubmenuid) {
		this.threeSubmenuid = threeSubmenuid;
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
//		this.fatherid = fatherid;
		this.fatherid = fatherid == null ? null:fatherid.trim();
	}
	@Override
	public String toString() {
		return "ThreelevelMenuEntity [id=" + id + ", threeSubmenuid=" + threeSubmenuid + ", menuname=" + menuname
				+ ", url=" + url + ", fatherid=" + fatherid + "]";
	}

}
