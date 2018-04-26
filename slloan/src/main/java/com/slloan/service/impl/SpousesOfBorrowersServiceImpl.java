package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.districtDao;
import com.slloan.dao.spousesOfBorrowersDao;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.Contacts;
import com.slloan.entity.SpousesOfBorrowers;
import com.slloan.service.inter.SpousesOfBorrowersService;

@Service(value = "spousesofborrowersserviceimpl")
public class SpousesOfBorrowersServiceImpl implements SpousesOfBorrowersService {

	@Autowired
	private spousesOfBorrowersDao spousesOfBorrowersDao;

	@Override
	public boolean save(SpousesOfBorrowers spousesOfBorrowers) {

		 String name =spousesOfBorrowers.getName();						//金额
		 String id_Type=spousesOfBorrowers.getId_Type();					//期限
		 String id_Other = spousesOfBorrowers.getId_Other();
		 String id_Number=spousesOfBorrowers.getId_Number();    		//借款品种
		 String uni_Name=spousesOfBorrowers.getUni_Name();					//还款方式
		 String unit_Phone=spousesOfBorrowers.getUnit_Phone();  		//收款银行名称
		 String home_Phone=spousesOfBorrowers.getHome_Phone();	    //收款账户名
		 String mobile_Phone=spousesOfBorrowers.getMobile_Phone();    		//收款账号
		 double monthly_Income=spousesOfBorrowers.getMonthly_Income();  		//还款银行名称
		 String state = spousesOfBorrowers.getState();
		 String ctime = spousesOfBorrowers.getCtime();

		SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type,id_Other, id_Number, uni_Name,
				unit_Phone, home_Phone, mobile_Phone, monthly_Income,state,ctime );
		
		return spousesOfBorrowersDao.save(spouses);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean spoupdate(SpousesOfBorrowers spousesOfBorrowers) {
	
		return spousesOfBorrowersDao.spoupdate(spousesOfBorrowers);
	}

	@Override
	public boolean delete(int id) {
		boolean isResult = spousesOfBorrowersDao.delete(id);
		if (isResult == true) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public SpousesOfBorrowers findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpousesOfBorrowers> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public SpousesOfBorrowers SelectById(int id) { 
		
		return spousesOfBorrowersDao.SelectById(id);
	}

}
