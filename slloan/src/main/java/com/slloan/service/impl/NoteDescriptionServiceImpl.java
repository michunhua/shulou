package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.NoteDescriptionDao;
import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.NoteDescription;
import com.slloan.service.inter.NoteDescriptionService;

@Service("notedescriptionserviceimpl")
public class NoteDescriptionServiceImpl implements NoteDescriptionService {
	
	@Autowired
	private NoteDescriptionDao notedescriptionDao;
	
	
	@Override
	public boolean save(NoteDescription notedescription) {
		 String note_Description1 =notedescription.getNote_Description1();						//金额
		 String note_Description2=notedescription.getNote_Description2();					//期限
		 String note_Description3=notedescription.getNote_Description3();    		//借款品种
		 String state = notedescription.getstate();
		 String ctime = notedescription.getCtime();
		 String username = notedescription.getUsername();
			String ParentnodeId = notedescription.getParentnodeId();
			String city = notedescription.getCity();
			String rolename = notedescription.getRolename();
		 NoteDescription note = new NoteDescription(note_Description1, note_Description2, note_Description3, state, ctime,username,ParentnodeId,city,rolename);
		return notedescriptionDao.save(note);

}


	@Override
	public NoteDescription findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean update(NoteDescription contacts) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public List<NoteDescription> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}