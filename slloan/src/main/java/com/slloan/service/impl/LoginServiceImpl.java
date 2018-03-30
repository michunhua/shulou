package com.slloan.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.loginDao;
import com.slloan.entity.AddRole;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.LoginService;

/**
 * 登录
 * @author Administrator
 *
 */
@Service("loginserviceimpl")
public class LoginServiceImpl implements LoginService{

	@Autowired
	public loginDao logindao;
	
	@Override
	public boolean Logindelu(UserLogin user) {
		Map<String,String> login = new HashMap<String,String>();
		login.put("username", user.getUserName());
		login.put("password", user.getPassWord());
		return logindao.Logindelu(login);
	}

//	@Override
//	public boolean addRole(AddRole addrole) {
//		Map<String, String> param = new HashMap<>();
//		param.put("roleName", addrole.getRoleName());//角色名
//		param.put("descriPtion", addrole.getDescriPtion());//角色描述
//		param.put("note", addrole.getNote());//所属城市
//		param.put("belongs_City", addrole.getBelongs_City());//备注
//		param.put("configuration", addrole.getConfiguration());//权限配置
//		param.put("loan_management", addrole.getLoan_management());//贷款管理
//		param.put("repayment_confirmation", addrole.getRepayment_confirmation());//回款确认
//		param.put("loan_Final_review", addrole.getLoan_Final_review());
//		param.put("loan_Create", addrole.getLoan_Create());
//		param.put("loan_First_trial", addrole.getLoan_First_trial());
//		param.put("loan_information", addrole.getLoan_information());
//		param.put("qz_certificate", addrole.getQz_certificate());
//		param.put("Incarceration_certificate", addrole.getIncarceration_certificate());
//		param.put("jy_certificate", addrole.getJy_certificate());
//		param.put("finance_Management", addrole.getFinance_Management());
//		param.put("finance_sp", addrole.getFinance_sp());
//		param.put("loan_Sp", addrole.getLoan_Sp());
//		param.put("Settlement_pz", addrole.getSetTlement_Pz());
//		param.put("Transfer_accounts_pz", addrole.getTransfer_Accounts_Pz());
//		param.put("personal_information_sz", addrole.getPersonal_information_sz());
//		param.put("personal_information_xg", addrole.getPersonal_information_xg());
//		param.put("password_Modify", addrole.getPassword_Modify());
//		param.put("role_Setup", addrole.getRole_Setup());
//		param.put("add_Role", addrole.getAdd_Role());
//		param.put("modify_Role", addrole.getModify_Role());
//		param.put("del_Role", addrole.getDel_Role());
//		param.put("qx__Setup", addrole.getQx__Setup());
//		param.put("fp_Jurisdiction", addrole.getFp_Jurisdiction());
//		param.put("del_Jurisdiction", addrole.getDel_Jurisdiction());
//		param.put("user_Administration", addrole.getUser_Administration());
//		param.put("add_User", addrole.getAdd_User());
//		param.put("fp_Role", addrole.getFp_Role());
//		param.put("del_User", addrole.getDel_User());
//		param.put("modify_user", addrole.getModify_user());
//		param.put("method", addrole.getMethod());
//		param.put("city", addrole.getCity());
//		param.put("county", addrole.getCounty());
////		return logindao.addrole(param);
//	}

}
