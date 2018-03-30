package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.PersonalProfile;

public interface PersonalProfileService {

	boolean save(PersonalProfile personalProfile);
	
	boolean update(PersonalProfile personalProfile);
	
	boolean delete(int id); 
	
	PersonalProfile findById(int id);
	
	List<PersonalProfile> findAll();
}
