package com.slloan.entity;

import java.io.Serializable;

public class ObjectSeq implements Serializable{
	private String name;
	private int start_value;
	private int increment_value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getStart_value() {
		return start_value;
	}
	public void setStart_value(int start_value) {
		this.start_value = start_value;
	}
	public int getIncrement_value() {
		return increment_value;
	}
	public void setIncrement_value(int increment_value) {
		this.increment_value = increment_value;
	}
	@Override
	public String toString() {
		return "ObjectSeq [name=" + name + ", start_value=" + start_value + ", increment_value=" + increment_value
				+ "]";
	}
}
