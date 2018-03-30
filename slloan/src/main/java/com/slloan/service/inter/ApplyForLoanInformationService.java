package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.ApplyForLoanInformation;


public interface ApplyForLoanInformationService {
	
	boolean save(ApplyForLoanInformation applyForLoanInformation);
	
	ApplyForLoanInformation findById(int id); 
	
	boolean delete(int id); 
	
	boolean update(ApplyForLoanInformation applyForLoanInformation);  
	
	List<ApplyForLoanInformation> findAll();

	boolean select(ApplyForLoanInformation ap);
	
}
