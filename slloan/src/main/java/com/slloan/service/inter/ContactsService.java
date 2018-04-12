package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.Contacts;



public interface ContactsService {
	
	
	
	boolean save(Contacts conta);
	
	Contacts findById(int id); 
	
	boolean delete(int id); 
	
	boolean update(Contacts contacts);  
	
	List<Contacts> findAll();  
	
	//查询ID
	Contacts SelectById(int id);
		
	//修改保存
	boolean updateadd (Contacts contacts);

}
