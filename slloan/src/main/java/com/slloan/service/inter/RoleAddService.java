package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.dao.BaseDao;
import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.PermissionEntity;
import com.slloan.entity.UserLogin;

/**
 * 角色添加
 * @author Administrator
 *
 */


public interface RoleAddService extends BaseDao<AddRole>{
	public boolean addRoleUser (AddRole addrole);
	 public AddRole selectRoleId(String rolename);
	 public  void deleteno(Integer[] id);
//	boolean addPermission(PermissionEntity permission);
//	 public boolean updateRole(Integer id);
	 boolean batchDeletes(List delList);//批量删除
	 //java.lang.Integer
	 
	//更新查询
	public AddRole updateselectId(Integer id);
			//更新
	public boolean updateRole(AddRole id);
	
	public List<AddRole> getRole();//获取下拉角色
	
	/**
     * 查询用户记录总数
     * @return
     */
	int selectCount();
	
	/**
     * 分页操作，调用getRolePage limit分页方法
     * @param map
     * @return
     */
	Page<AddRole> getRolePage(int currentPage);
	
	public List<AddRole> find(AddRole addrole);
	
	/**
	 * 
	 * @param addrole 角色名&城市
	 * @return Object
	 */
	
	public AddRole selectByRId(AddRole addrole);
	
	/**
	 * 根据角色名得到所属城市
	 */
	public AddRole accordingtoroleCity(AddRole param);
	
	//查询所有的角色
	public List<AddRole> list();
	
}
