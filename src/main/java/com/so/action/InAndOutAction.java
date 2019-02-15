package com.so.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.so.entity.CarInfo;
import com.so.entity.IoRecord;
import com.so.entity.SytermLog;
import com.so.entity.UserInfo;
import com.so.service.CarService;
import com.so.service.InAndOutService;
import com.so.service.SytermLogService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.GainIpUtils;
import com.so.utils.ManagerResult;

@SuppressWarnings("serial")
@Controller("inAndOut")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class InAndOutAction extends ActionSupport implements ModelDriven<IoRecord> {
	@Autowired
	private InAndOutService inAndOutService;
	
	@Autowired
	private SytermLogService sytermLogService;

	IoRecord ioRecord = new IoRecord();

	@Override
	public IoRecord getModel() {
		if (ioRecord != null) {
			return ioRecord;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Action(value = "/inAndOutList")
	public String inAndOutList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
//		大小
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		
		EasyUIDataGridResult result = inAndOutService.findAllIoRecord(page, rows);
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
		systermLog.setSdsc("查看进出信息");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上查看进出信息的日志");
		return null;
	}

	@Action(value = "/saveIoRecord")
	public String saveIoRecord() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//接受石腾飞前台传过来的数据
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		ioRecord.setRegisterp(userInfo.getUsername());
		//获取当前门卫登录的那用户SESSION在把名字set到实体类中
		ManagerResult result = inAndOutService.saveIoRecord(ioRecord);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/deteleInAndOut")
	public String deteleDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = null;
		String str = request.getParameter("rid");
		String[] sourceStrArray = str.split(",");
		for (Integer i = 0; i < sourceStrArray.length; i++) {
			result = inAndOutService.deleteIoRecord(Integer.parseInt(sourceStrArray[i]));
		}
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

}
