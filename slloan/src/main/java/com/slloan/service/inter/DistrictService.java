package com.slloan.service.inter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.UtilCity;

public interface DistrictService {
	
	public List<UtilCity> getProvinces();
	public List<UtilCity>getCities(String province);
}
