package com.slloan.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slloan.controller.RoleAddController;
import com.slloan.dao.RoleAddDao;
import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.PermissionEntity;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.RoleAddService;
import com.slloan.util.DateUtils;

import groovy.xml.Entity;


@Service(value="roleaddservice")
//@Transactional
public class RoleAddServiceImpl implements RoleAddService{

	@Autowired
	public RoleAddDao roleAddDao;
	
	@Override
	public void insert(AddRole t) {
		
	}

	@Override
	public void deleteById(String id) {
		
	}

	@Override
	public void update(AddRole t) {
		
	}

	@Override
	public AddRole selectById(String id) {
		return null;
	}

	@Override
	public List<AddRole> list() {
		return roleAddDao.list();
	}

	@Override
	public long count(Map<String, Object> map) {
		return 0;
	}

	@Override
	public List<AddRole> find(Map<String, Object> map) {
		return null;
	}
	//添加角色
	@Override
	public boolean addRoleUser(AddRole addrole) {
		String roleName = addrole.getRoleName();
		String descriPtion = addrole.getDescriPtion();
//		String descriPtion = addrole.getDescriPtion();
		String belongs_City = addrole.getBelongs_City();
		String note = addrole.getNote();
		String createDate = addrole.getCreateDate();
		String configuration = addrole.getConfiguration();
		String parentid = addrole.getParentid();
		AddRole addrolee = new AddRole(roleName,descriPtion,note,createDate,belongs_City,configuration,parentid);
		return roleAddDao.addRoleUser(addrolee);
	}

	@Override
	public AddRole selectRoleId(String rolename) {
		return roleAddDao.selectRoleId(rolename);
	}

	@Override
	public void deleteno(Integer[] id) {
		
	}

	@Override
	public boolean batchDeletes(List delList) {
		return roleAddDao.batchDeletes(delList);
	}

	@Override
	public AddRole updateselectId(Integer id) {
		return roleAddDao.updateselectId(id);
	}

	@Override
	public boolean updateRole(AddRole id) {
		Integer sid = id.getId();
		String rolename = id.getRoleName();
		String descriPtion = id.getDescriPtion();
		String city = id.getBelongs_City();
		String note = id.getNote();
		String configuration = id.getConfiguration();
		String updatedate =	DateUtils.getInDateTime((new Date()));//日期
		AddRole roleupdate = new AddRole(rolename,descriPtion,city,note,configuration,updatedate,sid);
		return roleAddDao.updateRole(roleupdate);
	}

	@Override
	public List<AddRole> getRole() {
		return roleAddDao.list();
	}

	@Override
	public int selectCount() {
		return roleAddDao.getRoleCount();
	}
	
	@Override
	public Page<AddRole> getRolePage(int currentPage,String parentid,String username) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<AddRole> pageBean = new Page<AddRole>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = roleAddDao.getRoleCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);//向上取整
		pageBean.setTotalPage(num.intValue());
		map.put("parentid",parentid);
		map.put("username",username);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<AddRole> lists = roleAddDao.getRolePage(map);
		pageBean.setLists(lists);
			return pageBean;
	}

	@Override
	public Page<AddRole> getRolePage(int currentPage) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<AddRole> pageBean = new Page<AddRole>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = roleAddDao.getRoleCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);//向上取整
		pageBean.setTotalPage(num.intValue());
//		map.put("parentid",parentid);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<AddRole> lists = roleAddDao.getRolePage2(map);
		pageBean.setLists(lists);
			return pageBean;
	}
	
	@Override
	public List<AddRole> find(AddRole map) {
		Map<String, Object> param = new HashMap<String,Object>();
			param.put("id", map.getId());
			param.put("rolename", map.getRoleName());
			param.put("belongscity", map.getBelongs_City());
		return roleAddDao.find(param);
	}

	@Override
	public AddRole selectByRId(AddRole addrole) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("roleName", addrole.getRoleName());
		map.put("belongs_City", addrole.getBelongs_City());
		return roleAddDao.selectByRId(map);
	}

//	@Override
//	public AddRole accordingtoroleCity(AddRole param) {
//		return null;
//	}

	@Override
	public AddRole accordingtoroleCity(AddRole param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", param.getId());
		return roleAddDao.accordingtoroleCity(map);
	}

	@Override
	public AddRole selectroleRoleName(Map<Object, Object> map) {
		Map<Object, Object> param = new HashMap<Object,Object>();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			param.put(key, value);
		}
		return roleAddDao.selectroleRoleName(param);
	}

	@Override
	public List<AddRole> getselectByid(String parentid) {
		return roleAddDao.getselectByid(parentid);
	}

	@Override
	public AddRole getselectByid2(String parentid) {
		return roleAddDao.getselectByid2(parentid);
	}
}
