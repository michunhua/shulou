package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.UserLogin;

@Repository(value = "jointapplicantdao")
public interface jointApplicantDao {

	boolean save(JointApplicant jointApplicant);

	JointApplicant findById(int id);

	boolean delete(int id);

	boolean update(JointApplicant jointApplicant);

	List<JointApplicant> findAll();

	 /**
 	  * 分页
 	  * @param param 参数
 	  * @return
 	  */
	public List<JointApplicant> getUserByPage(Map<String,Object>param);
 	 
	/**
	 * 统计
	 * @return
	 */
 	 public int getCount();//统计
 	 
 	 
 	 /**
 	  * 查询ID
 	  * @param reqid
 	  * @return
 	  */
 	JointApplicant SelectById(int id);
}
