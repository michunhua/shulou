package com.slloan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.circulationDao;
import com.slloan.entity.circulation;
import com.slloan.service.inter.circulationService;

@Service(value="circulationServiceImpl")
public class circulationServiceImpl implements circulationService{
	
	@Autowired
	private circulationDao recordSubmitDao;
	

	@Override
	public boolean save(circulation circul) {
//		Integer id = circul.getId();
		String state = circul.getState();
//		String circulation = circul.getCirculation();
		String ctime = circul.getCtime();
		String parentnodeId = circul.getParentnodeId();
		String rolename = circul.getRolename();
		String username = circul.getUsername();
		String city = circul.getCity();
		if(state.equals("1")){
			System.out.println("贷款创建提交到贷款初审,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款创建提交到贷款初审",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}if(state.equals("2")){
			System.out.println("贷款初审提交到贷款终审,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款初审提交到贷款终审",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("3")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("4")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("5")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("6")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("7")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("8")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("9")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		if(state.equals("10")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审提交到财务",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		return true;
	}
	@Override
	public boolean save2(circulation circul) {
//		Integer id = circul.getId();
		String state = circul.getState();
//		String circulation = circul.getCirculation();
		String ctime = circul.getCtime();
		String parentnodeId = circul.getParentnodeId();
		String rolename = circul.getRolename();
		String username = circul.getUsername();
		String city = circul.getCity();
		if(state.equals("0")){
			System.out.println("贷款创建提交到贷款初审,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款初审回退按揭员",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}if(state.equals("1")){
			System.out.println("贷款初审提交到贷款终审,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"贷款终审回退贷款初审",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}if(state.equals("2")){
			System.out.println("贷款初审提交到贷款终审,");
			circulation record = new circulation(state ,"------"+username+"------------>"+"财务回退贷款终审",ctime,username,parentnodeId,city,rolename);
			return recordSubmitDao.save(record);
		}
		return true;
	}

	@Override
	public circulation findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean appUpdate(circulation circul) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public circulation SelectById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<circulation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<circulation> findById(circulation param) {
		Map<String,Object>map = new HashMap<String,Object>();
		map.put("city", param.getCity());
		map.put("ParentnodeId", param.getParentnodeId());
		map.put("username", param.getUsername());
		return recordSubmitDao.findById(map);
	}



}
