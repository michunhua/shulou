package com.slloan.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.slloan.entity.Page;
import com.slloan.entity.UserRightsManagement;
import com.slloan.service.impl.UserManagement;

/**
 * 用户列表管理
 * @author Administrator
 *
 */
@Controller("userManagementController")
//@RequestMapping("/代款分页")
public class UserManagementController  {
	
	private static Logger logger = LoggerFactory.getLogger(UserManagementController.class);
	
	@Autowired
	public UserManagement userrightsManagement;
	
	/**
	 * 用户管理分页
	 * @return
	 */
	/*@RequestMapping("init.do")
	public String initPage(Page page,HttpServletRequest request){
		Page p  = page;
		int pageSize = 10;//设置大小
		p.setPageSize(pageSize);
		int curPage = p.getCurrentPage();
		if(curPage == 0){
			curPage=1;
            p.setCurrentPage(curPage);
		}
		int startRow =page.getBeginIndex();
		if(!(p.getCurrentPage() == 0)){
			startRow = getStartRowBycurrentPage(curPage, pageSize);
		}
		 p.setBeginIndex(startRow);
		 Integer totalCounts = userrightsManagement.searchTotalCount();//总条数
		 int totalPages=(totalCounts%pageSize==0)?(totalCounts/pageSize):(totalCounts/pageSize+1);//总页数=总条数/页大小+1
		 p.setPageCount(totalPages);
		 page.setBeginIndex(totalCounts);
		 request.setAttribute("page", page);
		return "页面";
	}*/

	@RequestMapping("delete.do")
	public String deleteUserId(Integer id){
		userrightsManagement.deleteUserId(id);
		return "";
	}
	/**
     * 根据当前页获取开始行
     * @param currentPage
     * @param pageSize
     * @return
     */
	private int getStartRowBycurrentPage(int curPage, int pageSize) {
		int startRow=0;
        
        if (curPage==1) {
            
            return startRow=0;
        }
        
        startRow=(curPage-1)*pageSize;
        
        return startRow;
        
	}

	@RequestMapping("update.do")
	public String updateId(UserRightsManagement user){
		boolean re = userrightsManagement.updateUserById(user);
		if(re == true){
			return "";
		}else
			return "";
		
		
	}
}
