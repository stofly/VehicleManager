package com.so.action;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.so.entity.CarInfo;
import com.so.entity.SytermLog;
import com.so.entity.UserInfo;
import com.so.service.CarService;
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
@Controller("car")
@ParentPackage(value = "struts-default")
@Scope("prototype")
@Namespace(value = "/")
public class CarAction extends ActionSupport implements ModelDriven<CarInfo> {
	@Autowired
	private CarService carService;

	@Autowired
	private SytermLogService sytermLogService;

	CarInfo carInfo = new CarInfo();

	@Override
	public CarInfo getModel() {
		if (carInfo != null) {
			return carInfo;
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Action(value = "/carInfoList")
	public String index() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		EasyUIDataGridResult result =null;
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		String cumstr = request.getParameter("cum");
		if(cumstr==null||cumstr.equals("")) {
			result = carService.findAllCarInfo(page, rows);
		}else {
			result = carService.findAllCarInfo(page, rows,cumstr);
		}
		String jsonString = JSON.toJSONString(result);
		// 用户查看车辆信息日志
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		GainIpUtils gi = new GainIpUtils();
		String ip2 = gi.getIp2(request);
		SytermLog systermLog = new SytermLog();
		systermLog.setSname(user.getUsername());
		systermLog.setStype(user.getType());
		systermLog.setSip(ip2);
		systermLog.setStime(new Date());
		systermLog.setSdsc("查看车辆信息");
		sytermLogService.saveSystermLog(systermLog);
		System.out.println("以上是查看车辆信息的日志");
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
	@Action(value = "/carInfoListByAble")
	public String carInfoListByAble() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		// 获得前台传过来的page和rows
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer rows = Integer.parseInt(request.getParameter("rows"));
		EasyUIDataGridResult result = carService.findCarInfoListByAble(page, rows);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/saveCarInfo")
	public String saveDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println(carInfo);
		ManagerResult result = carService.saveCarInfo(carInfo);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}

	@Action(value = "/updateCarInfo")
	public String updateDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = carService.updateCarInfo(carInfo);
		String jsonString = JSON.toJSONString(result);
		System.out.println(jsonString);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		System.out.println("以上是更新驾驶员的日志");
		return null;
	}
	
	
	@Action(value = "/deteleCarInfo")
	public String deteleDriverInfo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		ManagerResult result = null;
		String str = request.getParameter("cid");
		String[] sourceStrArray = str.split(",");
		for (Integer i = 0; i < sourceStrArray.length; i++) {
			result = carService.updateCarInfo(Integer.parseInt(sourceStrArray[i]));
		}
		String jsonString = JSON.toJSONString(result);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
	@Action(value = "/selectCarInfoByName")
	public String selectCarInfoByName() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String str = request.getParameter("q");
	   	List<CarInfo> carInfoList = carService.selectCarInfoByName(str);
		String jsonString = JSON.toJSONString(carInfoList);
		response.setContentType("application/json;charset=utf-8");
		response.getWriter().print(jsonString);
		return null;
	}
	

}