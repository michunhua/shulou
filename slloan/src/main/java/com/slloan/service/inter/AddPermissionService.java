package com.slloan.service.inter;

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

public interface AddPermissionService{

	public boolean addPermission(PermissionEntity permission);
	boolean batchDeletes(List delList);//批量删除
//	public boolean updateRole(Integer id);
//	boolean addpermission(PermissionEntity permision);
	public List<PermissionEntity> getRolePermissiondata(Map<String,String>param);//得到角色权限数据
	public List<PermissionEntity> find(PermissionEntity map);
	public boolean updatePermissionadd(PermissionEntity updateadd);//修改保存
	  /**
     * 批量删除权限表权限
     * @param delList
     * @return
     */
    boolean batchDelList(List batchdelpermission);//批量删除
}
