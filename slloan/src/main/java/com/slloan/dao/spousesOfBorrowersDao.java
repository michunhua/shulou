package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.SpousesOfBorrowers;


@Repository(value="spousesofborrowersdao")
public interface spousesOfBorrowersDao {
	
	boolean save(SpousesOfBorrowers spousesOfBorrowers);
	
	SpousesOfBorrowers findById(int id); 
	
	boolean delete(int id); 
	
	boolean update(SpousesOfBorrowers spousesOfBorrowers);  
	
	List<SpousesOfBorrowers> findAll();
}
