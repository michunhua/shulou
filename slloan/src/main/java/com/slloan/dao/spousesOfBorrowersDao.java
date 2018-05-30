package com.slloan.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.PropertyInformation;
import com.slloan.entity.SpousesOfBorrowers;


@Repository(value="spousesofborrowersdao")
public interface spousesOfBorrowersDao {
	
	boolean save(SpousesOfBorrowers spousesOfBorrowers);
	
	SpousesOfBorrowers findById(int id); 
	
	boolean delete(int id); 
	
	boolean spoupdate(SpousesOfBorrowers spousesOfBorrowers);  
	
	List<SpousesOfBorrowers> findAll();
	
	SpousesOfBorrowers SelectById(int id);
	
	SpousesOfBorrowers SelectByIdSpo(int id);
}
