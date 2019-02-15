package com.so.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.so.entity.SytermLog;
import com.so.entity.UserInfo;
import com.so.service.SytermLogService;
import com.so.service.UserService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.GainIpUtils;
import com.so.utils.ManagerResult;

import java.net.URLDecoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("user")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class UserAction extends ActionSupport implements ModelDriven<UserInfo> {
	@Autowired
	private UserService userService;
	@Autowired
	private SytermLogService sytermLogService;

	UserInfo userInfo = new UserInfo();

	@Override
	public UserInfo getModel() {
		// TODO Auto-generated method stub
		if (userInfo != null) {
			return userInfo;
		}
		return null;
	}

	/**
	 * 
	 * @Title: userlogin
	 * @Description: 用户登录
	 * @return
	 * @throws Exception String
	 */
	@Action(value = "/userlogin")
	public String findAllCategorys() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = new UserInfo();
		String username = URLDecoder.decode(request.getParameter("username"), "utf-8");
		String userpassword = request.getParameter("userpassword");
		String type = URLDecoder.decode(request.getParameter("type"), "utf-8");
		userInfo.setType(type);
		userInfo.setUsername(username);
		userInfo.setUserpassword(userpassword);
		ManagerResult result = userService.findUserInfo(userInfo);
		request.getSession().setAttribute("user", result.getData());
		// 添加日志操作信息
		GainIpUtils gi = new GainIpUtils();
		String ip2 = gi.getIp2(request);
		SytermLog systermLog = new SytermLog();
		systermLog.setSname(username);
		systermLog.setStype(type);
		systermLog.setSip(ip2);
		systermLog.setStime(new Date());
		systermLog.setSdsc("登录");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是登录日志");
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/updateUserPwdByIdAndUserName")
	public String updateUserPwdByIdAndUserName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = new UserInfo();
		String username = URLDecoder.decode(request.getParameter("username"), "utf-8");
		String type = URLDecoder.decode(request.getParameter("type"), "utf-8");
		String userpassword = request.getParameter("userpassword");
		Integer uid = Integer.parseInt(URLDecoder.decode(request.getParameter("uid"), "utf-8"));
		userInfo.setUid(uid);
		userInfo.setUsername(username);
		userInfo.setUserpassword(userpassword);
		userInfo.setType(type);
		userService.updateUserPwdByIdAndUserName(userInfo);
		// 用户更新密码日志
		GainIpUtils gi = new GainIpUtils();
		String ip2 = gi.getIp2(request);
		SytermLog systermLog = new SytermLog();
		systermLog.setSname(username);
		systermLog.setStype(type);
		systermLog.setSip(ip2);
		systermLog.setStime(new Date());
		systermLog.setSdsc("更新密码");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是修改密码的日志");
		return null;
	}

	/**
	 * 
	 * @Title: userlogout
	 * @Description:安全退出
	 * @return
	 * @throws Exception String
	 */
	@Action(value = "userlogout", results = { @Result(name = SUCCESS, type = "redirect", location = "/login.jsp") })
	public String userlogout() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		UserInfo user = (UserInfo) session.getAttribute("user");
		// 用户退出登录日志
		GainIpUtils gi = new GainIpUtils();
		String ip2 = gi.getIp2(request);
		SytermLog systermLog = new SytermLog();
		systermLog.setSname(user.getUsername());
		systermLog.setStype(user.getType());
		systermLog.setSip(ip2);
		systermLog.setStime(new Date());
		systermLog.setSdsc("退出登录");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是退出登录的日志");
		// 清除整个session
		session.removeAttribute("user");
		return SUCCESS;
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 列出所有用户
	 * @return
	 * @throws Exception String
	 */
	@Action(value = "/userInfoList")
	public String index() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
//		// 获得前台传过来的page和rows
		EasyUIDataGridResult result = null;
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String selectname = request.getParameter("name");
		String selecttype = request.getParameter("type");
		System.out.println(selectname + selecttype);
		if (selectname == null || selectname.equals("") || selecttype == null || selecttype.equals("")) {
			result = userService.findAllUserInfo(page, rows);
		} else {
			if (selecttype.equals("null")) {
				selecttype = null;
			}
			result = userService.findAllUserInfo(page, rows, selectname, selecttype);
		}
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	/**
	 * 
	 * @Title: saveDriverInfo
	 * @Description: ba
	 * @return
	 * @throws Exception String
	 */
	@Action(value = "/addUserInfo")
	public String addUserInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = userService.saveUserInfo(userInfo);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/updateUserInfo")
	public String updateUserInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = userService.updateUserInfo(userInfo);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/deteleUserInfo")
	public String deteleUserInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = null;
		String str = request.getParameter("uid");
		String[] sourceStrArray = str.split(",");
		for (Integer i = 0; i < sourceStrArray.length; i++) {
			result = userService.deteleUserInfo(Integer.parseInt(sourceStrArray[i]));
		}
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
}