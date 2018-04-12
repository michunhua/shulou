package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.AddRole;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;

public interface JointApplicantService {

	
	boolean save(JointApplicant jointApplicant);
	
	boolean update(JointApplicant jointApplicant);
	
	boolean delete(int id); 
	
	JointApplicant findById(int id);
	
	List<JointApplicant> findAll();
	
	/**
     * 分页操作，调用getRolePage limit分页方法
     * @param map
     * @return
     */
	Page<JointApplicant> getRolePage(int currentPage);
	
	/**
     * 查询用户记录总数
     * @return
     */
	int selectCount();

	JointApplicant SelectById(int reqid);
}

