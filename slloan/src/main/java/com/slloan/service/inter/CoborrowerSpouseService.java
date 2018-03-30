package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.CoborrowerSpouse;

public interface CoborrowerSpouseService {
	
	boolean save(CoborrowerSpouse conorowerSpouse);
	
	boolean update(CoborrowerSpouse coborrowerSpouse);
	
	boolean delete(int id); 
	
	CoborrowerSpouse findById(int id);
	
	List<CoborrowerSpouse> findAll();
}
