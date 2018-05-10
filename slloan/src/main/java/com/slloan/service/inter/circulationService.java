package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.circulation;

public interface circulationService {
	
	
	List<circulation> findById(circulation id); 
	
	boolean delete(int id); 
	
	boolean appUpdate(circulation circul);  
	
	circulation SelectById(int id);
	
	List<circulation> findAll();

	boolean save(circulation circul);  
	
	boolean save2(circulation circul); 
	
	List<circulation> findById2(circulation param); 
}
