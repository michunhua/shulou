package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.PersonalProfile;
import com.slloan.entity.ResultList;

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
	 	
	 	/**
	 	 * 按揭员列表查询
	 	 * @param param
	 	 * @return
	 	 */
	 	List<PersonalProfile> vaguelikeSelectCreateone(Map<String,String> param);

	 	/**
	 	 * 初审列表查询
	 	 * @param param
	 	 * @return
	 	 */
	 	List<PersonalProfile> vaguelikeSelectCreatetwo(Map<Object,Object> param);
	 	
	 	List<PersonalProfile> vaguelikeSelectCreate(Map<Object,Object> param);

}
