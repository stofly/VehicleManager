package com.so.service;

import com.so.entity.IoRecord;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

public interface InAndOutService {
	/**
	 * 
	 * @Title: findAllIoRecord
	 * @Description: 查询所有的进出记录
	 * @param page
	 * @param rows
	 * @return EasyUIDataGridResult
	 */
	EasyUIDataGridResult findAllIoRecord(Integer page, Integer rows);

	/**
	 * 
	 * @Title: deleteIoRecord
	 * @Description: 根据id删除记录
	 * @param parseInt
	 * @return ManagerResult
	 */

	ManagerResult deleteIoRecord(Integer i);

	/**
	 * 
	 * @Title: saveoRecord
	 * @Description: 保存进出记录
	 * @param ioRecord
	 * @return ManagerResult
	 */
	ManagerResult saveIoRecord(IoRecord ioRecord);

}
