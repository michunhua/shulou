package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.NoteExplain;
import com.slloan.entity.UtilCity;

/**
 * 终审的修改与保存
 * @author Administrator
 *
 */
@Repository(value="finaljudgmentdao")
public interface FinaljudgmentDao {
	
	//根据城市查之前的备注
	public boolean selectByCityId(String id);
	
	//添加终审备注
	public boolean noteExplainAdd(NoteExplain noteExplain);
	
}
