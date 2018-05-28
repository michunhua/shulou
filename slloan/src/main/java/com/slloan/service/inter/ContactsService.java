package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.Contacts;
import com.slloan.entity.NoteExplain;



public interface ContactsService {
	
	
	
	boolean save(Contacts conta);
	
	Contacts findById(int id); 
	
	boolean delete(int id); 
	
	boolean update(Contacts contacts);  
	
	List<Contacts> findAll();  
	
	Contacts SelectByIdCon(int id);
	
	//查询ID
	Contacts SelectById(int id);
		
	//修改保存
	boolean updateadd (Contacts contacts);

	/*
	 * 查询按揭员&初审的备注
	 */
	NoteExplain SelectBynote(NoteExplain param);
	
	/**
	 * 共用的备注插入
	 */
	boolean noteInsert(NoteExplain note);
	

	/**
	 * 贷款初审备注
	 * @param noteexplain
	 * @return
	 */
	public boolean firstTrial(NoteExplain noteexplain);
}
