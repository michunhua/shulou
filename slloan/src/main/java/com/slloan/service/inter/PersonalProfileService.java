package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.AddRole;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.ResultList;
import com.slloan.entity.UserLogin;

public interface PersonalProfileService {

	boolean save(PersonalProfile personalProfile);

	boolean perupdate(PersonalProfile personalProfile);

	// 查询ID
	PersonalProfile SelectById(int id);
	
	//查询ID 身份证
			PersonalProfile SelectToById(int id , String id_number);

//			Page<PersonalProfile> getLoanPage(int currentPage);	
			
		 	public Page<PersonalProfile> getPersonalProfilePage(int currentPage);
		 	
		 	int getPersonalProfileCount();
		 	
		 	
		 	/**
		 	 * 按揭员模糊查询
		 	 * @param param
		 	 * @return
		 	 */
		 	List<PersonalProfile> vaguelikeSelectCreateone(Map<String,String> param);
		 	
		 	/**
		 	 * 初审模糊查询
		 	 * @param param
		 	 * @return
		 	 */
		 	List<PersonalProfile> vaguelikeSelectCreatetwo(Map<String,String> param);
		 	
		 	
}