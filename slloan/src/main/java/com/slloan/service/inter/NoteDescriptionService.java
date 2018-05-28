package com.slloan.service.inter;

import java.util.List;
import java.util.Map;

import com.slloan.entity.Contacts;
import com.slloan.entity.NoteDescription;
import com.slloan.entity.PersonalProfile;

public interface NoteDescriptionService {
	
	boolean save(NoteDescription notedesc);
	


	NoteDescription findById(int id);
	


	boolean delete(int id);

	boolean update(NoteDescription contacts);

	List<NoteDescription> findAll();
	
	boolean updatetwo(NoteDescription contacts);

	boolean updatethere(NoteDescription contacts);

	boolean updatefore(NoteDescription contacts);
	




}
