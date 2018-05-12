package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.NoteDescription;

public interface CoborrowerSpouseService {
	
	boolean save(CoborrowerSpouse coborrow);
	
	boolean update(CoborrowerSpouse coborrowerSpouse);
	
	boolean delete(int id); 
	
	CoborrowerSpouse findById(int id);
	
	List<CoborrowerSpouse> findAll();
	
	//查询ID
	CoborrowerSpouse SelectById(int state);
}
