package com.slloan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.FinaljudgmentDao;
import com.slloan.service.inter.FinaljudgmentService;

@Service(value="finaljudgmentserviceimpl")
public class FinaljudgmentServiceImpl implements FinaljudgmentService{
	
	@Autowired
	private FinaljudgmentDao finaljudgmentdao;
	
	

}
