package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.UserLogin;

@Repository(value="personalprofiledao")
public interface personalProfileDao{

	
	boolean save(PersonalProfile personalProfile);
	
	PersonalProfile findById(int id); 
		
		boolean delete(int id); 
		

		
		List<PersonalProfile> findAll();

		boolean perupdate(PersonalProfile personalProfile);
	
		//查询ID
		PersonalProfile SelectById(int id);

	
		

}
