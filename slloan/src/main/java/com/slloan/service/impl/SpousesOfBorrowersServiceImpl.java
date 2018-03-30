package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.districtDao;
import com.slloan.dao.spousesOfBorrowersDao;
import com.slloan.entity.SpousesOfBorrowers;
import com.slloan.service.inter.SpousesOfBorrowersService;

@Service(value = "spousesofborrowersserviceimpl")
public class SpousesOfBorrowersServiceImpl implements SpousesOfBorrowersService {

	@Autowired
	private spousesOfBorrowersDao spousesOfBorrowersDao;

	@Override
	public boolean save(SpousesOfBorrowers spousesOfBorrowers) {

		String name = spousesOfBorrowers.getName(); // 借款人配偶姓名
		String id_Type = spousesOfBorrowers.getId_Type(); // 身份证件类型
		String id_Number = spousesOfBorrowers.getId_Number(); // 身份证件号码
		String uni_Name = spousesOfBorrowers.getUni_Name(); // 单位名称
		String unit_Phone = spousesOfBorrowers.getUnit_Phone(); // 单位电话
		String home_Phone = spousesOfBorrowers.getHome_Phone(); // 住宅电话
		String mobile_Phone = spousesOfBorrowers.getMobile_Phone(); // 手机
		String monthly_Income = spousesOfBorrowers.getMonthly_Income(); // 月收入
		String start = spousesOfBorrowers.getStart();
		String ctime = spousesOfBorrowers.getCtime();

		SpousesOfBorrowers spouses = new SpousesOfBorrowers(name, id_Type, id_Number, uni_Name, unit_Phone, home_Phone,
				mobile_Phone, monthly_Income,start,ctime);
		return spousesOfBorrowersDao.save(spouses);
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(SpousesOfBorrowers spousesOfBorrowers) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
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

}
