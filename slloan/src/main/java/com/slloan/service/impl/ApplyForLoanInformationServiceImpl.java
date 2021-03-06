package com.slloan.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.ApplyForLoanInformationDao;
import com.slloan.dao.districtDao;
import com.slloan.entity.ApplyForLoanInformation;
import com.slloan.entity.Contacts;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.service.inter.ApplyForLoanInformationService;
import com.slloan.util.Json;

@Service(value="ApplyForLoanInformationServiceImpl")
public class ApplyForLoanInformationServiceImpl implements ApplyForLoanInformationService{
	
	@Autowired
	public ApplyForLoanInformationDao app;
	
	
	
	@Override
	public boolean save(ApplyForLoanInformation applyForLoanInformation) {
		 String amount =applyForLoanInformation.getAmount();						//金额
		 String time_Limit=applyForLoanInformation.getTime_Limit();					//期限
		 String time_Limits=applyForLoanInformation.getTime_Limits();	
		 String borrowing_Variety=applyForLoanInformation.getBorrowing_Variety();    		//借款品种
		 String repayment=applyForLoanInformation.getRepayment();					//还款方式
		 String receiving_Bank_Name=applyForLoanInformation.getReceiving_Bank_Name();  		//收款银行名称
		 String receiving_Account_Name=applyForLoanInformation.getReceiving_Account_Name();	    //收款账户名
		 String receiving_Account=applyForLoanInformation.getReceiving_Account();    		//收款账号
		 String repayment_Bank_name=applyForLoanInformation.getRepayment_Bank_Name();  		//还款银行名称
		 String repayment_Account_Name=applyForLoanInformation.getRepayment_Account_Name(); 		//还款账户名
		 String repayment_Account_Number=applyForLoanInformation.getRepayment_Account_Number();		//还款账号
		 String state = applyForLoanInformation.getState();
		 String ctime = applyForLoanInformation.getCtime();
		 String username = applyForLoanInformation.getUsername();
			String ParentnodeId = applyForLoanInformation.getParentnodeId();
			String city = applyForLoanInformation.getCity();
			String rolename = applyForLoanInformation.getRolename();
		 ApplyForLoanInformation ap = new ApplyForLoanInformation(amount,time_Limit,time_Limits,borrowing_Variety,repayment,receiving_Bank_Name,receiving_Account_Name,receiving_Account,repayment_Bank_name,repayment_Account_Name,repayment_Account_Number,state,ctime,username,ParentnodeId,city,rolename);
		return app.save(ap);
			
		
	}

	@Override
	public ApplyForLoanInformation findById(int id) {
		
		return null;
	}

	@Override
	public boolean delete(int id) {
		boolean isResilt = app.delete(id);
		if(isResilt == true){
			return true;
		}else {
			return false;
		}
		
	}

	@Override
	public boolean appUpdate(ApplyForLoanInformation applyForLoanInformation) {
		
		return app.appUpdate(applyForLoanInformation);
			
	}
	
	@Override
	public boolean select(ApplyForLoanInformation ap) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public ApplyForLoanInformation SelectById(int id) {
		
		return app.SelectById(id);
	}

	@Override
	public ApplyForLoanInformation SelectByIdPro(int state) {
	
		return app.SelectByIdPro(state);
	}

	@Override
	public ApplyForLoanInformation SelectByIdApp(int id) {
		// TODO Auto-generated method stub
		return app.SelectByIdApp(id);

	}





	


}
