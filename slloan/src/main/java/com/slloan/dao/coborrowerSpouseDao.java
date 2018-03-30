package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.CoborrowerSpouse;
import com.slloan.service.inter.ApplyForLoanInformationService;


@Repository(value="coborrowerspousedao")
public interface coborrowerSpouseDao {
	
	boolean save(CoborrowerSpouse coborrowerSpouse);
	
	CoborrowerSpouse findById(int id); 
	
	boolean delete(int id); 
	
	boolean update(CoborrowerSpouse coborrowerSpouse);  
	
	List<CoborrowerSpouse> findAll();   
}
