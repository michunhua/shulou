package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.districtDao;
import com.slloan.entity.UtilCity;
import com.slloan.service.inter.DistrictService;

@Service(value="districtImple")
public class DistrictImple implements DistrictService{

	@Autowired
	private districtDao disdao;
	
	@Override
	public List<UtilCity> getProvinces() {
		return disdao.getProvinces();
	}

	@Override
	public List<UtilCity> getCities(String province) {
		return disdao.getCities(province);
	}

}
