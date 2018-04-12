package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.UserLogin;

public interface PersonalProfileService {

	boolean save(PersonalProfile personalProfile);
	
	boolean perupdate(PersonalProfile personalProfile);
	
	boolean delete(int id); 
	
	
	
	//查询ID
	PersonalProfile SelectById(int id);
	
	//分页
	
	Page<PersonalProfile> getRolesPage(int currentPage);
		
}
