package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.UtilCity;

@Repository(value="districtdao")
public interface districtDao{
	
	public List<UtilCity> getProvinces();
	
	public List<UtilCity>getCities(String province);
	
	
}
