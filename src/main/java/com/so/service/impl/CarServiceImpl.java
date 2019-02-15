package com.so.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.CarDao;
import com.so.entity.CarInfo;
import com.so.entity.DriverInfo;
import com.so.service.CarService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

@Service("carService")
public class CarServiceImpl implements CarService {
	@Autowired
	private CarDao carDao;

	@Override
	public EasyUIDataGridResult findAllCarInfo(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer cartotal = carDao.selectCountCar();
			List<CarInfo> carInfoList = carDao.selectAllCarByLimt(page, rows);
			result.setTotal(cartotal);
			result.setRows(carInfoList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public ManagerResult saveCarInfo(CarInfo carInfo) {
		if (carInfo != null || carInfo.equals("")) {
			try {
				Integer flage = carDao.saveCarInfo(carInfo);
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
	public ManagerResult updateCarInfo(CarInfo carInfo) {
		if (carInfo != null || carInfo.equals("")) {
			try {
				Integer flage = carDao.updateCarInfo(carInfo);
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
	public ManagerResult updateCarInfo(Integer i) {
		// TODO Auto-generated method stub
		if (i != null || i.equals("")) {
			try {
				CarInfo carInfo = carDao.findCarInfoById(i);
				carInfo.setCstate("冻结");
				Integer flage = carDao.updateCarInfo(carInfo);
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
	public EasyUIDataGridResult findCarInfoListByAble(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer cartotal = carDao.selectCountCarInfoListByAble();
			List<CarInfo> carInfoList = carDao.selectAllCarInfoListByAble(page, rows);
			result.setTotal(cartotal);
			result.setRows(carInfoList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public CarInfo findAllCarInfoById(String cid) {
		// TODO Auto-generated method stub
		try {
			if (cid!=null||!cid.equals("")) {
				Integer i = Integer.parseInt(cid);
				CarInfo findCarInfoById = carDao.findCarInfoById(i);
				return findCarInfoById;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public CarInfo findAllCarInfoByCnum(String acnum) {
		// TODO Auto-generated method stub
		try {
			if (acnum!=null||!acnum.equals("")) {
				CarInfo findCarInfoById = carDao.findCarInfoByCnum(acnum);
				return findCarInfoById;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<CarInfo> selectCarInfoByName(String str) {
		// TODO Auto-generated method stub
		try {
			if (str!=null||!str.equals("")) {
				List<CarInfo> carInfoList = carDao.selectCarInfoByName(str);
				return carInfoList;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult findAllCarInfo(Integer page, Integer rows, String cumstr) {
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer cartotal = carDao.selectCountCarInfoListByAble(cumstr);
			List<CarInfo> carInfoList = carDao.selectAllCarInfoListByAble(page, rows,cumstr);
			result.setTotal(cartotal);
			result.setRows(carInfoList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
}
