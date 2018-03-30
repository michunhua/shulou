package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.PropertyInformation;

@Repository(value="propertyinformationdao")
public interface propertyInformationDao {
	
	

		boolean save(PropertyInformation propertyInformation);
	
		PropertyInformation findById(int id); 
		
		boolean delete(int id); 
		
		boolean update(PropertyInformation propertyInformation);  
		
		List<PropertyInformation> findAll();
}
