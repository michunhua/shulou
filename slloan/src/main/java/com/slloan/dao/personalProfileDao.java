package com.slloan.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.slloan.entity.Page;
import com.slloan.entity.PersonalProfile;
import com.slloan.entity.ResultList;

@Repository(value="personalprofiledao")
public interface personalProfileDao{

	
	boolean save(PersonalProfile personalProfile);
	
	PersonalProfile findById(int id); 
		boolean delete(int id); 
		

		
		List<PersonalProfile> findAll();

		boolean perupdate(PersonalProfile personalProfile);
	
		//查询ID
		PersonalProfile SelectById(int id);

		//查询ID
		PersonalProfile SelectToById(int id , String id_number);

		/**
	     * 分页操作，调用findByPage limit分页方法
	     * @param map
	     * @return
	     */
	 	public List<PersonalProfile> getPersonalProfilePage(Map<String,Object> map);
	 	
	 	/**
	     * 查询用户记录总数
	     * @return
	     */
	 	int getPersonalProfileCount(String username,String rolename,String city,String parentnodeId);
	 	
	 
	 	
	 	/**
	 	 * 按揭员列表查询
	 	 * @param param
	 	 * @return
	 	 */
	 	List<PersonalProfile> vaguelikeSelectCreate(Map<Object,Object> param);


	 	/**
	     *初审 分页操作，调用分页方法
	     * @param map
	     * @return
	     */
	 	public List<PersonalProfile> getFirsttrialPage(Map<String,Object> map);
	 	/**
	     * 初审用户记录总数
	     * @return
	     */
	 	int getFirsttrialPageCount(String username,String rolename,String city,String parentnodeId);
	 	int getPersonalProfileCount2(Map<Object,Object> map);
	 	/**
	 	 * 初审统计模糊查询
	 	 * @param username
	 	 * @param rolename
	 	 * @param city
	 	 * @param parentnodeId
	 	 * @return
	 	 */
	 	int getFirsttrialPageCount2(Map<String,Object> param);
	 	
		/**
	     *终审 分页操作，调用分页方法
	     * @param map
	     * @return
	     */
	 	public List<PersonalProfile> getFinalreviewPage(Map<String,Object> map);
	 	/**
	     * 终审用户记录总数
	     * @return
	     */
	 	int getFinalreviewPageCount(String username,String rolename,String city,String parentnodeId);
	 	

		/**
	     *财务 分页操作，调用分页方法
	     * @param map
	     * @return
	     */
	 	public List<PersonalProfile> getFinancePagePage(Map<String,Object> map);
	 	/**
	     *财务用户记录总数
	     * @return
	     */
	 	int getFinancePageCount(String username,String rolename,String city,String parentnodeId);
	 	
	 	/**
	 	 * 结算列表
	 	 * @param map
	 	 * @return
	 	 */
	 	public  List<PersonalProfile>  getjsloanPage(Map<String,Object> map);
	 	
		/**
	     *财务用户记录总数
	     * @return
	     */
	 	int getjsloanPageCount(String username,String rolename,String city,String parentnodeId);
	 	
	 	
	 	/**
	 	 * 转账列表
	 	 * @param map
	 	 * @return
	 	 */
	 	public  List<PersonalProfile>  getTransferloanPage(Map<String,Object> map);
	 	
		/**
	     *转账用户记录总数
	     * @return
	     */
	 	int getTransferloanPageCount(String username,String rolename,String city,String parentnodeId);
	 	
		/**
	 	 * 贷款信息列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getLoanInformation(Map<String, Object> map);
	 	
	 	/**
	 	 * 贷款信息列表城市经理
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getLoanInformationCity(Map<String, Object> map);
	 	
	 	/**
	 	 * 贷款信息统计
	 	 * @return
	 	 */
	 	int getLoanInformationCount(String username,String rolename,String city,String parentnodeId);

	 	/**
	 	 * 贷款信息统计城市经理
	 	 * @return
	 	 */
	 	int getLoanInformationCountCity(String username,String rolename,String city,String parentnodeId);
	 	
		/**
	 	 * 回款列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getReturnMoney(Map<String, Object> map);
	 	
	 	/**
	 	 * 回款统计
	 	 * @return
	 	 */
	 	int getReturnMoneyCount(String username,String rolename,String city,String parentnodeId);

		/**
	 	 * 取证列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getObtainEvidence(Map<String, Object> map);
	 	
	 	/**
	 	 * 取证统计
	 	 * @return
	 	 */
	 	int getObtainEvidenceCount(String username,String rolename,String city,String parentnodeId);

		/**
	 	 * 解压列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getDecompression(Map<String, Object> map);
	 	
	 	/**
	 	 * 解压统计
	 	 * @return
	 	 */
	 	int getDecompressionCount(String username,String rolename,String city,String parentnodeId);

		/**
	 	 * 进压列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getLoanPressure(Map<String, Object> map);
	 	
	 	/**
	 	 * 进压统计
	 	 * @return
	 	 */
	 	int getLoanPressureCount(String username,String rolename,String city,String parentnodeId);
	 	
	 	public PersonalProfile getSelectById(Map<String,Object> param);
	 	
	 	public List<PersonalProfile> checkHangData(Map<String,Object> param);
		int checkHangDataCount(String state);
		
		int getPersonalProfileCountAll(Map<Object,Object> map);

		List<PersonalProfile> vaguelikeSelectCreateoneAll(Map<Object,Object> param);
		List<PersonalProfile> vaguelikeSelectCreatethere(Map<Object,Object> param);

		PersonalProfile SelectByIdMarital(int id); 
}
