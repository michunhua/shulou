package com.slloan.dao;

import java.util.List;

import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.NoteDescription;

public interface NoteDescriptionDao {

	boolean save(NoteDescription notedesc);
	
	boolean savetwo(NoteDescription notedesc);
	
	boolean savethere(NoteDescription notedesc);

	NoteDescription findById(int id);

	boolean delete(int id);

	boolean update(NoteDescription contacts);

	List<NoteDescription> findAll();
	
	boolean updatetwo(NoteDescription contacts);
	boolean updatethere(NoteDescription contacts);
	boolean updatefore(NoteDescription contacts);

}
