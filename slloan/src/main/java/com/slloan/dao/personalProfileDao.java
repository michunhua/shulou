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
	 	int getPersonalProfileCount();
	 	
	 	/**
	 	 * 初审列表查询
	 	 * @param param
	 	 * @return
	 	 */
	 	List<PersonalProfile> vaguelikeSelectCreateone(Map<String,String> param);

	 	/**
	 	 * 终审列表查询
	 	 * @param param
	 	 * @return
	 	 */
	 	List<PersonalProfile> vaguelikeSelectCreatetwo(Map<Object,Object> param);
	 	
	 	
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
	 	int getFirsttrialPageCount();
	 	
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
	 	int getFinalreviewPageCount();
	 	

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
	 	int getFinancePageCount();
	 	
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
	 	int getjsloanPageCount();
	 	
	 	
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
	 	int getTransferloanPageCount();
	 	
		/**
	 	 * 贷款信息列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getLoanInformation(Map<String, Object> map);
	 	
	 	/**
	 	 * 贷款信息统计
	 	 * @return
	 	 */
	 	int getLoanInformationCount();

		/**
	 	 * 回款列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getReturnMoney(Map<String, Object> map);
	 	
	 	/**
	 	 * 回款统计
	 	 * @return
	 	 */
	 	int getReturnMoneyCount();

		/**
	 	 * 取证列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getObtainEvidence(Map<String, Object> map);
	 	
	 	/**
	 	 * 取证统计
	 	 * @return
	 	 */
	 	int getObtainEvidenceCount();

		/**
	 	 * 解压列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getDecompression(Map<String, Object> map);
	 	
	 	/**
	 	 * 解压统计
	 	 * @return
	 	 */
	 	int getDecompressionCount();

		/**
	 	 * 进压列表
	 	 * @return
	 	 */
	 	public List<PersonalProfile> getLoanPressure(Map<String, Object> map);
	 	
	 	/**
	 	 * 进压统计
	 	 * @return
	 	 */
	 	int getLoanPressureCount();
	 	
	 	public PersonalProfile getSelectById(Map<String,Object> param);
}
