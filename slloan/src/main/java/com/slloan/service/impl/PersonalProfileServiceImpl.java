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
		int totalCount = personalProfiledao.getPersonalProfileCount();
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
	public int getPersonalProfileCount() {
		return personalProfiledao.getPersonalProfileCount();
	}
	
	/**
	 * 按揭员列表模糊查询
	 *//*

	@Override
	public List<PersonalProfile> vaguelikeSelectCreateone(Map<String,String> param) {
		Map<String,String> map = new TreeMap<String,String>();

			if(param.get("a") !=null){
				map.put("name", param.get("a"));
				System.out.println("a: "+param.get("a"));
			} if(param.get("b")!=null){
				map.put("idnumber", param.get("b"));
				System.out.println("b: "+param.get("b"));
			} if(param.get("c")!=null){
				map.put("mobilephone", param.get("c"));
				System.out.println("c: "+param.get("c"));
			} if(param.get("d")!=null){
				map.put("ctim1", param.get("d"));
				System.out.println("d: "+param.get("d"));
			} if(param.get("e")!=null){
				map.put("ctim2", param.get("e"));
				System.out.println("e: "+param.get("e"));
			} if(param.get("f")!=null){
				map.put("amount", param.get("f"));
				System.out.println("f: "+param.get("f"));
			} if(param.get("g")!=null){
				map.put("amount2", param.get("g"));
				System.out.println("g: "+param.get("g"));
			}
			
		return personalProfiledao.vaguelikeSelectCreatetwo(map);

	}*/
	
	/**
	 *  按揭员模糊查询
	 */

	@Override
	public List<PersonalProfile> vaguelikeSelectCreatetwo(Map<Object,Object> param) {
		Map<Object,Object> map = new HashMap<Object,Object>();
		ResultList<PersonalProfile> resulit = new ResultList<PersonalProfile>();
		 Iterator it = param.entrySet().iterator();  
		 while (it.hasNext()) {  
			   Map.Entry entry = (Map.Entry) it.next();  
			   Object key = entry.getKey();  
			   Object value = entry.getValue();  
//			   System.out.println("key=" + key + " value=" + value);  
			   map.put(key, value);
			   
//			   return personalProfiledao.vaguelikeSelectCreate(map);
			  }
		 List<PersonalProfile>  p= personalProfiledao.vaguelikeSelectCreate(map);
		 resulit.setLists(p);
//		return resulit;
		return p;
		
//		return ;
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
		return personalProfiledao.vaguelikeSelectCreate(map);
		
//		return ;
	}

	@Override
	public Page<PersonalProfile> getFirsttrialPage(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = personalProfiledao.getFirsttrialPageCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<PersonalProfile> lists = personalProfiledao.getFirsttrialPage(map); 
		pageBean.setLists(lists);
		return pageBean;
}

	@Override
	public int getFirsttrialPageCount() {
		return personalProfiledao.getFirsttrialPageCount();
	}

	@Override
	public Page<PersonalProfile> getFinalreviewPage(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		//封装当前页数
		pageBean.setCurrPage(currentPage);
		//每页显示的数据
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		//封装总记录数
		int totalCount = personalProfiledao.getFinalreviewPageCount();
		pageBean.setTotalCount(totalCount);
		//封装总页数
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		//封装每页显示的数据
		List<PersonalProfile> lists = personalProfiledao.getFinalreviewPage(map); 
		pageBean.setLists(lists);
		return pageBean;
}

	@Override
	public int getFinalreviewPageCount() {
		return personalProfiledao.getFinalreviewPageCount();
	}

	@Override
	public Page<PersonalProfile> getFinancePagePage(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getFinancePageCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getFinancePagePage(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getFinancePageCount() {
		return personalProfiledao.getFinancePageCount();
	}

	@Override
	public Page<PersonalProfile> getjsloanPage(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getjsloanPageCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getjsloanPage(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getjsloanPageCount() {
		return personalProfiledao.getjsloanPageCount();
	}

	@Override
	public Page<PersonalProfile> getTransferloanPage(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getTransferloanPageCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getTransferloanPage(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getTransferloanPageCount() {
		return personalProfiledao.getTransferloanPageCount();
	}
	

	@Override
	public Page<PersonalProfile> getLoanInformation(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getLoanInformationCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getLoanInformation(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getLoanInformationCount() {
		return personalProfiledao.getLoanInformationCount();
	}

	@Override
	public Page<PersonalProfile> getReturnMoney(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getReturnMoneyCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getReturnMoney(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getReturnMoneyCount() {
		return personalProfiledao.getReturnMoneyCount();
	}

	@Override
	public Page<PersonalProfile> getObtainEvidence(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getObtainEvidenceCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getObtainEvidence(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getObtainEvidenceCount() {
		return personalProfiledao.getObtainEvidenceCount();
	}

	@Override
	public Page<PersonalProfile> getDecompression(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getDecompressionCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getDecompression(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getDecompressionCount() {
		return personalProfiledao.getDecompressionCount();
	}

	@Override
	public Page<PersonalProfile> getLoanPressure(int currentPage, String username, String role, String city,String parentnodeId) {
		Map<String,Object> map = new HashMap<String,Object>();
		Page<PersonalProfile> pageBean = new Page<PersonalProfile>();
		pageBean.setCurrPage(currentPage);
		int pageSize = 10;
		pageBean.setPageSize(pageSize);
		int totalCount = personalProfiledao.getLoanPressureCount();
		pageBean.setTotalCount(totalCount);
		double tc = totalCount;
		Double num = Math.ceil(tc/pageSize);
		pageBean.setTotalPage(num.intValue());
		map.put("username",username);
		map.put("rolename",role);
		map.put("city",city);
		map.put("parentnodeId",parentnodeId);
		map.put("page", (currentPage-1)*pageSize);
		map.put("limit", pageBean.getPageSize());
		List<PersonalProfile> lists = personalProfiledao.getLoanPressure(map);
			pageBean.setLists(lists);
		return pageBean;
	}

	@Override
	public int getLoanPressureCount() {
		return personalProfiledao.getLoanPressureCount();
	}

	@Override
	public PersonalProfile getSelectById(PersonalProfile param) {
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("username", param.getUsername());
		map.put("city", param.getCity());
		map.put("rolename", param.getRolename());
		map.put("parentnodeId", param.getParentnodeId());
		map.put("name", param.getName());
		return personalProfiledao.getSelectById(map);
	}

}
