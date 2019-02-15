package com.so.service;

import java.util.List;

import com.so.entity.DriverInfo;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

public interface DriverService {
	/**
	 * 
	 * @Title: findAllDriverInfo
	 * @Description: 查询所有的驾驶员基本信息
	 * @param page
	 * @param rows
	 * @return
	 * @throws Exception EasyUIDataGridResult
	 */
	EasyUIDataGridResult findAllDriverInfo(Integer page, Integer rows) throws Exception;

	/**
	 * 
	 * @Title: saveDriverInfo
	 * @Description:保存驾驶员信息
	 * @param driverInfo
	 * @return ManagerResult
	 */
	ManagerResult saveDriverInfo(DriverInfo driverInfo);

	/**
	 * 
	 * @Title: updateDriverInfo
	 * @Description: 更新驾驶员信息
	 * @param driverInfo
	 * @return ManagerResult
	 */
	ManagerResult updateDriverInfo(DriverInfo driverInfo);

	/**
	 * 
	 * @Title: updateDriverInfo
	 * @Description:根据驾驶员id删除驾驶员信息
	 * @param i void
	 */
	ManagerResult updateDriverInfo(Integer i);
	/**
	 * 
	 * @Title: findAllDriverInfo  
	 * @Description: TODO 
	 * @return
	 * EasyUIDataGridResult
	 */
	List<DriverInfo> findAllDriverInfo();

	DriverInfo findAllDriverInfoById(String sjid);

	DriverInfo findAllDriverInfoByDrivername(String adrivername);

}
