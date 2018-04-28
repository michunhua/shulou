package com.slloan.service.impl;

import java.util.Date;

import javax.rmi.CORBA.UtilDelegate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.CircuLationRecordSubmitDao;
import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.Firstfilla;
import com.slloan.entity.NoteDescription;
import com.slloan.entity.NoteExplain;
import com.slloan.service.inter.CircuLationRecordSubmitService;
import com.slloan.util.DateUtils;

/**
 * 提交&回退Service实现类
 * @author Administrator
 *
 */
@Service(value="circuLationrecordsubmitserviceimpl")
public class CircuLationRecordSubmitServiceImpl implements CircuLationRecordSubmitService {

	@Autowired
	private CircuLationRecordSubmitDao recordSubmitDao;
	private String firstname;
	private String firstnamese;
	
	@Override
	public CircuLationRecord fallbackSelectById(int id) {
		return null;
	}

	@Override
	public boolean fallbackinsert(CircuLationRecord circuLationRecord) {
		String fallbackname = circuLationRecord.getFallbackname();
//		String submit = circuLationRecord.getSubmit();
		int state = circuLationRecord.getState();
//		String spare1 =circuLationRecord.getSpare1();
//		String Sparefield =circuLationRecord.getSparefield();
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		String username = circuLationRecord.getUsername();
		String ParentnodeId = circuLationRecord.getParentnodeId();
		String city = circuLationRecord.getCity();
		String rolename = circuLationRecord.getRolename();
		String spare1 = circuLationRecord.getSpare1();
		CircuLationRecord record = new CircuLationRecord(fallbackname,state,createDate,username,ParentnodeId,city,rolename,spare1);
		return recordSubmitDao.fallbackinsert(record);
	}

	@Override
	public boolean firstTrial(NoteExplain noteExplain) {
		String firstTrialNote = noteExplain.getFirstTrialNote();
		String recordSingleNote = noteExplain.getRecordSingleNote();
		String city = noteExplain.getCity();
		String createDate = DateUtils.getInDateTime((new Date()));//日期
		NoteExplain note = new NoteExplain(firstTrialNote,recordSingleNote,city,createDate);
		return recordSubmitDao.firstTrial(note);
	}

	@Override
	public boolean firstName(Firstfilla firstfilla) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean firstname(Firstfilla firstfilla) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updatefallbackinsert(CircuLationRecord circuLationRecord) {
		int id =circuLationRecord.getId();
		String fallbackname =circuLationRecord.getFallbackname();
		int state =circuLationRecord.getState();
//		String username = circuLationRecord.getUsername();
//		String rolename =circuLationRecord.getRolename();//obj.getString("rolename");
//		String city =circuLationRecord.getCity();//obj.getString("city");
//		 String parentnodeId =circuLationRecord.getParentnodeId();// obj.getString("parentnodeId");
		
		CircuLationRecord c = new CircuLationRecord(fallbackname,state,id);
		
		return recordSubmitDao.updatefallbackinsert(c);
	}

	@Override
	public boolean fallbackinserts(CircuLationRecord circuLationRecord) {
		String fallbackname = circuLationRecord.getFallbackname();
		int state = circuLationRecord.getState();
		String createDate =  DateUtils.getInDateTime((new Date()));//日期
		String username = circuLationRecord.getUsername();
		String ParentnodeId = circuLationRecord.getParentnodeId();
		String city = circuLationRecord.getCity();
		String rolename = circuLationRecord.getRolename();
		CircuLationRecord record = new CircuLationRecord(fallbackname,state,createDate,username,ParentnodeId,city,rolename);
		return recordSubmitDao.fallbackinserts(record);
	}


	@Override
	public CircuLationRecord findById(int id) {
		return recordSubmitDao.findById(id);
	}
	
}
