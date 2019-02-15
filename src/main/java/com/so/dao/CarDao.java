package com.so.dao;

import java.util.List;

import com.so.entity.CarInfo;

public interface CarDao {

	/**
	 * 
	 * @Title: selectCountCar
	 * @Description:查询所车辆数
	 * @return Integer
	 */
	Integer selectCountCar();

	/**
	 * 
	 * @Title: selectAllCarByLimt
	 * @Description:分页查询的车辆
	 * @param page
	 * @param rows
	 * @return List<CarInfo>
	 */
	List<CarInfo> selectAllCarByLimt(Integer page, Integer rows);

	/**
	 * 
	 * @Title: saveCarInfo
	 * @Description: 保存新增车辆
	 * @param carInfo
	 * @return Integer
	 */
	Integer saveCarInfo(CarInfo carInfo);

	/**
	 * 
	 * @Title: updateCarInfo
	 * @Description: 更新车辆信息
	 * @param carInfo
	 * @return Integer
	 */
	Integer updateCarInfo(CarInfo carInfo);

	/**
	 * @Title: findCarInfoById
	 * @Description:根据id查询车辆信息
	 * @param i
	 * @return CarInfo
	 */
	CarInfo findCarInfoById(Integer i);
	/**
	 * 
	 * @Title: selectCountCarInfoListByAble  
	 * @Description: 查询可用车辆记录数
	 * @return
	 * Integer
	 */
	Integer selectCountCarInfoListByAble();
	/**
	 * 
	 * @Title: selectAllCarInfoListByAble  
	 * @Description: 查询可用车辆信息
	 * @param page
	 * @param rows
	 * @return
	 * List<CarInfo>
	 */
	List<CarInfo> selectAllCarInfoListByAble(Integer page, Integer rows);

	CarInfo findCarInfoByCnum(String acnum);

	List<CarInfo> selectCarInfoByName(String str);

	Integer selectCountCarInfoListByAble(String cumstr);

	List<CarInfo> selectAllCarInfoListByAble(Integer page, Integer rows, String cumstr);

}
