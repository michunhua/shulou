package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.PropertyInformation;

@Repository(value="propertyinformationdao")
public interface propertyInformationDao {
	
	

		boolean save(PropertyInformation propertyInformation);
	
		PropertyInformation findById(int id); 
		
		boolean delete(int id); 
		
		boolean proupdate(PropertyInformation propertyInformation);  
		
		List<PropertyInformation> findAll();
		
		//查询ID
		PropertyInformation SelectById(int id);
		
		PropertyInformation SelectByIdPro(int id);
}
