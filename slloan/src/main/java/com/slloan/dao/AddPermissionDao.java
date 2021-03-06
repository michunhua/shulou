package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.AddRole;
import com.slloan.entity.PermissionEntity;

/**
 * 权限添加
 * @author Administrator
 *
 */

@Repository(value="addpermissiondao")
public interface AddPermissionDao extends BaseDao<PermissionEntity>{

	public boolean addPermission(PermissionEntity permission);
	
	boolean batchDeletes(List delList);//批量删除
	
	public List<PermissionEntity>  getRolePermissiondata(Map<String,String>param);//得到角色权限数据
	
	public boolean updatePermissionadd(PermissionEntity updateadd);//修改保存
	
	  
    /**
     * 批量删除权限表权限
     * @param delList
     * @return
     */
    boolean batchDelList(List batchdelpermission);//批量删除
}
