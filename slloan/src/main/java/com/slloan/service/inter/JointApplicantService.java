package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.JointApplicant;

public interface JointApplicantService {

	
	boolean save(JointApplicant jointApplicant);
	
	boolean update(JointApplicant jointApplicant);
	
	boolean delete(int id); 
	
	JointApplicant findById(int id);
	
	List<JointApplicant> findAll();
}
