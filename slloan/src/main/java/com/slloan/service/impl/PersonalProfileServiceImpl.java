package com.slloan.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.slloan.dao.coborrowerSpouseDao;
import com.slloan.dao.personalProfileDao;
import com.slloan.entity.AddRole;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.ResultList;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.PersonalProfileService;
import com.slloan.util.DateUtils;

@Service(value = "personalprofileserviceimpl")
public class PersonalProfileServiceImpl implements PersonalProfileService {

	@Autowired
	private personalProfileDao personalProfiledao;
	private int state;
	private int tc;

	@Override
	public boolean save(PersonalProfile personalProfile) {
		String product_Number = personalProfile.getProduct_Number();// 编号
		String name = personalProfile.getName();// 共同借款人姓名
		String phoneticize = personalProfile.getPhoneticize();// 拼音、英文姓名
		String id_type = personalProfile.getId_type();// 身份证件类型
		String Other_identity_types = personalProfile.getOther_identity_types();// 输入身份证类型
		String id_number = personalProfile.getId_number();// 身份证件号码
		String country_and_region = personalProfile.getCountry_and_region();// 国家及地区
		String other_Countries = personalProfile.getOther_Countries();// 其他国家
		String sex = personalProfile.getSex();// 性别: 0男 1女
		String Local_domicile = personalProfile.getLocal_domicile();// 本地户籍
		String household_registration = personalProfile.getHousehold_registration();// 其他户籍所在地
		String marital_status = personalProfile.getMarital_status();// 婚姻情况0已婚，1未婚
		String housing_condition_now = personalProfile.getHousing_condition_now();// 0房改/继承
		String otherCensus = personalProfile.getOtherCensus();// 其他
		String birthday = personalProfile.getBirthday();// 出生日期
		String home_address_now = personalProfile.getHome_address_now();// 现住房地址
		String home_phone = personalProfile.getHome_phone();// 住宅电话
		String mobile_phone = personalProfile.getMobile_phone();// 移动电话
		String email = personalProfile.getEmail();// E-mail
		String present_address_zip_code = personalProfile.getPresent_address_zip_code();// 现住址邮编
		String vocation = personalProfile.getVocation();// 职业
		String unit_industry = personalProfile.getUnit_industry();// 现单位所处行业
		String uni_name = personalProfile.getUni_name();// 现单位名称
		String unit_address = personalProfile.getUnit_address();// 现单位地址
		String enterprise_scale = personalProfile.getEnterprise_scale();// 企业人数
		double Revenue_previous_year = personalProfile.getRevenue_previous_year();// 上年营收
		String asset_scale = personalProfile.getAsset_scale();// 资产规模
		String unit_phone = personalProfile.getUnit_phone();// 单位电话
		String postCode = personalProfile.getPostCode();// 单位邮编
		String job_category = personalProfile.getJob_category();// 职位类别
		String seniority = personalProfile.getSeniority();// 现单位工龄
		String former_unit_name = personalProfile.getFormer_unit_name();// 前单位名称
		String former_seniority = personalProfile.getFormer_seniority();// 前单位工龄
		String source_of_income = personalProfile.getSource_of_income();// 收入来源
		double monthly_income = personalProfile.getMonthly_income();// 月收入
		double Income_from_investment = personalProfile.getIncome_from_investment();// 投资收益
		double supportPeople = personalProfile.getSupportPeople();// 租金收入
		double Other_income = personalProfile.getOther_income();// 其他收入
		String family_number = personalProfile.getFamily_number();// 供养人数
		double monthly_expenditure = personalProfile.getMonthly_expenditure();// 月支出
		String postal_address = personalProfile.getPostal_address();// 通讯地址
		String state = personalProfile.getState();// 状态 //
													// 0按揭员录单1待初审审批中2待终审审批中3待出账确认4待放款5待取证6待解押7待进押8待确认回款9待结算10已结清
		String ctime = personalProfile.getCtime();// 日期
		String username = personalProfile.getUsername();
		String ParentnodeId = personalProfile.getParentnodeId();
		String city = personalProfile.getCity();
		String rolename = personalProfile.getRolename();
		String applicationnumber = DateUtils.getInDateTime(new Date());
		PersonalProfile joint = new PersonalProfile(product_Number, name, phoneticize, id_type, Other_identity_types,
				id_number, country_and_region, other_Countries, sex, Local_domicile, household_registration,
				marital_status, housing_condition_now, otherCensus, birthday, home_address_now, home_phone,
				mobile_phone, email, present_address_zip_code, vocation, unit_industry, uni_name, unit_address,
				enterprise_scale, Revenue_previous_year, asset_scale, unit_phone, postCode, job_category, seniority,
				former_unit_name, former_seniority, source_of_income, monthly_income, Income_from_investment,
				supportPeople, Other_income, family_number, monthly_expenditure, postal_address, state, ctime,username,ParentnodeId,city,rolename,applicationnumber);
		return personalProfiledao.save(personalProfile);
	}

