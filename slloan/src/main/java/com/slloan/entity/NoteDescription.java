package com.slloan.entity;

public class NoteDescription {
	
	private Integer id;
	private String note_Description1;
	private String note_Description2;
	private String note_Description3;
	private String note_Description4;
	private String state;
	private String ctime;
	private String username;//存入用户名
	private String ParentnodeId;//存入登录用户名ID
	private String city;//城市
	private String rolename;//角色名
	private String submit;
	
	public NoteDescription()   {  
		  
	}  
	
	
	
//
//	public NoteDescription(String note_Description1, String note_Description2, String note_Description3,
//			String note_Description4, String state, String ctime) {
//		super();
//		this.id = id;
//		this.note_Description1 = note_Description1;
//		this.note_Description2 = note_Description2;
//		this.note_Description3 = note_Description3;
//		this.note_Description4 = note_Description4;
//		this.state = state;
//		this.ctime = ctime;
//		
//	}




	public NoteDescription(String note_Description1, String note_Description2, String note_Description3, String note_Description4,String state,
			String ctime, String username, String parentnodeId, String city, String rolename) {
		super();
		this.note_Description1 = note_Description1;
		this.note_Description2 = note_Description2;
		this.note_Description3 = note_Description3;
		this.note_Description4 = note_Description4;
		this.state = state;
		this.ctime = ctime;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
	
	}




	public NoteDescription(Integer id, String note_Description1, String note_Description2, String note_Description3,String note_Description4,
			String state, String ctime) {
		super();
		this.id = id;
		this.note_Description1 = note_Description1;
		this.note_Description2 = note_Description2;
		this.note_Description3 = note_Description3;
		this.note_Description4 = note_Description4;
		this.state = state;
		this.ctime = ctime;
	}
	
	public NoteDescription(Integer id, String note_Description1, String note_Description2, String note_Description3,String note_Description4,
			String state, String ctime, String username, String rolename,
			String parentnodeId, String city) {
		super();
		this.id = id;
		this.note_Description1 = note_Description1;
		this.note_Description2 = note_Description2;
		this.note_Description3 = note_Description3;
		this.note_Description4 = note_Description4;
		this.state = state;
		this.ctime = ctime;
		this.username = username;
		this.rolename = rolename;
		this.ParentnodeId = parentnodeId;
		this.city = city;
	}
	
	
	public NoteDescription(String note_Description1, String state, String ctime, String username, String parentnodeId,
			String city, String rolename) {
		super();
		this.note_Description1 = note_Description1;
		this.state = state;
		this.ctime = ctime;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
	
	}


	public String getNote_Description4() {
		return note_Description4;
	}




	public void setNote_Description4(String note_Description4) {
		this.note_Description4 = note_Description4;
	}




	public String getSubmit() {
		return submit;
	}
	public void setSubmit(String submit) {
		this.submit = submit;
	}
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
	public String getstate() {
		return state;
	}
	public void setstate(String state) {
		this.state = state;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getParentnodeId() {
		return ParentnodeId;
	}
	public void setParentnodeId(String parentnodeId) {
		ParentnodeId = parentnodeId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	


	@Override
	public String toString() {
		return "NoteDescription [id=" + id + ", note_Description1=" + note_Description1 + ", note_Description2="
				+ note_Description2 + ", note_Description3=" + note_Description3 + ", state=" + state + ", ctime="
				+ ctime + ", username=" + username + ", ParentnodeId=" + ParentnodeId + ", city=" + city + ", rolename="
				+ rolename + ", submit=" + submit + "]";
	}
	
	
	
}
