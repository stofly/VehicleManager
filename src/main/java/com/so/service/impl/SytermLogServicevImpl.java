package com.so.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.SytermLogDao;
import com.so.entity.CarInfo;
import com.so.entity.SytermLog;
import com.so.service.SytermLogService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

@Service("sytermLogService")
public class SytermLogServicevImpl implements SytermLogService {

	@Autowired
	private SytermLogDao sytermLogDao;

	@Override
	public EasyUIDataGridResult findAllSytermLog(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer sytermLogtotal = sytermLogDao.selectCountSytermLog();
			List<SytermLog> driverInfoList = sytermLogDao.selectAllSytermLogByLimt(page, rows);
			result.setTotal(sytermLogtotal);
			result.setRows(driverInfoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return result;
	}

	@Override
	public void saveSystermLog(SytermLog systermLog) {
		// TODO Auto-generated method stub
		if (systermLog != null || !systermLog.equals("")) {
			sytermLogDao.saveSystermLog(systermLog);
		}

	}

	@Override
	public ManagerResult deleteSytermLog(Integer i) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (i != null || i.equals("")) {
			try {
				SytermLog sytermLog = sytermLogDao.findSytermLogById(i);
				Integer flage = sytermLogDao.deleteSytermLog(sytermLog);
				if (flage != 0) {
					return new ManagerResult(200, "删除成功", null);
				} else {
					return new ManagerResult(400, "删除失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult findAllSytermLogWhereConditions(Integer page, Integer rows, String logType,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer sytermLogtotal = sytermLogDao.selectCountSytermLog(logType,startTime,endTime);
			List<SytermLog> driverInfoList = sytermLogDao.selectAllSytermLogByLimt(page, rows,logType,startTime,endTime);
			result.setTotal(sytermLogtotal);
			result.setRows(driverInfoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return result;
	}

}
