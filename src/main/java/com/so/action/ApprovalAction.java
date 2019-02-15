package com.so.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.so.entity.Approval;
import com.so.entity.CarInfo;
import com.so.entity.DriverInfo;
import com.so.entity.UserInfo;
import com.so.service.ApprovalService;
import com.so.service.CarService;
import com.so.service.DriverService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

import java.net.URLDecoder;

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
@Controller("approval")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class ApprovalAction extends ActionSupport {

	@Autowired
	private ApprovalService approvalService;

	@Autowired
	private CarService carService;

	@Autowired
	private DriverService driverService;

	@Action(value = "/addApproval")
	public String addApproval() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Approval approval = new Approval();
		String acnum = URLDecoder.decode(request.getParameter("acnum"), "utf-8");
		String actype = URLDecoder.decode(request.getParameter("actype"), "utf-8");
		String adrivername = URLDecoder.decode(request.getParameter("adrivername"), "utf-8");
		String reason = request.getParameter("reason");
		String startime = request.getParameter("startime");
		String endtime = request.getParameter("endtime");
		String sjid = request.getParameter("sjid");
		String cid = request.getParameter("cid");
		
		// 获取当前申请用户
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		approval.setAcnum(acnum);
		approval.setActype(actype);
		approval.setAdrivername(adrivername);
		approval.setAstate("待审核");
		approval.setApprovalperson(userInfo.getUsername());
		approval.setStartime(startime);
		approval.setEndtime(endtime);
		approval.setReason(reason);
		ManagerResult result = approvalService.saveApproval(approval);
		// 申请时，将所选的车辆和驾驶员都设为繁忙
		CarInfo carInfo = carService.findAllCarInfoById(cid);
		carInfo.setCstate("繁忙");
		carService.updateCarInfo(carInfo);
		DriverInfo driverInfo = driverService.findAllDriverInfoById(sjid);
		driverInfo.setDriverstate("繁忙");
		driverService.updateDriverInfo(driverInfo);
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/approvalList")
	public String index() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		EasyUIDataGridResult result = approvalService.findAllApproval(page, rows);
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/approvalHistoryList")
	public String approvalHistoryList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		EasyUIDataGridResult result = approvalService.findAllApprovalHistoryList(page, rows);
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/approvalListByName")
	public String approvalListByName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		EasyUIDataGridResult result = approvalService.findApprovalListByName(page, rows, userInfo.getUsername());
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/updateApproval")
	public String updateApproval() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = null;
		UserInfo userInfo = (UserInfo) request.getSession().getAttribute("user");
		// 获得前台传过来的page和rows
		String astate = URLDecoder.decode(request.getParameter("astate"), "utf-8");
		String adesc = URLDecoder.decode(request.getParameter("adesc"), "utf-8");
		Integer aid = Integer.parseInt(request.getParameter("aid"));
		// 首先先将这条记录查出来
		if (astate != null && astate.equals("通过")) {
			Approval approval = approvalService.findApprovalById(aid);
			// 把状态修改、添加审核人、添加审核附言
			approval.setAstate(astate);
			approval.setAuditperson(userInfo.getUsername());
			approval.setAdesc(adesc);
			result = approvalService.updateApproval(approval);
//			CarInfo carInfo = carService.findAllCarInfoByCnum(approval.getAcnum());
//			carInfo.setCplan(approval.getReason());
//			carService.updateCarInfo(carInfo);
		} else {
			Approval approval = approvalService.findApprovalById(aid);
			// 把状态修改、添加审核人、添加审核附言
			approval.setAstate(astate);
			approval.setAuditperson(userInfo.getUsername());
			approval.setAdesc(adesc);
			result = approvalService.updateApproval(approval);
			CarInfo carInfo = carService.findAllCarInfoByCnum(approval.getAcnum());
			carInfo.setCstate("可用");
			carService.updateCarInfo(carInfo);
			DriverInfo driverInfo = driverService.findAllDriverInfoByDrivername(approval.getAdrivername());
			driverInfo.setDriverstate("可用");
			driverService.updateDriverInfo(driverInfo);
		}
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
	
	@Action(value = "/approvalListWhereNo")
	public String approvalListWhereNo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		Integer i = approvalService.findAllApprovalWhereNo();
		String jsonString = JSON.toJSONString(i);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

}