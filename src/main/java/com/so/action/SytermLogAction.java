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
import com.so.entity.SytermLog;
import com.so.entity.UserInfo;
import com.so.service.SytermLogService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.GainIpUtils;
import com.so.utils.ManagerResult;

@SuppressWarnings("serial")
@Controller("sytermLog")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class SytermLogAction extends ActionSupport implements ModelDriven<SytermLog> {

	@Autowired
	private SytermLogService sytermLogService;

	SytermLog sytermLog = new SytermLog();

	@Override
	public SytermLog getModel() {
		if (sytermLog != null) {
			return sytermLog;
		}
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @Title: index
	 * @Description: 列出所有日志
	 * @return
	 * @throws Exception String
	 */
	@Action(value = "/sytermLogList")
	public String sytermLogList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		EasyUIDataGridResult result =null;
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String logType = request.getParameter("logType");
		String startTime = request.getParameter("starttime");
		String endTime = request.getParameter("endtime");
		if (logType == null || logType.equals("") || startTime == null || startTime.equals("") || endTime == null
				|| endTime.equals("")) {
			result = sytermLogService.findAllSytermLog(page, rows);
		}else {
			if (logType.equals("null")) {
				logType = null;
			}
			result = sytermLogService.findAllSytermLogWhereConditions(page, rows,logType,startTime,endTime);
		}
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
		systermLog.setSdsc("查看日志信息");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是查看日志信息");
		return null;
	}

	@Action(value = "/deteleSytermLog")
	public String deteleSytermLog() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = null;
		String str = request.getParameter("sid");
		String[] sourceStrArray = str.split(",");
		for (Integer i = 0; i < sourceStrArray.length; i++) {
			result = sytermLogService.deleteSytermLog(Integer.parseInt(sourceStrArray[i]));
		}
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

}
