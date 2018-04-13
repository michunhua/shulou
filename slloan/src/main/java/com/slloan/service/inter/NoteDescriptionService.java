package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.NoteDescription;

public interface NoteDescriptionService {
	
	boolean save(NoteDescription notedesc);

	NoteDescription findById(int id);

	boolean delete(int id);

	boolean update(NoteDescription contacts);

	List<NoteDescription> findAll();
}
