package com.slloan.entity;

public class NoteDescription {
	
	private Integer id;
	private String note_Description1;
	private String note_Description2;
	private String note_Description3;
	private String start;
	private String ctime;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNote_Description1() {
		return note_Description1;
	}
	public void setNote_Description1(String note_Description1) {
		this.note_Description1 = note_Description1;
	}
	public String getNote_Description2() {
		return note_Description2;
	}
	public void setNote_Description2(String note_Description2) {
		this.note_Description2 = note_Description2;
	}
	public String getNote_Description3() {
		return note_Description3;
	}
	public void setNote_Description3(String note_Description3) {
		this.note_Description3 = note_Description3;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	@Override
	public String toString() {
		return "NoteDescription [id=" + id + ", note_Description1=" + note_Description1 + ", note_Description2="
				+ note_Description2 + ", note_Description3=" + note_Description3 + ", start=" + start + ", ctime="
				+ ctime + "]";
	}
	public NoteDescription(String note_Description1,String start,
			String ctime) {
		super();
		this.note_Description1 = note_Description1;
		this.start = start;
		this.ctime = ctime;
	}
	
	public NoteDescription(String note_Description1, String note_Description2, String start,
			String ctime) {
		super();
		this.note_Description1 = note_Description1;
		this.note_Description2 = note_Description2;
		this.start = start;
		this.ctime = ctime;
	}
	
	public NoteDescription(String note_Description1, String note_Description2,String note_Description3, String start,
			String ctime) {
		super();
		this.note_Description1 = note_Description1;
		this.note_Description2 = note_Description2;
		this.note_Description2 = note_Description3;
		this.start = start;
		this.ctime = ctime;
	}
}
