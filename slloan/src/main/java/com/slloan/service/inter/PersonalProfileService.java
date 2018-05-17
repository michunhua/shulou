package com.slloan.service.inter;

import java.util.List;
import java.util.Map;


import com.slloan.entity.AddRole;
import com.slloan.entity.Contacts;
import com.slloan.entity.JointApplicant;
import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.ResultList;
import com.slloan.entity.UserLogin;

public interface PersonalProfileService {

	boolean save(PersonalProfile personalProfile);

	boolean perupdate(PersonalProfile personalProfile);

	// 查询ID
	PersonalProfile SelectById(int id);
	
	//查询ID 身份证
			PersonalProfile SelectToById(int id , String id_number);

//			Page<PersonalProfile> getLoanPage(int currentPage);	
			
		 	public Page<PersonalProfile> getPersonalProfilePage(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	int getPersonalProfileCount(String username,String rolename,String city,String parentnodeId);
		 	
		 	
		 	/**
		 	 * 初审模糊查询
		 	 * @param param
		 	 * @return
		 	 */
		 	List<PersonalProfile> vaguelikeSelectCreateone(Map<Object,Object> param);
		 	
		 	/**
		 	 * 按揭员模糊查询
		 	 * @param param
		 	 * @return
		 	 */
		 	
		 	Page<PersonalProfile> vaguelikeSelectCreatetwo(Map<Object,Object> param);
		 	
		 	Page<PersonalProfile> vaguelikeSelectCreatetwo2(Map<Object,Object> param);
		 	
		 	/**
		     *初审 分页操作，调用分页方法
		     * @param map
		     * @return
		     */
		 	public Page<PersonalProfile> getFirsttrialPage(int currentPage, String username, String role, String city,String parentnodeId , int intid);
		 	public Page<PersonalProfile> getFirsttrialPage(int currentPage, String username, String role, String city,String parentnodeId);
		 		/**
			     * 初审用户记录总数
			     * @return
			     */
		 	int getFirsttrialPageCount(String username,String rolename,String city,String parentnodeId);
		 	
			/**
		     *终审分页操作，调用分页方法
		     * @param map
		     * @return
		     */
		 	public Page<PersonalProfile> getFinalreviewPage(int currentPage,String username,String role,String city,String parentnodeId);
		 	public Page<PersonalProfile> getFinalreviewPage(int currentPage,String username,String role,String city,String parentnodeId,int intid);
		 		/**
			     * 终审用户记录总数
			     * @return
			     */
		 	int getFinalreviewPageCount(String username,String rolename,String city,String parentnodeId);
		 	
		 	/**
		 	 * 财务列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getFinancePagePage(int currentPage,String username,String role,String city,String parentnodeId);
		 	public Page<PersonalProfile> getFinancePagePage(int currentPage,String username,String role,String city,String parentnodeId,int intid);
		 	/**
		 	 * 财务状态统计
		 	 * @return
		 	 */
		 	int getFinancePageCount(String username,String rolename,String city,String parentnodeId);
		 	
		 	/**
		 	 * 结算列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getjsloanPage(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 结算状态统计
		 	 * @return
		 	 */
		 	int getjsloanPageCount(String username,String rolename,String city,String parentnodeId);
		 	
		 	/**
		 	 * 转账列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getTransferloanPage(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 转账状态统计
		 	 * @return
		 	 */
		 	int getTransferloanPageCount(String username,String rolename,String city,String parentnodeId);
		 	

		 	/**
		 	 * 贷款信息列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getLoanInformation(int currentPage,String username,String role,String city,String parentnodeId);
		 	public Page<PersonalProfile> getLoanInformationCity(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 贷款信息统计
		 	 * @return
		 	 */
		 	int getLoanInformationCount(String username,String rolename,String city,String parentnodeId);

			/**
		 	 * 回款列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getReturnMoney(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 回款统计
		 	 * @return
		 	 */
		 	int getReturnMoneyCount(String username,String rolename,String city,String parentnodeId);

			/**
		 	 * 取证列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getObtainEvidence(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 取证统计
		 	 * @return
		 	 */
		 	int getObtainEvidenceCount(String username,String rolename,String city,String parentnodeId);

			/**
		 	 * 解压列表
		 	 * @return
		 	 */
		 	public Page<PersonalProfile> getDecompression(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 解压统计
		 	 * @return
		 	 */
		 	int getDecompressionCount(String username,String rolename,String city,String parentnodeId);
	
			/**
		 	 * 进压列表
		 	 * @return
		 	 */
		 	
		 	public Page<PersonalProfile> getLoanPressure(int currentPage,String username,String role,String city,String parentnodeId);
		 	
		 	/**
		 	 * 进押统计
		 	 * @return
		 	 */
		 	int getLoanPressureCount(String username,String rolename,String city,String parentnodeId);

//			int getPersonalProfilePage();
		 	
		 	public PersonalProfile getSelectById(PersonalProfile param);
			public PersonalProfile getSelectByIdAdmin(PersonalProfile param);
		 	
		 	public Page<PersonalProfile> checkHangData(String state,String username,String role,String city,String parentnodeId,int id,int currentPage);
		 	int checkHangDataCount(String state);
		 	
}
