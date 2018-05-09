package com.slloan.entity;

public class circulation {
	
	private Integer id;
	private String state;
	private String circulation;
	private String ctime;
	private String endTime;
	private String username;//存入用户名
	private String ParentnodeId;//存入登录用户名ID
	private String city;//城市
	private String rolename;//角色名
	public  circulation (){
		
	}
	
	public circulation(String state, String circulation, String ctime,  String username,
			String parentnodeId, String city, String rolename) {
		super();
		this.state = state;
		this.circulation = circulation;
		this.ctime = ctime;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
	}
	@Override
	public String toString() {
		return "circulation [id=" + id + ", state=" + state + ", circulation=" + circulation + ", ctime=" + ctime
				+ ", endTime=" + endTime + ", username=" + username + ", ParentnodeId=" + ParentnodeId + ", city="
				+ city + ", rolename=" + rolename + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCirculation() {
		return circulation;
	}
	public void setCirculation(String circulation) {
		this.circulation = circulation;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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

	public void setUpdatedata(String updatedate) {
		// TODO Auto-generated method stub
		
	}
	
	
}
