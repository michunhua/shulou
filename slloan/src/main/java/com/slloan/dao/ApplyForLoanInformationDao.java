package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.CoborrowerSpouse;


@Repository(value="applyforloaninformation")
public interface ApplyForLoanInformationDao {
	
	
	boolean save(ApplyForLoanInformation applyforl);
	
	ApplyForLoanInformation findById(int id); 
	
	boolean delete(int id); 
	
	boolean appUpdate(ApplyForLoanInformation applyForLoanInformation);  
	
	ApplyForLoanInformation SelectById(int id);
	
	List<ApplyForLoanInformation> findAll();  
}
