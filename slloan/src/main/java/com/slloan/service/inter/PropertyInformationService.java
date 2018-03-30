package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.PropertyInformation;

public interface PropertyInformationService {
	
	boolean save(PropertyInformation jointApplicant);
	
	boolean update(PropertyInformation jointApplicant);
	
	boolean delete(int id); 
	
	PropertyInformation findById(int id);
	
	List<PropertyInformation> findAll();
}
