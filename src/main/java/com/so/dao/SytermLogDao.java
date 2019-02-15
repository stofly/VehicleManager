package com.so.dao;

import java.util.List;

import com.so.entity.SytermLog;

public interface SytermLogDao {
	/**
	 * 
	 * @Title: selectCountSytermLog
	 * @Description: 查询所有日志数量
	 * @return Integer
	 */
	Integer selectCountSytermLog();

	/**
	 * 
	 * @Title: selectAllSytermLogByLimt
	 * @Description:
	 * @param page
	 * @param rows
	 * @return List<SytermLog>
	 */

	List<SytermLog> selectAllSytermLogByLimt(Integer page, Integer rows);

	/**
	 * 
	 * @Title: saveSystermLog
	 * @Description: 插入日志
	 * @param systermLog void
	 */
	void saveSystermLog(SytermLog systermLog);

	SytermLog findSytermLogById(Integer i);

	Integer deleteSytermLog(SytermLog sytermLog);

	Integer selectCountSytermLog(String logType, String startTime, String endTime);

	List<SytermLog> selectAllSytermLogByLimt(Integer page, Integer rows, String logType, String startTime,
			String endTime);

}
