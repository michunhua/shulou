package com.slloan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.DataUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.circulationDao;
import com.slloan.entity.circulation;
import com.slloan.service.inter.circulationService;
import com.slloan.util.DateUtils;

@Service(value="circulationServiceImpl")
public class circulationServiceImpl implements circulationService{
	
	@Autowired
	private circulationDao recordSubmitDao;
	

	@Override
	public boolean save(circulation circul) {
//		Integer id = circul.getId();
		String state = circul.getState();
//		String circulation = circul.getCirculation();
		String ctime = circul.getCreatedata();
		String parentnodeId = circul.getParentnodeId();
		String rolename = circul.getRolename();
		String username = circul.getUsername();
		String city = circul.getCity();
		String spare = circul.getSpare();
		String updatedata = circul.getUpdatedata();
		if(state.equals("1")){
			System.out.println("贷款创建提交到贷款初审,");
			circulation record = new circulation(state ,"贷款创建提交到贷款初审",ctime,username.trim(),parentnodeId,city.trim(),rolename.trim(),updatedata,spare);
			return recordSubmitDao.save(record);
		}if(state.equals("2")){
			System.out.println("贷款初审提交到贷款终审,");
			circulation record = new circulation(state ,"贷款初审提交到贷款终审",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("3")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"贷款终审提交到财务待出账确认",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("4")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"待出账确认通过待放款确认",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("5")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"放款财务确认通过待取证确认",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("6")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"待取证确认通过待解押确认",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("7")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"待解押确认通过待进押确认",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("8")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"待进押确认通过待确认回款",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("9")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"待确认回款确认通过待结算",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		if(state.equals("10")){
			System.out.println("贷款终审提交到财务,");
			circulation record = new circulation(state ,"结算已结清贷款信息贷款查看",ctime,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save(record);
		}
		return true;
	}
	@Override
	public boolean save2(circulation circul) {
//		Integer id = circul.getId();
		String state = circul.getState();
		String circulation = circul.getCirculation();
		String parentnodeId = circul.getParentnodeId();
		String rolename = circul.getRolename();
		String username = circul.getUsername();
		String city = circul.getCity();
		String createdata = circul.getCreatedata();
		String updatedata =circul.getUpdatedata();
		String spare = circul.getSpare();
		
		
//		circulation record = new  circulation();
//		record.setState(state);
		
//		record.setCirculation("-------------->"+circulation);
//		record.setUsername(username);
//		record.setParentnodeId(parentnodeId);
//		record.setCity(city);
//		record.setRolename(rolename);
//		record.setCreatedata(createdata);
//		record.setUpdatedata(updatedata);
//		record.setSpare(spare);
		if(state.equals("0")){
			System.out.println("贷款初审退回到贷款创建,");
//			circulation record = new circulation(state ,circulation,createdata,updatedata,spare);
			circulation record = new circulation(state ,"贷款初审退回到贷款创建",createdata,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save2(record);
		}if(state.equals("1")){
			System.out.println("贷款终审退回到贷款初审");
//			circulation record = new circulation(state ,"------用户名:"+username+"------------>"+"贷款终审回退贷款初审",username,parentnodeId,city,rolename,updatedata);
//			circulation record = new circulation(state ,"贷款终审提交到财务",createdata,updatedata,spare);
			circulation record = new circulation(state ,"贷款终审回退贷款初审",createdata,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
			return recordSubmitDao.save2(record);
		}if(state.equals("2")){
			System.out.println("审批财务回退到终审审批中");
//			circulation record = new circulation(state ,"贷款终审提交到财务",createdata,updatedata,spare);
			circulation record = new circulation(state ,"财务回退贷款终审",createdata,username.trim(),parentnodeId.trim(),city.trim(),rolename.trim(),updatedata,spare.trim());
//			circulation record = new circulation(state ,"------用户名:"+username+"------------>"+"财务回退贷款终审",username,parentnodeId,city,rolename,updatedata);
			return recordSubmitDao.save2(record);
		}
		return true;
	}

	@Override
	public List<circulation> findById(circulation id) {
		Map<String,Object>param = new HashMap<String,Object>();
		param.put("spare", id.getSpare());
		param.put("city", id.getCity());
		param.put("ParentnodeId", id.getParentnodeId());
		param.put("username", id.getUsername());
		return recordSubmitDao.findById(param);
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
	public List<circulation> findById2(circulation param) {
		Map<String,Object>map = new HashMap<String,Object>();
//		map.put("id", param.getId());
		map.put("city", param.getCity());
		map.put("ParentnodeId", param.getParentnodeId());
		map.put("username", param.getUsername());
		map.put("submit", param.getId());
		map.put("state", param.getState());
		return recordSubmitDao.findById(map);
	}



}
