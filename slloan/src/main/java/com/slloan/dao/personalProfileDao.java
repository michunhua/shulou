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

		//查询ID
		PersonalProfile SelectToById(int id , String id_number);

		/**
	     * 分页操作，调用findByPage limit分页方法
	     * @param map
	     * @return
	     */
	 	public List<PersonalProfile> getPersonalProfilePage(Map<String,Object> map);
	 	
	 	/**
	     * 查询用户记录总数
	     * @return
	     */
	 	int getPersonalProfileCount();

	 	
	


}
