package com.so.service;

import java.util.List;

import com.so.entity.CarInfo;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

public interface CarService {
	/**
	 * 
	 * @Title: findAllCarInfo
	 * @Description:查询所有车辆信息
	 * @param page
	 * @param rows
	 * @return EasyUIDataGridResult
	 */
	EasyUIDataGridResult findAllCarInfo(Integer page, Integer rows);

	/**
	 * 
	 * @Title: saveCarInfo
	 * @Description: 保存学生信息
	 * @param carInfo
	 * @return ManagerResult
	 */
	ManagerResult saveCarInfo(CarInfo carInfo);

	/**
	 * 
	 * @Title: updateDriverInfo
	 * @Description: 更新学生信息
	 * @param carInfo
	 * @return ManagerResult
	 */

	ManagerResult updateCarInfo(CarInfo carInfo);

	/**
	 * 
	 * @Title: updateDriverInfo
	 * @Description: 删除驾驶员，实质是改变状态
	 * @param parseInt
	 * @return ManagerResult
	 */
	ManagerResult updateCarInfo(Integer i);

	EasyUIDataGridResult findCarInfoListByAble(Integer page, Integer rows);

	CarInfo findAllCarInfoById(String cid);

	CarInfo findAllCarInfoByCnum(String acnum);

	List<CarInfo> selectCarInfoByName(String str);

	EasyUIDataGridResult findAllCarInfo(Integer page, Integer rows, String cumstr);

}
