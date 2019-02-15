package com.so.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.so.entity.DriverInfo;
import com.so.entity.SytermLog;
import com.so.entity.UserInfo;
import com.so.service.DriverService;
import com.so.service.SytermLogService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.GainIpUtils;
import com.so.utils.ManagerResult;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@SuppressWarnings("serial")
@Controller("driver")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class DriverAction extends ActionSupport implements ModelDriven<DriverInfo> {
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private SytermLogService sytermLogService;

	DriverInfo driverInfo = new DriverInfo();

	@Override
	public DriverInfo getModel() {
		if (driverInfo != null) {
			return driverInfo;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Action(value = "/driverInfoList")
	public String index() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		EasyUIDataGridResult result = driverService.findAllDriverInfo(page, rows);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		GainIpUtils gi = new GainIpUtils();
		String ip2 = gi.getIp2(request);
		SytermLog systermLog = new SytermLog();
		systermLog.setSname(user.getUsername());
		systermLog.setStype(user.getType());
		systermLog.setSip(ip2);
		systermLog.setStime(new Date());
		systermLog.setSdsc("查看驾驶员信息");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是查看驾驶员信息的日志");
		return null;
	}
	@Action(value = "/findAllDriverInfoName")
	public String findAllDriverInfoName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		List<DriverInfo> driverInfoList = driverService.findAllDriverInfo();
		String jsonString = JSON.toJSONString(driverInfoList);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
	

	@Action(value = "/saveDriverInfo")
	public String saveDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = driverService.saveDriverInfo(driverInfo);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/updateDriverInfo")
	public String updateDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = driverService.updateDriverInfo(driverInfo);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		GainIpUtils gi = new GainIpUtils();
		String ip2 = gi.getIp2(request);
		SytermLog systermLog = new SytermLog();
		systermLog.setSname(user.getUsername());
		systermLog.setStype(user.getType());
		systermLog.setSip(ip2);
		systermLog.setStime(new Date());
		systermLog.setSdsc("更新驾驶员信息");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是更新驾驶员的日志");
		return null;
	}

	@Action(value = "/deteleDriverInfo")
	public String deteleDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = null;
		String str = request.getParameter("did");
		String[] sourceStrArray = str.split(",");
		for (Integer i = 0; i < sourceStrArray.length; i++) {
			result = driverService.updateDriverInfo(Integer.parseInt(sourceStrArray[i]));
		}
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
	
	

}