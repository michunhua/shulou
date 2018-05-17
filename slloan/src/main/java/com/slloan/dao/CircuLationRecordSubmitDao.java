package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.Contacts;
import com.slloan.entity.Firstfilla;
import com.slloan.entity.NoteExplain;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.PropertyInformation;
import com.slloan.entity.circulation;

/**
 * 提交&回退DAO
 * @author Administrator
 *
 */
@Repository(value="circuLationRecordsubmitdao")
public interface CircuLationRecordSubmitDao {
	
	/**
	 * 回退到 借款申请人个人资料
	 * @return
	 */
	public CircuLationRecord fallbackSelectById(int id);
	
	/**
	 * 回退保存一条流转记录
	 * @param circuLationRecord
	 * @return
	 */
	public boolean fallbackinsert(CircuLationRecord circuLationRecord);

	public boolean firstTrial(NoteExplain note);
	

	public boolean updatefallbackinsert(CircuLationRecord circuLationRecord);

	
	public boolean fallbackinserts(CircuLationRecord circuLationRecord);
	CircuLationRecord findById(int id);
	
	List<CircuLationRecord> findById2(Map<String,Object>param);
	
	public boolean updateDateState(String id);//挂起
	CircuLationRecord findByIdHangup(String id);
	CircuLationRecord selectByidHangup(Map<String,Object> submit);
	public boolean updateDateStateCancel(String id);
}
