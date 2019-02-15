package com.so.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.so.entity.Menus;
import com.so.entity.UserInfo;
import com.so.service.MenuService;
import com.so.service.UserService;
import com.so.utils.ManagerResult;

import net.sf.json.JSONArray;

import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

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
@Controller("menu")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class MenuAction extends ActionSupport {
	@Autowired
	private MenuService menuService;

	/**
	 * 
	 * @Title: index
	 * @Description:菜单列表
	 * @return
	 * @throws Exception String
	 */
	@Action(value = "index")
	public String index() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		List<Menus> list = new LinkedList<Menus>();
		if (userInfo == null) {
			return "";
		}
		List<Menus> findMenuByUserType = menuService.findMenuByUserType(userInfo.getType());
		String jsonString = JSON.toJSONString(findMenuByUserType);
		String menusjson = "{\"menus\":" + jsonString + "}";
		System.out.println(menusjson);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(menusjson);
		return null;
	}
}