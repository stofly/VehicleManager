package com.so.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.InAndOutDao;
import com.so.entity.CarInfo;
import com.so.entity.IoRecord;
import com.so.service.InAndOutService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

@Service("inAndOutService")
public class InAndOutServiceImpl implements InAndOutService {
	@Autowired
	private InAndOutDao inAndOutDao;

	@Override
	public EasyUIDataGridResult findAllIoRecord(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer cartotal = inAndOutDao.selectCountIoRecord();
			List<IoRecord> ioRecordList = inAndOutDao.selectAllIoRecordByLimt(page, rows);
			result.setTotal(cartotal);
			result.setRows(ioRecordList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public ManagerResult deleteIoRecord(Integer i) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (i != null || !i.equals("")) {
			try {
				IoRecord ioRecord = inAndOutDao.findIoRecordById(i);
				Integer flage = inAndOutDao.deleteIoRecord(ioRecord);
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
	public ManagerResult saveIoRecord(IoRecord ioRecord) {
		// TODO Auto-generated method stub
		if (ioRecord != null || ioRecord.equals("")) {
			try {
				Integer flage = inAndOutDao.saveIoRecord(ioRecord);
				if (flage != 0) {
					return new ManagerResult(200, "添加成功", null);
				} else {
					return new ManagerResult(400, "添加失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

}
