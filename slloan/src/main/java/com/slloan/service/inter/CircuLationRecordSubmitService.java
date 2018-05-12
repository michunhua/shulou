package com.slloan.service.inter;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.CircuLationRecord;
import com.slloan.entity.Firstfilla;
import com.slloan.entity.NoteDescription;
import com.slloan.entity.NoteExplain;
import com.slloan.entity.circulation;

/**
 * 提交&回退Service
 * @author Administrator
 *
 */
public interface CircuLationRecordSubmitService {
	
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
	
	
	public boolean updatefallbackinsert(CircuLationRecord circuLationRecord);
	
	
	/**
	 * 贷款初审备注
	 * @param noteexplain
	 * @return
	 */
	public boolean firstTrial(NoteExplain noteexplain);
	
	
	/**
	 * 贷款创建到贷款初审
	 * @param firstfilla
	 * @return
	 */



	public boolean firstName(Firstfilla firstfilla);

	boolean firstname(Firstfilla firstfilla);


	public boolean fallbackinserts(CircuLationRecord circuLationRecord);
	
	CircuLationRecord findById(int id);
	
	List<CircuLationRecord> findById2(CircuLationRecord param); 
	
	public boolean updateDateState(String id);//挂起
}
