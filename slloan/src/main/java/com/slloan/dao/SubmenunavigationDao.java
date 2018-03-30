package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.SubmenunavigationEntity;


@Repository(value="submenunavigationdao")
public interface SubmenunavigationDao extends BaseDao<SubmenunavigationEntity>{
	public List<SubmenunavigationEntity> getMainMenuSubmenuid(String id);
}