	@Override
	public boolean perupdate(PersonalProfile personalProfile) {

		return personalProfiledao.perupdate(personalProfile);

	}

	@Override
	public PersonalProfile SelectById(int id) {

		return personalProfiledao.SelectById(id);
	}

	@Override
	public PersonalProfile SelectToById(int id, String id_number) {
		// TODO Auto-generated method stub
		return personalProfiledao.SelectToById(id,id_number);

	}
	


	@Override
	public Page<PersonalProfile> getPersonalProfilePage(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = personalProfiledao.getPersonalProfileCount(username,role,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);//向上取整
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<PersonalProfile> lists =personalProfiledao.getPersonalProfilePage(map);
		pageBean.setLists(lists);
			return pageBean;
	}

	@Override
	public int getPersonalProfileCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getPersonalProfileCount(username,rolename,city,parentnodeId);
	}
	
	
	/*
	 *  初审模糊查询
	 */
	@Override
	public List<PersonalProfile> vaguelikeSelectCreateone(Map<Object, Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		 Iterator it = param.entrySet().iterator();  
		 while (it.hasNext()) {  
			   Map.Entry entry = (Map.Entry) it.next();  
			   Object key = entry.getKey();  
			   Object value = entry.getValue();  
			   map.put(key, value);
			   
			  }
		 return null;
		
	}

	//初审

	@Override
	public Page<PersonalProfile> getFirsttrialPage(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = personalProfiledao.getFirsttrialPageCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("intid",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<PersonalProfile> lists = personalProfiledao.getFirsttrialPage(map); 
		pageBean.setLists(lists);
		return pageBean;
}
	
	@Override
	public int getFirsttrialPageCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getFirsttrialPageCount(username,rolename,city,parentnodeId);
	}

	//终审
	@Override
	public Page<PersonalProfile> getFinalreviewPage(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = personalProfiledao.getFinalreviewPageCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("id",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<PersonalProfile> lists = personalProfiledao.getFinalreviewPage(map); 
		pageBean.setLists(lists);
		return pageBean;
}

	@Override
	public int getFinalreviewPageCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getFinalreviewPageCount(username,rolename,city,parentnodeId);
	}
	
	
