package com.slloan.dao;

import java.util.List;
import java.util.Map;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.circulation;

public interface circulationDao  {
	
	boolean save(circulation circul);
	boolean save2(circulation circul);
	
	List<circulation> findById(Map<String,Object>param); 
	
	boolean delete(int id); 
	
	boolean appUpdate(circulation circul);  
	
	circulation SelectById(int id);
	
	List<circulation> findAll();


}
