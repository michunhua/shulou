package com.slloan.entity;

import java.io.Serializable;
import java.util.List;

public class ResultList<T> {
	
	 private List<T> lists;//返回结果数据

	public List<T> getLists() {
		return lists;
	}

	public void setLists(List<T> lists) {
		this.lists = lists;
	}

	@Override
	public String toString() {
		return "[lists=" + lists + "]";
	}

}
