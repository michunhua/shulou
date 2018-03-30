package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ThreelevelMenuEntity;

@Repository(value="threelevelMenuDao")
public interface ThreelevelMenuDao extends BaseDao<ThreelevelMenuEntity>{
	
	public List<ThreelevelMenuEntity> getSecondMenuid(String id);
}