//财务
	@Override
	public Page<PersonalProfile> getFinancePagePage(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getFinancePageCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getFinancePagePage(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getFinancePageCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getFinancePageCount(username,rolename,city,parentnodeId);
	}

	
	//结算
	@Override
	public Page<PersonalProfile> getjsloanPage(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getjsloanPageCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getjsloanPage(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getjsloanPageCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getjsloanPageCount(username,rolename,city,parentnodeId);
	}
	
	
//转账凭证
	@Override
	public Page<PersonalProfile> getTransferloanPage(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getTransferloanPageCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getTransferloanPage(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getTransferloanPageCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getTransferloanPageCount(username,rolename,city,parentnodeId);
	}
	

	@Override
	public Page<PersonalProfile> getLoanInformation(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getLoanInformationCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getLoanInformation(map);
			pageBean.setLists(lists);
		return pageBean;
	}
	
	
	//贷款信息状态
	@Override
	public Page<PersonalProfile> getLoanInformationCity(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getLoanInformationCountCity(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getLoanInformationCity(map);
			pageBean.setLists(lists);
		return pageBean;
	}
	@Override
	public int getLoanInformationCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getLoanInformationCount(username,rolename,city,parentnodeId);
	}

	
	
	//回款状态
	@Override
	public Page<PersonalProfile> getReturnMoney(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getReturnMoneyCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getReturnMoney(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getReturnMoneyCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getReturnMoneyCount(username,rolename,city,parentnodeId);
	}

	
	//回款确认
	@Override
	public Page<PersonalProfile> getObtainEvidence(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getObtainEvidenceCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getObtainEvidence(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getObtainEvidenceCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getObtainEvidenceCount(username,rolename,city,parentnodeId);
	}

	
	
	//解压状态
	@Override
	public Page<PersonalProfile> getDecompression(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getDecompressionCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getDecompression(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getDecompressionCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getDecompressionCount(username,rolename,city,parentnodeId);
	}

	
	//进押状态
	@Override
	public Page<PersonalProfile> getLoanPressure(int currentPage, String username, String rolename, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getLoanPressureCount(username,rolename,city,parentnodeId);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",rolename);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getLoanPressure(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getLoanPressureCount(String username,String rolename,String city,String parentnodeId) {
		return personalProfiledao.getLoanPressureCount(username,rolename,city,parentnodeId);
	}

	@Override
	public PersonalProfile getSelectById(PersonalProfile param) {
		Map<String, Object> map = new HashMap<String,Object>();
//		map.put("username", param.getUsername());
		map.put("city", param.getCity());
//		map.put("rolename", param.getRolename());
//		map.put("parentnodeId", param.getParentnodeId());
		map.put("name", param.getName());
		map.put("id", param.getId());
		PersonalProfile p =	personalProfiledao.getSelectById(map);
				if(p !=null){
					return personalProfiledao.getSelectById(map);
				}else{
					return null;
				}
					
				
//		return ;
	}

	@Override
	public Page<PersonalProfile> checkHangData(String state,String username,String role,String city,String parentnodeId,int id,int currentPage) {
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		Map<String, Object> map = new HashMap<String,Object>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.checkHangDataCount(state);
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("state",state);
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("intid",id);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.checkHangData(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int checkHangDataCount(String state) {
		return personalProfiledao.checkHangDataCount(state);
	}
	
	/**
	 *  按揭员模糊查询
	 */
	
	@Override
	public Page<PersonalProfile> vaguelikeSelectCreatetwo(Map<Object,Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
//		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		ResultList<PersonalProfile> resulit = new ResultList<PersonalProfile>();

		Map<String,Object> map2 = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		
		if(param.get("name") !=null){
			map.put("name", param.get("name"));
//			map2.put("name",param.get("name"));
			System.out.println("a: "+param.get("a"));
		} if(param.get("id_number")!=null){
			map.put("id_number", param.get("id_number"));
//			map2.put("id_number", param.get("id_number"));
			System.out.println("b: "+param.get("b"));
		} if(param.get("mobile_phone")!=null){
			map.put("mobile_phone", param.get("mobile_phone"));
//			map2.put("mobile_phone", param.get("mobile_phone"));
			System.out.println("c: "+param.get("c"));
		} if(param.get("ctime")!=null){
			map.put("ctime", param.get("ctime"));
//			map2.put("ctime", param.get("ctime"));
			System.out.println("d: "+param.get("d"));
		} if(param.get("ctime2")!=null){
			map.put("ctime2", param.get("ctime2"));
//			map2.put("ctime2", param.get("ctime2"));
			System.out.println("e: "+param.get("e"));
		} if(param.get("amount")!=null){
			map.put("amount", param.get("amount"));
//			map2.put("amount", param.get("amount"));
			System.out.println("f: "+param.get("f"));
		} if(param.get("amount2")!=null){
			map.put("amount2", param.get("amount2"));
//			map2.put("amount2", param.get("amount2"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("state")!=null){
			map.put("state", param.get("state"));
//			map2.put("state", param.get("state"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("city")!=null){
			map.put("city", param.get("city"));
//			map2.put("city", param.get("city"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("username")!=null){
			map.put("username", param.get("username"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("rolename")!=null){
			map.put("rolename", param.get("rolename"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("rolename")!=null){
			map.put("rolename", param.get("rolename"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("applicationnumber")!=null){
//			map2.put("applicationnumber", param.get("applicationnumber"));
			map.put("applicationnumber", param.get("applicationnumber"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("page")!=null || param.get("city") !=null){
			
			String str = (String)param.get("page");
			String city = (String)param.get("city");
			int par = Integer.parseInt(str);
			//封装当前页数
			pageBean.setCurrPage(par);
			//每页显示的数据
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			//封装总记录数
			
			int totalCount = personalProfiledao.getPersonalProfileCount2(map);
			pageBean.setTotalCount(totalCount);
			//封装总页数
			double tc = totalCount;
			Double num = Math.ceil(tc/pageSize);
			pageBean.setTotalPage(num.intValue());
			map.put("page", (par-1)*pageSize);
		}if(param.get("limit")!=null){
			map.put("limit", pageBean.getPageSize());
		}
		 List<PersonalProfile>  p= personalProfiledao.vaguelikeSelectCreate(map);
		 pageBean.setLists(p);
		return pageBean;
		
	}
	//共同
	public Page<PersonalProfile> vaguelikeSelectCreatetwo2(Map<Object,Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
//		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		ResultList<PersonalProfile> resulit = new ResultList<PersonalProfile>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		if(param.get("name") !=null){
			map.put("name", param.get("name"));
			System.out.println("a: "+param.get("a"));
		} if(param.get("id_number")!=null){
			map.put("id_number", param.get("id_number"));
			System.out.println("b: "+param.get("b"));
		} if(param.get("mobile_phone")!=null){
			map.put("mobile_phone", param.get("mobile_phone"));
			System.out.println("c: "+param.get("c"));
		} if(param.get("ctime")!=null){
			map.put("ctime", param.get("ctime"));
			System.out.println("d: "+param.get("d"));
		} if(param.get("ctime2")!=null){
			map.put("ctime2", param.get("ctime2"));
			System.out.println("e: "+param.get("e"));
		} if(param.get("amount")!=null){
			map.put("amount", param.get("amount"));
			System.out.println("f: "+param.get("f"));
		} if(param.get("amount2")!=null){
			map.put("amount2", param.get("amount2"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("state")!=null){
			map.put("state", param.get("state"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("username")!=null){
			map.put("username", param.get("username"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("city")!=null){
			map.put("city", param.get("city"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("applicationnumber")!=null){
			map.put("applicationnumber", param.get("applicationnumber"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("page")!=null || param.get("city") !=null){
			
			String str = (String)param.get("page");
			String city = (String)param.get("city");
			int par = Integer.parseInt(str);
			//封装当前页数
			pageBean.setCurrPage(par);
			//每页显示的数据
			int pageSize = 10;
			pageBean.setPageSize(pageSize);
			int totalCount = personalProfiledao.getPersonalProfileCount2(map);
			pageBean.setTotalCount(totalCount);
			double tc = totalCount;
			//封装总记录数
			Double num = Math.ceil(tc/pageSize);
			pageBean.setTotalPage(num.intValue());
			map.put("page", (par-1)*pageSize);
		}if(param.get("limit")!=null){
			map.put("limit", pageBean.getPageSize());
		}
		 List<PersonalProfile>  p= personalProfiledao.vaguelikeSelectCreate(map);
		 pageBean.setLists(p);
		return pageBean;
		
	}
	//贷款信息查看
	@Override
	public Page<PersonalProfile> vaguelikeSelectCreatetwoAll(Map<Object, Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		ResultList<PersonalProfile> resulit = new ResultList<PersonalProfile>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		if(param.get("name") !=null){
			map.put("name", param.get("name"));
			System.out.println("a: "+param.get("a"));
		} if(param.get("id_number")!=null){
			map.put("id_number", param.get("id_number"));
			System.out.println("b: "+param.get("b"));
		} if(param.get("mobile_phone")!=null){
			map.put("mobile_phone", param.get("mobile_phone"));
			System.out.println("c: "+param.get("c"));
		} if(param.get("ctime")!=null){
			map.put("ctime", param.get("ctime"));
			System.out.println("d: "+param.get("d"));
		} if(param.get("ctime2")!=null){
			map.put("ctime2", param.get("ctime2"));
			System.out.println("e: "+param.get("e"));
		} if(param.get("amount")!=null){
			map.put("amount", param.get("amount"));
			System.out.println("f: "+param.get("f"));
		} if(param.get("amount2")!=null){
			map.put("amount2", param.get("amount2"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("state")!=null){
			map.put("state", param.get("state"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("city")!=null){
			map.put("city", param.get("city"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("username")!=null){
			map.put("username", param.get("username"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("applicationnumber")!=null){
			map.put("applicationnumber", param.get("applicationnumber"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("page")!=null || param.get("city") !=null){
			
			String str = (String)param.get("page");
			String city = (String)param.get("city");
			String username = "admin";
			int par = Integer.parseInt(str);
			//封装当前页数
			pageBean.setCurrPage(par);
			
			//每页显示的数据
			int pageSize = 10;
			
			pageBean.setPageSize(pageSize);
			//封装总记录数
			int totalCount = personalProfiledao.getPersonalProfileCountAll(map);
			pageBean.setTotalCount(totalCount);
			//封装总页数
			double tc = totalCount;
			Double num = Math.ceil(tc/pageSize);
			pageBean.setTotalPage(num.intValue());
			map.put("page", (par-1)*pageSize);
		}if(param.get("limit")!=null){
			map.put("limit", pageBean.getPageSize());
			
		}
		 List<PersonalProfile>  p= personalProfiledao.vaguelikeSelectCreateoneAll(map);
		 pageBean.setLists(p);
		return pageBean;
		
	}
//贷款信息查看城市经理
	@Override
	public Page<PersonalProfile> vaguelikeSelectCreatethere(Map<Object, Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		ResultList<PersonalProfile> resulit = new ResultList<PersonalProfile>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		if(param.get("name") !=null){
			map.put("name", param.get("name"));
			System.out.println("a: "+param.get("a"));
		} if(param.get("id_number")!=null){
			map.put("id_number", param.get("id_number"));
			System.out.println("b: "+param.get("b"));
		} if(param.get("mobile_phone")!=null){
			map.put("mobile_phone", param.get("mobile_phone"));
			System.out.println("c: "+param.get("c"));
		} if(param.get("ctime")!=null){
			map.put("ctime", param.get("ctime"));
			System.out.println("d: "+param.get("d"));
		} if(param.get("ctime2")!=null){
			map.put("ctime2", param.get("ctime2"));
			System.out.println("e: "+param.get("e"));
		} if(param.get("amount")!=null){
			map.put("amount", param.get("amount"));
			System.out.println("f: "+param.get("f"));
		} if(param.get("amount2")!=null){
			map.put("amount2", param.get("amount2"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("state")!=null){
			map.put("state", param.get("state"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("city")!=null){
			map.put("city", param.get("city"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("username")!=null){
			map.put("username", param.get("username"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("applicationnumber")!=null){
			map.put("applicationnumber", param.get("applicationnumber"));
			System.out.println("g: "+param.get("g"));
		}if(param.get("page")!=null || param.get("city") !=null){
			
			String str = (String)param.get("page");
			String city = (String)param.get("city");
			int par = Integer.parseInt(str);
			//封装当前页数
			pageBean.setCurrPage(par);
			//每页显示的数据
			int pageSize = 10;
			
			pageBean.setPageSize(pageSize);
			//封装总记录数
 			int totalCount = personalProfiledao.getPersonalProfileCountAll(map);
			pageBean.setTotalCount(totalCount);
			//封装总页数
			double tc = totalCount;
			Double num = Math.ceil(tc/pageSize);
			pageBean.setTotalPage(num.intValue());
			map.put("page", (par-1)*pageSize);
		}if(param.get("limit")!=null){
			map.put("limit", pageBean.getPageSize());
		}
		 List<PersonalProfile>  p= personalProfiledao.vaguelikeSelectCreatethere(map);
		 pageBean.setLists(p);
		return pageBean;
	}
 
	
	@Override
	public PersonalProfile SelectByIdMarital(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PersonalProfile> getFinalreviewPage(int currentPage, String username, String rolename, String city,
			String parentnodeId, int intid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PersonalProfile> getFinancePagePage(int currentPage, String username, String rolename, String city,
			String parentnodeId, int intid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonalProfile getSelectByIdAdmin(PersonalProfile param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<PersonalProfile> getFirsttrialPage(int currentPage, String username, String rolename, String city,
			String parentnodeId, int intid) {
		// TODO Auto-generated method stub
		return null;
	}



}
