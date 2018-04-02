package com.slloan.entity;

import java.io.Serializable;

/**
 * 系统菜单拥有什么权限
 * @author Administrator
 *
 */
public class Menu implements Serializable{
	private Integer id;
	private String parent_id;//父ID
	private String url;//地址
	private String method;//请求方式
	private String name;//栏目名称
	private String orderline;//排序值

	public Menu(){
		
	}

	public Menu(Integer id, String parent_id, String url, String method, String name, String orderline) {
		super();
		this.id = id;
		this.parent_id = parent_id;
		this.url = url;
		this.method = method;
		this.name = name;
		this.orderline = orderline;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getParent_id() {
		return parent_id;
	}

	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrderline() {
		return orderline;
	}

	public void setOrderline(String orderline) {
		this.orderline = orderline;
	}

	@Override
	public String toString() {
		return "Menu [id=" + id + ", parent_id=" + parent_id + ", url=" + url + ", method=" + method + ", name=" + name
				+ ", orderline=" + orderline + "]";
	}
	
}
