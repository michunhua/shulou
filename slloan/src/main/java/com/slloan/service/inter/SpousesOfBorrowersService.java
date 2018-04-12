package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.SpousesOfBorrowers;

public interface SpousesOfBorrowersService {
	
	boolean save(SpousesOfBorrowers spousesOfBorrowers);
	
	boolean spoupdate(SpousesOfBorrowers spousesOfBorrowers);
	
	boolean delete(int id); 
	
	SpousesOfBorrowers findById(int id);
	
	List<SpousesOfBorrowers> findAll();

	SpousesOfBorrowers SelectById(int id);
}
