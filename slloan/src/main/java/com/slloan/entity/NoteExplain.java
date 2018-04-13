package com.slloan.entity;

import java.io.Serializable;

/**
 * 备注说明
 * @author Administrator
 *
 */
public class NoteExplain implements Serializable{

	private int id;
	private String recordSingleNote;//录单备注
	private String firstTrialNote;//初审备注
	private String city;
	private String parentnode;
	private String createdate;
	

	public NoteExplain(String recordSingleNote, String firstTrialNote, String city, 
			String createdate) {
		super();
		this.recordSingleNote = recordSingleNote;
		this.firstTrialNote = firstTrialNote;
		this.city = city;
		this.createdate = createdate;
	}



	public NoteExplain(int id, String city) {
		super();
		this.id = id;
		this.city = city;
	}



	public NoteExplain(String recordSingleNote, String firstTrialNote, String city, String parentnode,
			String createdate) {
		super();
		this.recordSingleNote = recordSingleNote;
		this.firstTrialNote = firstTrialNote;
		this.city = city;
		this.parentnode = parentnode;
		this.createdate = createdate;
	}

	public NoteExplain(int id, String recordSingleNote, String firstTrialNote, String city, String parentnode,
			String createdate) {
		super();
		this.id = id;
		this.recordSingleNote = recordSingleNote;
		this.firstTrialNote = firstTrialNote;
		this.city = city;
		this.parentnode = parentnode;
		this.createdate = createdate;
	}



	public String getCreatedate() {
		return createdate;
	}


	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}


	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getParentnode() {
		return parentnode;
	}

	public void setParentnode(String parentnode) {
		this.parentnode = parentnode;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getRecordSingleNote() {
		return recordSingleNote;
	}

	public void setRecordSingleNote(String recordSingleNote) {
		this.recordSingleNote = recordSingleNote;
	}

	public String getFirstTrialNote() {
		return firstTrialNote;
	}

	public void setFirstTrialNote(String firstTrialNote) {
		this.firstTrialNote = firstTrialNote;
	}


	@Override
	public String toString() {
		return "NoteExplain [id=" + id + ", recordSingleNote=" + recordSingleNote + ", firstTrialNote=" + firstTrialNote
				+ "]";
	}
	
}
