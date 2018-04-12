package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.ApplyForLoanInformationDao;
import com.slloan.dao.ContactsDao;
import com.slloan.entity.Contacts;
import com.slloan.service.inter.ContactsService;

@Service(value = "ContactsServiceImpl")
public class ContactsServiceImpl implements ContactsService {
	
	@Autowired
	public ContactsDao cntactsdao;

	@Override
	public boolean save(Contacts con) {
		// TODO Auto-generated method stub
		Integer id = con.getId();
		String contacts=con.getContacts();
		String contacts1=con.getContacts1();
		String contacts2=con.getContacts2();
		String relationship=con.getRelationship();
		String relationship1=con.getRelationship1();
		String relationship2=con.getRelationship2();
		String c_Telephone=con.getC_Telephone();
		String c_Telephone1=con.getContacts1();
		String c_Telephone2=con.getC_Telephone2();
		String start =con.getStart();
		String ctime = con.toString();
		Contacts co = new Contacts(contacts, contacts1, contacts2, relationship, relationship1, relationship2, c_Telephone, c_Telephone1, c_Telephone2, start, ctime);
		return cntactsdao.save(co);
	}

	@Override
	public Contacts findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Contacts contacts) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Contacts> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contacts SelectById(int id) {
		
		return cntactsdao.SelectById(id);
	}

	@Override
	public boolean updateadd(Contacts contacts) {
		
		return cntactsdao.updateadd(contacts);
	}

}
