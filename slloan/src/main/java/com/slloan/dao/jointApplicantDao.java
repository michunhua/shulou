package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.JointApplicant;

@Repository(value = "jointapplicantdao")
public interface jointApplicantDao {

	 boolean save(JointApplicant jointApplicant);
	

	JointApplicant findById(int id);

	boolean delete(int id);

	boolean update(JointApplicant jointApplicant);

	List<JointApplicant> findAll();
}
