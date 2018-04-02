package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.MenunMainAvigationEntity;
import com.slloan.entity.UtilCity;

/**
 * 菜单导航Dao
 * @author asus-
 *
 */
@Repository(value="menunmainavigationdao")
public interface MenunMainAvigationDao extends BaseDao<MenunMainAvigationEntity> {
	
//	public List<MenunMainAvigationEntity> getProvinces();
	
//	public List<MenunMainAvigationEntity>getCities(String province);
}
