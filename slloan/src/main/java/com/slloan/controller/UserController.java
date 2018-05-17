package com.slloan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.slloan.constants.CaptchaConstants;
import com.slloan.entity.AddRole;
import com.slloan.entity.Page;
import com.slloan.entity.PermissionEntity;
import com.slloan.entity.ResultList;
import com.slloan.entity.UserLogin;
import com.slloan.service.inter.AddPermissionService;
import com.slloan.service.inter.LoginService;
import com.slloan.service.inter.RoleAddService;
import com.slloan.service.inter.UserService;
import com.slloan.util.DateUtils;
import com.slloan.util.GraphicHelper;
import com.slloan.util.Json;

import net.sf.json.JSONObject;
import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;

/**
 * 用户列表
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userservice;
	@Autowired
	private RoleAddService roleAddService;
	@Autowired
	private AddPermissionService addPermissionService;
	@Autowired
	HttpServletRequest req;

	@Value("${admin}")
	public String admin; 
	/**
	 * 用户列表展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/userlist", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String userLlist(HttpServletRequest req) {
		String json = req.getParameter("data");
		JSONObject jsonobj = new JSONObject().fromObject(json);
		String page = req.getParameter("page");
		String limit = req.getParameter("limit");
		int startPos = Integer.parseInt(page);
		int pageSize = Integer.parseInt(limit);
		String parentid = jsonobj.getString("parentid");
		String username = jsonobj.getString("username");
		String id = jsonobj.getString("id");
		if (username.contains(admin)) {
			return JSON.toJSONString(userservice.getUserByPage(startPos));
		}
		return JSON.toJSONString(userservice.getUserByPage(startPos, id));
	}

	/**
	 * 用户列表展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "/selectrolecity", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json selectrolecity(HttpServletRequest req) {
		String json = req.getParameter("data");
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonobj = new JSONObject().fromObject(json);
		String parentid = jsonobj.getString("parentid");
		String city = jsonobj.getString("city");
		String city2 = jsonobj.getString("city");
		List<AddRole> add = roleAddService.getselectByid(parentid, city, city2);
		if (add != null) {
			return new Json(true, "success", add, "");// JSON.toJSONString(addrole);
		} else {
			return new Json(false, "fail", add, "");// JSON.toJSONString(addrole);
		}

	}

	/**
	 * 初始化角色与城市
	 */
	/*
	 * @RequestMapping(value="/initrole",method=RequestMethod.GET,produces=
	 * "application/json;charset=utf-8")
	 * 
	 * @ResponseBody public String initrolecs(AddRole rid){ AddRole addrole =
	 * new AddRole(); addrole.setRoleName(rid.getRoleName());
	 * addrole.setBelongs_City(rid.getBelongs_City()); AddRole add =
	 * userservice.accordingtoroleCity(addrole); return JSON.toJSONString(add);
	 * }
	 */

	/**
	 * 用户添加
	 * 
	 * @return
	 */
	@RequestMapping(value = "/adduser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json addUser(HttpServletRequest req, UserLogin userlogin) {
		boolean isResult = false;
		try {
			String json = req.getParameter("data");
			JSONObject jsonobj = new JSONObject().fromObject(json);
			String username = jsonobj.getString("userName");
			String password = jsonobj.getString("password");
			String employeeis_Name = jsonobj.getString("employee");// 员工姓名
			String distribution_Role = jsonobj.getString("role");// 分配角色
			String belongs_City = jsonobj.getString("city");// 所属城市
			String note = jsonobj.getString("note");
			String spare = jsonobj.getString("parentid");
			String id = jsonobj.getString("id");
			String createDate = DateUtils.getInDateTime((new Date()));// 日期
			AddRole add = new AddRole();
			add.setRoleName(distribution_Role);// 角色
			add.setBelongs_City(belongs_City);// 城市
			AddRole rid = roleAddService.selectByRId(add);
			// initrolecs(rid);
			UserLogin u = new UserLogin(username, password, employeeis_Name, distribution_Role, belongs_City, note,
					rid.getId(), createDate, id);
			Map<Object, Object> selectUserName = new HashMap<Object, Object>();
			selectUserName.put("username", username);
			UserLogin selectuserName2 = userservice.selectroleUserName(selectUserName);
			if (selectuserName2 != null) {
				return new Json(false, "fail", selectuserName2, "用户名已存在插入失败");// JSON.toJSONString("用户名已存在插入失败");
																				// //new
																				// Json(false,"fail",selectuserName2,"用户名已存在插入失败");
			} else {
				isResult = userservice.addUser(u);
			}

		} catch (Exception e) {
			// logger.error("连接异常"+e);
			return new Json(false, "fail", e.getMessage(), "连接异常");
		}
		if (isResult == true) {
			logger.info("添加用户成功");
			return new Json(true, "success", isResult, "用户名添加成功");// JSON.toJSONString("success");
		} else
			logger.debug("添加用户失败");
		return new Json(false, "fail", isResult, "添加用户失败");// JSON.toJSONString("fail");

	}

	/***
	 * 得到所有角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getrole")
	@ResponseBody
	public Json getrole() {
		List<UserLogin> rolelist = userservice.list();
		if (rolelist != null) {
			return new Json(true, "success", rolelist);
		} else
			return new Json(false, "fail", null);
	}

	/***
	 * 得到所属城市与角色
	 * 
	 * @RequestMapping(value="/getprovince.do") public Json getprovince(){ Map
	 *                                          <String,String> p = new HashMap
	 *                                          <String,String>();
	 *                                          p.put("rolename", "");
	 *                                          userservice.accordingtoroleCity(
	 *                                          param); }
	 */

	/***
	 * 根据ID查所有
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/selectuserbyid", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json UserSelectById(HttpServletRequest req) {
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String uid = json.getString("id");
		int id = Integer.parseInt(uid);
		UserLogin isResult = userservice.selectUserById(id);
		if (isResult != null) {
			return new Json(true, "success", isResult);
		} else
			return new Json(false, "fail", isResult, "");
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deluser", method = RequestMethod.POST)
	@ResponseBody
	public Json delUser(HttpServletRequest req) {
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String deid = json.getString("id");
		int id = Integer.parseInt(deid);
		boolean isResult = userservice.deleteById(id);
		if (isResult == true) {
			return new Json(true, "success", isResult, "");// JSON.toJSONString(isResult);
		} else
			return new Json(false, "fail", isResult, "");// JSON.toJSONString(isResult);
	}

	/**
	 * 修改用户保存
	 * 
	 * @return
	 */
	@RequestMapping(value = "/modifyuser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updateUser(HttpServletRequest req) {
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String userName = json.getString("username");// 用户名
		String passWord = json.getString("password");
		String employeeis_Name = json.getString("employee");// 员工姓名
		String distribution_Role = json.getString("urolename");// 分配角色
		String belongs_City = json.getString("city");// 所属城市
		String updateDate = DateUtils.getInDateTime((new Date()));// 日期
		String note = json.getString("note");
		String r_id = json.getString("id");// 角色ID
		String uid = json.getString("uid");// 用户ID
		int id = Integer.parseInt(r_id);
		int uid2 = Integer.parseInt(uid);
		UserLogin userlogin = new UserLogin(userName, passWord, employeeis_Name, distribution_Role, belongs_City, note,
				updateDate, id, uid2);
		Map<Object, Object> param = new HashMap<Object, Object>();
		param.put("username", userName);
		UserLogin userUpdateAdd = userservice.selectroleUserName(param);

		if (userUpdateAdd == null) {
			return new Json(false, "fail", userUpdateAdd, "修改保存用户名存在修改成功");
		} else {
			boolean isResult = userservice.updateaddUser(userlogin);
			// boolean is =
			// roleAddService.updateRoleCity(distribution_Role,belongs_City,id);
			if (isResult == true) {
				return new Json(true, "success", isResult, "");
			} else
				return new Json(false, "fail", isResult, "");
		}

	}

	/**
	 * 修改查询
	 * 
	 * @param id
	 *            参数
	 * @return
	 */
	@RequestMapping(value = "/modifselect", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public String updateselectId(HttpServletRequest req) {
		String ddd = req.getParameter("datas");
		System.out.println(ddd);
		// System.out.println(username);
		String dataid = req.getParameter("datas");
		JSONObject json = new JSONObject().fromObject(dataid);
		String strid = json.getString("id");
		int modifid = Integer.parseInt(strid);
		UserLogin isResult = userservice.updateselectId(modifid);
		if (isResult != null) {
			return JSON.toJSONString(isResult);
		} else
			logger.info("修改失败");
		return JSON.toJSONString(isResult);
	}

	/**
	 * 登录
	 * 
	 * @param id
	 *            参数
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	@ResponseBody
	public Object updateselectId(HttpSession session, HttpServletRequest req, HttpServletResponse response)
			throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		// String data = req.getParameter("data");
		// JSONObject jsonobject = new JSONObject().fromObject(data);
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String paramcode = req.getParameter("code");
		String code = CaptchaController.codeyzm();
		System.out.println("控制台的验证码 " + code);

		List<Object> li = new ArrayList<Object>();
		UserLogin user = new UserLogin(username, password);
		UserLogin userlogin = userservice.logindelu(user);
		AddRole addrole = new AddRole();
		PermissionEntity permission = new PermissionEntity();
		ResultList<Object> pageBean = new ResultList<Object>();// 返回权限集合
		addrole.setId(userlogin.getR_id());
		addrole.setRoleName(userlogin.getDistribution_Role());// 分配角色
		addrole.setBelongs_City(userlogin.getBelongs_City());// 所属城市
		List<AddRole> listrole = roleAddService.find(addrole);
		for (AddRole role : listrole) {
			li.add(role);
			int zh = role.getId();
			String zhrole = String.valueOf(zh);
			permission.setR_id(zhrole);
		}

		List<PermissionEntity> rolepermission = addPermissionService.find(permission);
		for (PermissionEntity entity : rolepermission) {
			li.add(entity);
		}
		// li.add(addrole);
		li.add(userlogin);

		// List<AddRole> r = neW ARRAYLIST<ADDROLE>();
		// LIST<USERLOGIN> DD = new ArrayList<UserLogin>();
		// userlogin.setUserRole(r);
		if (userlogin != null) {
			if (paramcode.toLowerCase().equals(code.toLowerCase())) {
				ServletContext application = session.getServletContext();
				Map<String, String> loginMap = (Map<String, String>) application.getAttribute("loginMap");
				if (loginMap == null) {
					loginMap = new HashMap<>();
				}
				for (String key : loginMap.keySet()) {
					if (userlogin.getUserName().equals(key)) {
						if (session.getId().equals(loginMap.get(key))) {
							System.out.println("在同一地点多次登录");
							// 获取session的创建时间
							System.out.println(session.getCreationTime());
							// 获取上次与服务器交互时间
							System.out.println(session.getLastAccessedTime());
							// 获取session最大的不活动的间隔时间，以秒为单位120秒。
							System.out.println(session.getMaxInactiveInterval());
							// return JSON.toJSONString(username+"在同一地点多次登录！");
							return new Json(false, "fail", "在同一地点多次登录！", "multiplelogon");
						} else {
							// response.sendRedirect("/signin");
							System.out.println("异地登录被拒绝！");
							// 获取session的创建时间
							System.out.println(session.getCreationTime());
							// 获取上次与服务器交互时间
							System.out.println(session.getLastAccessedTime());
							// 获取session最大的不活动的间隔时间，以秒为单位120秒。
							System.out.println(session.getMaxInactiveInterval());
							// return
							// JSON.toJSONString(username+"异地登录被拒绝！该用户已经登录！");
							return new Json(false, "fail", "异地登录被拒绝！该用户已经登录！70秒后在登录", "loginrefusal");
						}
					}
				}
				loginMap.put(userlogin.getUserName(), session.getId());
				application.setAttribute("loginMap", loginMap);
				session.setAttribute("username", user.getUserName());
				// for(Object obj : li){
				// System.out.println(obj.toString());
				pageBean.setLists(li);
				// }

				// response.encodeRedirectURL("ulist");
				// return JSON.toJSONString("验证码输入证确登录成功:"+li);
				// return new
				// Json(true,"success","验证码输入证确登录成功:"+pageBean,"loginsuccess");
				return new Json(true, "success", pageBean, "loginsuccess");
			} else {
				// response.encodeRedirectURL("signin");
				// req.getRequestDispatcher("signin").forward(req, response);

				// return JSON.toJSONString("","验证码输入错误");
				return new Json(false, "fail", "验证码输入错误");
			}
			// return "fff";

		} else {
			// 登录失败
			System.out.println("登录失败！");
			session.setAttribute("tip", "登录失败！");
			// return JSON.toJSON(username+"登录失败");
			return new Json(false, "fail", "登录失败！");
		}

	}

	/**
	 * 验证码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping(value = CaptchaConstants.VERIFICATIONCODE, method = RequestMethod.GET)
	@ResponseBody
	protected static String service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得 当前请求 对应的 会话对象
		// String paramcode = request.getParameter("code");

		HttpSession session = request.getSession();
		String uri = request.getRequestURI();
		// System.out.println("hello"+uri);
		final int width = 125;
		final int height = 35;
		final String imgType = "jpg";
		final ServletOutputStream output = response.getOutputStream(); // 获得可以向客户端返回图片的输出流

		String code = GraphicHelper.create(width, height, imgType, output);
		System.out.println("验证码内容:" + code);
		// if(paramcode.toLowerCase().equals(code.toLowerCase())){
		// request.getRequestDispatcher("succsg").forward(request, response);
		// return JSON.toJSONString("验证码输入证确");
		// }else{
		// request.getRequestDispatcher("/fail.jsp").forward(request, response);
		// return JSON.toJSONString("验证码输入错误");
		// }
		// 创建验证码图片并返回图片上的字符串

		// 建立 uri 和 相应的 验证码 的关联 ( 存储到当前会话对象的属性中 )
		session.setAttribute(uri, code);

		System.out.println(session.getAttribute(uri));
		return JSON.toJSONString(code);
	}

	/***
	 * 密码修改
	 * 
	 * @param newpassword
	 *            新密码
	 * @param username
	 *            用户名
	 * @param oldpassword
	 *            旧密码
	 * @return
	 */
	@RequestMapping(value = "/updatepwd", method = (RequestMethod.POST), produces = "application/json;charset=utf-8")
	@ResponseBody
	public Json updatepwd(HttpServletRequest req) {

		String data = req.getParameter("data");
		JSONObject obj = new JSONObject().fromObject(data);
		String username = obj.getString("username");
		String passWord = obj.getString("oldpassword");
		String newpassword = obj.getString("newpassword");
		// UserLoginUpdate updateupdate = new UserLoginUpdate();
		boolean isResult = userservice.updatePassWord(username, newpassword, passWord);
		if (isResult == true) {
			return new Json(true, "success", isResult, "修改成功");// JSON.toJSONString(isResult);
		} else
			return new Json(false, "fail", isResult, "输入的密码错误或不正确");// JSON.toJSONString("旧密码或用户名不正确");
	}

	/**
	 * 用户列表分页
	 * 
	 * @param page
	 * @return
	 *//*
		 * @RequestMapping(value="/pagefy",method =
		 * RequestMethod.GET,produces="application/json;charset=utf-8")
		 * 
		 * @ResponseBody public String pagefy(HttpServletRequest req){ String
		 * page = req.getParameter("page"); String limit =
		 * req.getParameter("limit"); List<UserLogin> listuser= new
		 * ArrayList<UserLogin>(); int totalCount = (int)
		 * userservice.getCount(); int startPos= Integer.parseInt(page); int
		 * pageSize = Integer.parseInt(limit); if(pageNow !=null){ page = new
		 * PageBean(totalCount,Integer.parseInt(pageNow));
		 * userservice.getUserByPage(page.getStartPos(), page.getPageSize());
		 * return new Json(true,"success",page); }else if(pageNow ==null){ page
		 * = new Page(totalCount, 1);
		 * userservice.getUserByPage(page.getStartPos(), page.getPageSize());
		 * return new Json(true,"success",page); }else{ return new
		 * Json(true,"success",listuser); }
		 * 
		 * return JSON.toJSONString(userservice.getRolePage(startPos));
		 * 
		 * String id = req.getParameter("1"); //每页显示的条数 int pagesize
		 * =page.getPageSize(); int pageTimes; int startRow
		 * =(Integer.parseInt(id)-1)*pagesize;
		 * List<UserLogin>users=userservice.list(); users =
		 * userservice.getUserByPage(startRow, pagesize); if(users.size()%
		 * pagesize ==0){ pageTimes = users.size()/pagesize; }else{ pageTimes =
		 * users.size()/pagesize +1; } if(users.size()>0){ return new
		 * Json(true,"success",users.size()); }else if(pageTimes >=0){ return
		 * new Json(true,"success",pageTimes); }else if(users !=null){ return
		 * new Json(true,"success",users);//每页开始的第几条记录 }else return new
		 * Json(false,"fail",false);
		 * 
		 * 
		 * }
		 */

	// /**
	// * 登出 方法一
	// * @param session
	// * @return
	// */
	// @RequestMapping(value="/exit")
	// public String exit(HttpSession session){
	// session.invalidate();
	// return "index/index";
	// }

	/**
	 * 登出 方法二
	 * 
	 * @param session
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/exit")
	public String exit(HttpServletRequest req, HttpServletResponse res) throws IOException {
		UserLogin userlogin = (UserLogin) req.getSession().getAttribute("loginMap");
		// UserLogin username =
		// (UserLogin)req.getSession().getAttribute("username");
		// 清除页面缓存 在html页里
		res.setHeader("Pragma", "No-cache");
		res.setHeader("Cache-Control", "no-cache");
		res.setDateHeader("Expires", 0);
		res.flushBuffer();
		req.getSession().setMaxInactiveInterval(1);
		// 删除登录cookie
		// Cookie userNameCookie = new Cookie("loginUserName",
		// userlogin.getUserName());
		// Cookie passWordCokie = new
		// Cookie("loginPassowrd",userlogin.getPassWord());
		// userNameCookie.setMaxAge(0);//设置0为立即删除该cooke
		// userNameCookie.setPath("/");//删除指定路径的COOKIE，不设置该路径
		// passWordCokie.setMaxAge(0);
		// passWordCokie.setPath("/");
		// res.addCookie(userNameCookie);
		// res.addCookie(passWordCokie);

		Cookie[] cookie = req.getCookies();
		for (Cookie c : cookie) {
			// 方法一
			/*
			 * if("autoLogin".equals(c.getName())){ c.setMaxAge(0);
			 * res.addCookie(c); }
			 */
			// 方法二
			if (null == c) {
				System.out.println("没有cookie");
			} else {
				for (Cookie cc : cookie) {
					if (cc.getName().equals("loginMap")) {
						cc.setValue(null);
						cc.setMaxAge(0);
						cc.setPath("/");
						res.addCookie(cc);
						// 获取session的创建时间
						HttpSession session = req.getSession();
						System.out.println(session.getCreationTime());
						// 获取上次与服务器交互时间
						System.out.println(session.getLastAccessedTime());
						// 获取session最大的不活动的间隔时间，以秒为单位120秒。
						System.out.println(session.getMaxInactiveInterval());
						break;
					}
				}
			}
		}
		// session.invalidate();
		req.getSession().removeAttribute("loginMap");
		// req.getSession().removeAttribute("username");
		req.getSession().invalidate();// 清除 session 中的所有信息
		// request.getSession().setMaxInactiveInterval(1800);/*秒为单位，1800= 60*30
		// 即30分种*/
		return "index/index";
	}

	// 创建cookie，并将新cookie添加到“响应对象”response中。
	public void addCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie("name_test", "value_test");// 创建新cookie
		cookie.setMaxAge(5 * 60);// 设置存在时间为5分钟
		cookie.setPath("/");// 设置作用域
		response.addCookie(cookie);// 将cookie添加到response的cookie数组中返回给客户端
	}

	/***
	 * 主页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/signin")
	public String index() {
		return "index/index";
	}

	/***
	 * 用户列表
	 * 
	 * @return
	 */
	@RequestMapping(value = "/ulist")
	public String ulist() {
		return "useradd/userList";
	}

	/***
	 * 添加用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addu")
	public String addu() {
		// return "redirect:useradd/addUser";
		return "useradd/addUser";
	}

	/***
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "/delu")
	public String delu() {
		return "useradd/deleteUser";
	}

	/***
	 * 删除用户
	 * 
	 * @return
	 */
	@RequestMapping(value = "updateuser")
	public String updateUser() {
		return "useradd/updateUser";
	}

	/***
	 * 分配角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "allotuser")
	public String allotUser() {
		return "useradd/allotUser";
	}

	/***
	 * 个人信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "userinfo/updateuserInfo")
	public String personalmanagement() {
		return "userInfo/updateUserInfo";
	}

	/***
	 * 个人信息密码修改
	 * 
	 * @return
	 */
	@RequestMapping(value = "userinfo/updatepassword")
	public String updatePassword() {
		return "userInfo/updatePassword";
	}

	/***
	 * 跳转到firstPage.html
	 * 
	 * @return
	 */
	@RequestMapping(value = "firstPage")
	public String firstPage() {
		return "index/firstPage";
	}

	/***
	 * 跳转到expirytime.html
	 * 
	 * @return http://localhost:8089/slloan/user/gqtime
	 */
	@RequestMapping(value = "/expirytime")
	public String expirytime() {
		System.out.println("66666666666666666666666666666666");
		return "error/expirytime";
	}
}
