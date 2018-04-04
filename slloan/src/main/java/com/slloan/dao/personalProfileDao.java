package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.JointApplicant;
import com.slloan.entity.PersonalProfile;

@Repository(value="personalprofiledao")
public interface personalProfileDao {

	
	boolean save(PersonalProfile personalProfile);
	
	PersonalProfile findById(int id); 
		
		boolean delete(int id); 
		
		
		
		List<PersonalProfile> findAll();

		boolean update(PersonalProfile personalProfile);
}
