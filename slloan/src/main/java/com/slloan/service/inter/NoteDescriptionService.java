package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.Contacts;
import com.slloan.entity.NoteDescription;

public interface NoteDescriptionService {
	
	boolean save(NoteDescription notedesc);
	


	NoteDescription findById(int state);
	


	boolean delete(int id);

	boolean update(NoteDescription contacts);

	List<NoteDescription> findAll();
	
	boolean updatetwo(NoteDescription contacts);

	boolean updatethere(NoteDescription contacts);

	boolean updatefore(NoteDescription contacts);
}
