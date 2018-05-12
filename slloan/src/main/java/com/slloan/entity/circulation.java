package com.slloan.entity;

public class circulation {
	
	private Integer id;
	private String state;
	private String circulation;//描述
	private String createdata;
	private String username;//存入用户名
	private String ParentnodeId;//存入登录用户名ID
	private String city;//城市
	private String rolename;//角色名
	private String updatedata;
	private String spare;
	private String marked;
	public  circulation (){
		
	}

	public circulation(String state, String circulation, String createdata, String username, String parentnodeId,
			String city, String rolename) {
		super();
		this.state = state;
		this.circulation = circulation;
		this.createdata = createdata;
	}
	
	public circulation(String state, String circulation, String createdata, String updatedata,String spare) {
		super();
		this.state = state;
		this.circulation = circulation;
		this.createdata = createdata;
		this.updatedata = updatedata;
		this.spare = spare;
	}
//circulation [id=null, state=0, circulation=初审审批回退, createdata=aa, username=317, ParentnodeId=内蒙古, city=工程名, rolename=2018-05-02 11:35:35.503, updatedata=null]
	public circulation(String state,String username, String parentnodeId, String city, String rolename, String updatedata) {
		super();
		this.state = state;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
		this.updatedata = updatedata;
	}

	public circulation(String state, String circulation, String createdata, String username, String parentnodeId,
			String city, String rolename, String updatedata,String spare) {
		super();
		this.state = state;
		this.circulation = circulation;
		this.createdata = createdata;
		this.username = username;
		ParentnodeId = parentnodeId;
		this.city = city;
		this.rolename = rolename;
		this.updatedata = updatedata;
		this.spare = spare;
	}

	public String getMarked() {
		return marked;
	}

	public void setMarked(String marked) {
		this.marked = marked;
	}

	public String getSpare() {
		return spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

	public String getCreatedata() {
		return createdata;
	}

	public void setCreatedata(String createdata) {
		this.createdata = createdata;
	}

	public String getUpdatedata() {
		return updatedata;
	}

	public void setUpdatedata(String updatedata) {
		this.updatedata = updatedata;
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
		return "circulation [id=" + id + ", state=" + state + ", circulation=" + circulation + ", createdata="
				+ createdata + ", username=" + username + ", ParentnodeId=" + ParentnodeId + ", city=" + city
				+ ", rolename=" + rolename + ", updatedata=" + updatedata + ", spare=" + spare + "]";
	}

}
