package com.slloan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.slloan.dao.RoleAddDao;
import com.slloan.dao.UserDao;
import com.slloan.dao.UserUpdateDao;
import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.UserService;
import com.slloan.util.DateUtils;
import com.slloan.util.Json;

@Service(value="userServiceimpl")
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userdao;
	
	@Autowired
	private UserUpdateDao userupdatedao;
	
	@Autowired
	private RoleAddDao roleaddDao;
	
	private List<UserLogin> userlistpage;
	@Override
	public boolean addUser(UserLogin t) {
		Integer id = t.getId();
		String userName = t.getUserName();
		String password = t.getPassWord();
		String employeeis_Name = t.getEmployeeis_Name();
		String belongs_City =t.getBelongs_City();//所属城市
		String note = t.getNote();//备注
		String createDate = t.getCreate_Date();
		Integer r_id = t.getR_id();
		String distribution_Role = t.getDistribution_Role();//分配角色
		UserLogin user = new UserLogin(id,userName,password,employeeis_Name,distribution_Role,belongs_City,note,r_id,createDate);
		boolean isResult= userdao.addUser(user);
			if(isResult == true){
				return isResult;
			}else{
				return false;
			}
	}

	@Override
	public boolean deleteById(Integer id) {
		boolean isResult = userdao.deleteId(id);
		if(isResult == true){
			return true;
		}else
			return false;
	}

	@Override
	public boolean updateaddUser(UserLogin user) {
		int id = user.getId();
		 String userName = user.getUserName();//用户名
		 String passWord = user.getPassWord();//密码
		 String employeeis_Name = user.getEmployeeis_Name();//员工姓名
		 String distribution_Role = user.getDistribution_Role();//分配角色
		 String belongs_City =user.getBelongs_City();//所属城市
		 String note = user.getNote();//备注
		String updateDate = DateUtils.getInDateTime((new Date()));//日期
		 UserLogin u = new UserLogin(userName,passWord,employeeis_Name,distribution_Role,belongs_City,note,updateDate,id);

		boolean isResult = userdao.updateUser(u);
		if(isResult == true){
			return true;
		}else
			return false;
	}

	@Override
	public List<UserLogin> list() {
		return userdao.list();
	}

	@Override
	public UserLogin updateselectId(Integer sno) {
		return userdao.updateselectId(sno);
	}

	@Override
	public UserLogin selectById(String id) {
		return userdao.selectById(id);
	}

	@Override
	public UserLogin logindelu(UserLogin user) {
		Map<String,String> param = new HashMap<String, String>();
		param.put("username", user.getUserName());
		param.put("password", user.getPassWord());
		return userdao.logindelu(param);
	}

	@Override
	public boolean updatePassWord(String username, String passWord, String newpassword) {
		return userupdatedao.updatePassWord(username, passWord, newpassword);
	}

	@Override
	public int getCount() {
		return userdao.getCount();
	}


	@Override
	public AddRole accordingtoroleCity(AddRole param) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("rolename", param.getRoleName());
		map.put("belongs_city", param.getBelongs_City());
//		map.put("rolename", "财务");
//		map.put("belongs_city", "北京");
		return roleaddDao.accordingtoroleCity(map);
	}

	@Override
	public int selectCount() {
		return roleaddDao.getRoleCount();
	}

	@Override
	public Page<UserLogin> getUserByPage(int currentPage) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<UserLogin> pageBean = new Page<UserLogin>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = userdao.getCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<UserLogin> lists = userdao.getUserByPage(map); 
		pageBean.setLists(lists);
		return pageBean;
	}

//	@Override
//	public Page<UserLogin> getRolePage(int currentPage) {
//
//		Map<String,Object> map = new HashMap<String,Object>();
//		Page<UserLogin> pageBean = new Page<UserLogin>();
//		//封装当前页数
//		pageBean.setCurrPage(currentPage);
//		//每页显示的数据
//		int pageSize = 10;
//		pageBean.setPageSize(pageSize);
//		//封装总记录数
//		int totalCount = userdao.getRoleCount();
//		pageBean.setTotalCount(totalCount);
//		//封装总页数
//		double tc = totalCount;
//		Double num = Math.ceil(tc/pageSize);//向上取整
//		pageBean.setTotalPage(num.intValue());
//		map.put("page", (currentPage-1)*pageSize);
//		map.put("limit", pageBean.getPageSize());
//		//封装每页显示的数据
//		List<UserLogin> lists = userdao.getRolePage(map);
//		pageBean.setLists(lists);
//			return pageBean;
//	
//	}

	@Override
	public UserLogin selectUserById(int id) {
		return userdao.selectUserById(id);
	}

	@Override
	public UserLogin selectroleUserName(Map<Object, Object> map) {
		Map<Object,Object> param = new HashMap<Object,Object>();
		Iterator it = map.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry = (Map.Entry)it.next();
			Object key = entry.getKey();
			Object value= entry.getValue();
			param.put(key, value);
		}
		return userdao.selectroleUserName(param);
	}
}
