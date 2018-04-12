package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.coborrowerSpouseDao;
import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.service.inter.CoborrowerSpouseService;

@Service(value = "CoborrowerSpouseServiceImpl")
public class CoborrowerSpouseServiceImpl implements CoborrowerSpouseService {

	@Autowired
	private coborrowerSpouseDao coborrowerSpouseDao;

	
	
	@Override
	public boolean save(CoborrowerSpouse coborrowerSpouse) {
		 String name =coborrowerSpouse.getName();						//金额
		 String id_Type=coborrowerSpouse.getId_Type();					//期限
		 String id_Number=coborrowerSpouse.getId_Number();    		//借款品种
		 String uni_Name=coborrowerSpouse.getUni_Name();					//还款方式
		 String unit_Phone=coborrowerSpouse.getUnit_Phone();  		//收款银行名称
		 String home_Phone=coborrowerSpouse.getHome_Phone();	    //收款账户名
		 String mobile_Phone=coborrowerSpouse.getMobile_Phone();    		//收款账号
		 String monthly_Income=coborrowerSpouse.getMonthly_Income();  		//还款银行名称
		 String start = coborrowerSpouse.getStart();
		 String ctime = coborrowerSpouse.getCtime();
		
		 CoborrowerSpouse coborrow = new CoborrowerSpouse(name,id_Type,id_Number,uni_Name,unit_Phone,home_Phone,mobile_Phone,monthly_Income,start,ctime);
		return coborrowerSpouseDao.save(coborrow);
			
		
	}
	@Override
	public boolean update(CoborrowerSpouse coborrowerSpouse) {
		
		 return coborrowerSpouseDao.cobupdate(coborrowerSpouse);	
		
	}

	@Override
	public boolean delete(int id) {
		boolean	isResult = 	coborrowerSpouseDao.delete(id);
		if(isResult == true){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public CoborrowerSpouse findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CoborrowerSpouse> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public CoborrowerSpouse SelectById(int id) {
		// TODO Auto-generated method stub
		return coborrowerSpouseDao.SelectById(id);
	}
	

	

}
