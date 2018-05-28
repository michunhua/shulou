package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.Contacts;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;


public interface ApplyForLoanInformationService {
	
	boolean save(ApplyForLoanInformation applyForLoanInformation);
	
	ApplyForLoanInformation findById(int id); 
	
//	ApplyForLoanInformation SelectByIdApp(int id);
	
	boolean delete(int id); 
	
	boolean appUpdate(ApplyForLoanInformation applyForLoanInformation);  
	


	boolean select(ApplyForLoanInformation ap);
	//查询ID
	ApplyForLoanInformation SelectById(int id);

	ApplyForLoanInformation SelectByIdApp(int id);
	
	ApplyForLoanInformation SelectByIdPro(int state);

}
