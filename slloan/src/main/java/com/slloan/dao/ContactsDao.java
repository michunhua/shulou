package com.slloan.dao;

import java.util.List;
import java.util.Map;

import com.slloan.entity.Contacts;
import com.slloan.entity.NoteExplain;


public interface ContactsDao {
	
	//添加
	boolean save(Contacts contacts);
	
	Contacts findById(int id); 
	
	boolean delete(int id); 
	
	boolean updateupdate(Contacts contacts);  
	
	List<Contacts> findAll();  
	
	//查询ID
	Contacts SelectById(int id);
	
	//修改保存
	boolean updateadd (Contacts contacts);
	
	/*
	 * 查询按揭员&初审的备注
	 */
	NoteExplain SelectBynote(Map<String,Object> param);
}	
