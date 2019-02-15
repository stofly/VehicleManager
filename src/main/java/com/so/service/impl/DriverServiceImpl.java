package com.so.service.impl;

import java.util.List;

import org.apache.commons.lang.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.DriverDao;
import com.so.entity.CarInfo;
import com.so.entity.DriverInfo;
import com.so.service.DriverService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

@Service("driverService")
public class DriverServiceImpl implements DriverService {
	@Autowired
	private DriverDao driverDao;

	@Override
	public EasyUIDataGridResult findAllDriverInfo(Integer page, Integer rows) throws Exception {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer drivetotal = driverDao.selectCountDriver();
			List<DriverInfo> driverInfoList = driverDao.selectAllDriverByLimt(page, rows);
			result.setTotal(drivetotal);
			result.setRows(driverInfoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return result;
	}

	@Override
	public ManagerResult saveDriverInfo(DriverInfo driverInfo) {
		// TODO Auto-generated method stub
		if (driverInfo != null || driverInfo.equals("")) {
			try {
				Integer flage = driverDao.saveDriverInfo(driverInfo);
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

	@Override
	public ManagerResult updateDriverInfo(DriverInfo driverInfo) {
		// TODO Auto-generated method stub
		if (driverInfo != null || driverInfo.equals("")) {
			try {
				Integer flage = driverDao.updateDriverInfo(driverInfo);
				if (flage != 0) {
					return new ManagerResult(200, "修改成功", null);
				} else {
					return new ManagerResult(400, "修改失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public ManagerResult updateDriverInfo(Integer i) {
		// TODO Auto-generated method stub
		if (i != null || i.equals("")) {
			try {
				DriverInfo driverInfo = driverDao.findDriverInfoById(i);
				driverInfo.setDriverstate("冻结");
				Integer flage = driverDao.updateDriverInfo(driverInfo);
				if (flage != 0) {
					return new ManagerResult(200, "修改成功", null);
				} else {
					return new ManagerResult(400, "修改失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public List<DriverInfo> findAllDriverInfo() {
		// TODO Auto-generated method stub
		List<DriverInfo> findAllDriverInfo = null;
		try {
			findAllDriverInfo = driverDao.findAllDriverInfo();
			if (findAllDriverInfo != null || findAllDriverInfo.equals("")) {
				return findAllDriverInfo;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public DriverInfo findAllDriverInfoById(String sjid) {
		// TODO Auto-generated method stub
		try {
			if (sjid != null || !sjid.equals("")) {
				Integer i = Integer.parseInt(sjid);
				DriverInfo findDriverInfoById = driverDao.findDriverInfoById(i);
				return findDriverInfoById;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public DriverInfo findAllDriverInfoByDrivername(String adrivername) {
		// TODO Auto-generated method stub
		try {
			if (adrivername != null || !adrivername.equals("")) {
				DriverInfo findDriverInfoById = driverDao.findDriverInfoByDrivername(adrivername);
				return findDriverInfoById;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
}
