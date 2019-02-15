package com.so.dao;

import java.util.List;

import com.so.entity.DriverInfo;

public interface DriverDao {

	/**
	 * @Title: selectCountDriver
	 * @Description: 查询驾驶员信息总数
	 * @return Integer
	 */
	Integer selectCountDriver();

	/**
	 * 
	 * @Title: selectAllDriverByLimt
	 * @Description:分页查询
	 * @param page
	 * @param rows
	 * @return List<DriverInfo>
	 */

	List<DriverInfo> selectAllDriverByLimt(Integer page, Integer rows);

	/**
	 * 
	 * @Title: saveDriverInfo
	 * @Description: 保存驾驶员信息
	 * @param driverInfo
	 * @return Integer
	 */
	Integer saveDriverInfo(DriverInfo driverInfo);

	/**
	 * 
	 * @Title: updateDriverInfo
	 * @Description: 更新驾驶员信息
	 * @param driverInfo
	 * @return Integer
	 */

	Integer updateDriverInfo(DriverInfo driverInfo);

	/**
	 * 
	 * @Title: findDriverInfoById
	 * @Description: 根据id查询驾驶员信息
	 * @param i
	 * @return DriverInfo
	 */
	DriverInfo findDriverInfoById(Integer i);

	/**
	 * @Title: findAllDriverInfo
	 * @Description: TODO
	 * @return List<DriverInfo>
	 */
	List<DriverInfo> findAllDriverInfo();

	DriverInfo findDriverInfoByDrivername(String adrivername);

}
