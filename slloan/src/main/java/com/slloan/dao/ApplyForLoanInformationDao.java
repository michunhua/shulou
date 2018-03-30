package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ApplyForLoanInformation;


@Repository(value="applyforloaninformation")
public interface ApplyForLoanInformationDao {
	
	
	boolean save(ApplyForLoanInformation applyforl);
	
	ApplyForLoanInformation findById(int id); 
	
	boolean delete(int id); 
	
	boolean update(ApplyForLoanInformation applyForLoanInformation);  
	
	List<ApplyForLoanInformation> findAll();  
}
