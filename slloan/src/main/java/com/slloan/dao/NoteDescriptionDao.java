package com.slloan.dao;

import java.util.List;

import com.slloan.entity.CoborrowerSpouse;
import com.slloan.entity.NoteDescription;

public interface NoteDescriptionDao {

	boolean save(NoteDescription notedesc);

	NoteDescription findById(int id);

	boolean delete(int id);

	boolean update(NoteDescription contacts);

	List<NoteDescription> findAll();
	


}
