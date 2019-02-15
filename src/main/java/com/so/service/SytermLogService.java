package com.so.service;

import com.so.entity.SytermLog;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

public interface SytermLogService {
	EasyUIDataGridResult findAllSytermLog(Integer page, Integer rows);

	void saveSystermLog(SytermLog systermLog);

	ManagerResult deleteSytermLog(Integer parseInt);

	EasyUIDataGridResult findAllSytermLogWhereConditions(Integer page, Integer rows, String logType, String startTime,
			String endTime);
}
