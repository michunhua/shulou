package com.slloan.dao;

import org.springframework.stereotype.Repository;

import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.Contacts;
import com.slloan.entity.Firstfilla;
import com.slloan.entity.NoteExplain;
import com.slloan.entity.PropertyInformation;

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
	
}
