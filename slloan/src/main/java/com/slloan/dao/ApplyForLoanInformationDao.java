package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.PersonalProfile;


@Repository(value="applyforloaninformation")
public interface ApplyForLoanInformationDao {
	
	
	boolean save(ApplyForLoanInformation applyforl);
	
	ApplyForLoanInformation findById(int id); 
//	
//	ApplyForLoanInformation SelectByIdApp(int id);
	
	boolean delete(int id); 
	
	boolean appUpdate(ApplyForLoanInformation applyForLoanInformation);  
	
	ApplyForLoanInformation SelectById(int id);
	
	ApplyForLoanInformation SelectByIdPro(int state);

	ApplyForLoanInformation SelectByIdApp(int id);
}
