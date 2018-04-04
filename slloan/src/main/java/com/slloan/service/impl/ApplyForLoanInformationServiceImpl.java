package com.slloan.service.impl;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.slloan.dao.ApplyForLoanInformationDao;
import com.slloan.dao.districtDao;
import com.slloan.entity.ApplyForLoanInformation;
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
		 String borrowing_Variety=applyForLoanInformation.getBorrowing_Variety();    		//借款品种
		 String repayment=applyForLoanInformation.getRepayment();					//还款方式
		 String receiving_Bank_Name=applyForLoanInformation.getReceiving_Bank_Name();  		//收款银行名称
		 String receiving_Account_Name=applyForLoanInformation.getReceiving_Account_Name();	    //收款账户名
		 String receiving_Account=applyForLoanInformation.getReceiving_Account();    		//收款账号
		 String repayment_Bank_name=applyForLoanInformation.getRepayment_Bank_name();  		//还款银行名称
		 String repayment_Account_Name=applyForLoanInformation.getRepayment_Account_Name(); 		//还款账户名
		 String repayment_Account_Number=applyForLoanInformation.getRepayment_Account_Number();		//还款账号
		 String start = applyForLoanInformation.getStart();
		 String ctime = applyForLoanInformation.getCtime();
		 ApplyForLoanInformation ap = new ApplyForLoanInformation(amount,time_Limit,borrowing_Variety,repayment,receiving_Bank_Name,receiving_Account_Name,receiving_Account,repayment_Bank_name,repayment_Account_Name,repayment_Account_Number,start,ctime);
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
	public List<ApplyForLoanInformation> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ApplyForLoanInformation applyForLoanInformation) {
		 String amount =applyForLoanInformation.getAmount();						//金额
		 String time_Limit=applyForLoanInformation.getTime_Limit();					//期限
		 String borrowing_Variety=applyForLoanInformation.getBorrowing_Variety();    		//借款品种
		 String repayment=applyForLoanInformation.getRepayment();					//还款方式
		 String receiving_Bank_Name=applyForLoanInformation.getReceiving_Bank_Name();  		//收款银行名称
		 String receiving_Account_Name=applyForLoanInformation.getReceiving_Account_Name();	    //收款账户名
		 String receiving_Account=applyForLoanInformation.getReceiving_Account();    		//收款账号
		 String repayment_Bank_name=applyForLoanInformation.getRepayment_Bank_name();  		//还款银行名称
		 String repayment_Account_Name=applyForLoanInformation.getRepayment_Account_Name(); 		//还款账户名
		 String repayment_Account_Number=applyForLoanInformation.getRepayment_Account_Number();		//还款账号
		 String start = applyForLoanInformation.getStart();
		 String ctime = applyForLoanInformation.getCtime();
		 ApplyForLoanInformation apc = new ApplyForLoanInformation(amount,time_Limit,borrowing_Variety,repayment,receiving_Bank_Name,receiving_Account_Name,receiving_Account,repayment_Bank_name,repayment_Account_Name,repayment_Account_Number,start,ctime);
		 boolean isResult = app.update(apc);
		 if (isResult == true) {
			return true;
		}else{
			return false;
		}
			
	}

	@Override
	public boolean select(ApplyForLoanInformation ap) {
		// TODO Auto-generated method stub
		return false;
	}

}
