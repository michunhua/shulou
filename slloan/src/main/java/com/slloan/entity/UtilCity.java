package com.slloan.entity;

import java.io.Serializable;

public class UtilCity {
	private Integer id;
	private String type;
	private String parent_id;
	private String name;
	private String shortt;
	
	public UtilCity(){}
	
	public UtilCity(Integer id, String type, String parent_id, String name, String shortt) {
		super();
		this.id = id;
		this.type = type;
		this.parent_id = parent_id;
		this.name = name;
		this.shortt = shortt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortt() {
		return shortt;
	}

	public void setShortt(String shortt) {
		this.shortt = shortt;
	}

	@Override
	public String toString() {
		return "UtilCity [id=" + id + ", type=" + type + ", parent_id=" + parent_id + ", name=" + name + ", shortt="
				+ shortt + "]";
	}
}
