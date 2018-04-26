package com.slloan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.propertyInformationDao;
import com.slloan.entity.Contacts;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.PropertyInformation;
import com.slloan.service.inter.PropertyInformationService;

@Service(value = "PropertyInformationServiceImpl")
public class PropertyInformationServiceImpl implements PropertyInformationService {

	@Autowired
	private propertyInformationDao propertyInformationdao;



	@Override
	public boolean proupdate(PropertyInformation propertyInformation) {
		return propertyInformationdao.proupdate(propertyInformation);
	}

	@Override
	public boolean delete(int id) {
		boolean isResult = propertyInformationdao.delete(id);
		if (isResult == true) {
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public PropertyInformation findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PropertyInformation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public PropertyInformation SelectById(int id) {
		
		return propertyInformationdao.SelectById(id);
	}

	@Override
	public boolean save(PropertyInformation propertyInformation) {
		// TODO Auto-generated method stub
		
		String name = propertyInformation.getName();
		String ownership_And_percentage = propertyInformation.getOwnership_And_percentage(); // 权属人及占比
		String property_Address = propertyInformation.getProperty_Address(); // 房产地址
		String conStruction_Area = propertyInformation.getConStruction_Area(); // 建筑面积
		String inner_Area = propertyInformation.getInner_Area(); // 套内面积
		String sales_Contract_Number = propertyInformation.getSales_Contract_Number(); // 买卖合同编号
		String certificate_of_Title = propertyInformation.getCertificate_of_Title(); // 产权证号
		String proPerty_for = propertyInformation.getProPerty_for(); // 房产用于
		String the_Assessed_Value = propertyInformation.getThe_Assessed_Value(); // 评估值
		String original_Loan_Bank = propertyInformation.getOriginal_Loan_Bank(); // 原贷款银行
		double original_Loan_Amount = propertyInformation.getOriginal_Loan_Amount(); // 原贷款金额
		double loan_Outstanding_Balance = propertyInformation.getLoan_Outstanding_Balance(); // 原贷款尚欠本息余额
		String house_Account = propertyInformation.getHouse_Account(); // 供楼账号
		double transaCtion_Price = propertyInformation.getTransaCtion_Price(); // 买卖成交价
		double purchase_Deposit = propertyInformation.getPurchase_Deposit(); // 购房定金
		String supervision_of_funds = propertyInformation.getSupervision_of_funds(); // 资金监管
		String new_loan_Bank = propertyInformation.getNew_loan_Bank(); // 新贷款银行
		double new_Loan_Approval_Amount = propertyInformation.getNew_Loan_Approval_Amount(); // 新贷款批复金额
		String new_Loan_Bank_Account_Number = propertyInformation.getNew_Loan_Bank_Account_Number(); // 新贷款行账号
		String note_DescriPtion = propertyInformation.getNote_DescriPtion(); // 备注
		String state = propertyInformation.getState();
		String ctime = propertyInformation.getCtime();
		PropertyInformation preperty = new PropertyInformation(name,ownership_And_percentage, property_Address,
				conStruction_Area, inner_Area, sales_Contract_Number, certificate_of_Title, proPerty_for,
				the_Assessed_Value, original_Loan_Bank, original_Loan_Amount, loan_Outstanding_Balance, house_Account,
				transaCtion_Price, purchase_Deposit, supervision_of_funds, new_loan_Bank, new_Loan_Approval_Amount,
				new_Loan_Bank_Account_Number, note_DescriPtion,state,ctime);
		return propertyInformationdao.save(preperty); 
	}

}
