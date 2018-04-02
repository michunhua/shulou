package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.AddRole;
import com.slloan.entity.PermissionEntity;
import com.slloan.entity.UserLogin;

/**
 * 角色添加
 * @author Administrator
 *
 */

@Repository(value="roleaaddao")
public interface RoleAddDao extends BaseDao<AddRole>{
	
	public boolean addRoleUser (AddRole addrole);
	
	 public AddRole selectRoleId(String rolename);
	 
	 boolean batchDeletes(List delList);//批量删除
	 
	//更新查询
	 public AddRole updateselectId(Integer sno);
		//更新
	 boolean updateRole(AddRole sno);
	 
	//查询roleid是否存在
//	boolean addPermission(PermissionEntity permission);
	 /**
		 * 根据角色名得到所属城市
		 */
	 public AddRole accordingtoroleCity(Map<String,String>param);
	 	/**
	     * 分页操作，调用findByPage limit分页方法
	     * @param map
	     * @return
	     */
	 	public List<AddRole> getRolePage(Map<String,Object> map);
	 	/**
	     * 查询用户记录总数
	     * @return
	     */
	 	int getRoleCount();
	 	
	 	  /**
	     * 根据Id查询一条记录
	     *
	     * @param id
	     * @return
	     */
	    public AddRole selectByRId(Map<String,Object>map);
}
