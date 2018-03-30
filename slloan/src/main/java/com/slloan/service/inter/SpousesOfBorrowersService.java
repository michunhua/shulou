package com.slloan.service.inter;

import java.util.List;

import com.slloan.entity.SpousesOfBorrowers;

public interface SpousesOfBorrowersService {
	
	boolean save(SpousesOfBorrowers spousesOfBorrowers);
	
	boolean update(SpousesOfBorrowers spousesOfBorrowers);
	
	boolean delete(int id); 
	
	SpousesOfBorrowers findById(int id);
	
	List<SpousesOfBorrowers> findAll();
}
