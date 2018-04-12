package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.Contacts;


public interface ApplyForLoanInformationService {
	
	boolean save(ApplyForLoanInformation applyForLoanInformation);
	
	ApplyForLoanInformation findById(int id); 
	
	boolean delete(int id); 
	
	boolean appUpdate(ApplyForLoanInformation applyForLoanInformation);  
	
	List<ApplyForLoanInformation> findAll();

	boolean select(ApplyForLoanInformation ap);
	//查询ID
	ApplyForLoanInformation SelectById(int id);
	
}
