package com.so.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.CarDao;
import com.so.entity.CarInfo;
import com.so.entity.DriverInfo;
import com.so.utils.PageHibernateCallback;

@Repository("carDao")
@Transactional
public class CarDaoImpl extends MyHibernateDaoSupport implements CarDao {

	@Override
	public Integer selectCountCar() {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		try {
			String hql = "from CarInfo WHERE cstate = '" + "可用" + "'OR cstate = '" + "有计划" + "'OR cstate = '" + "繁忙"
					+ "'";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<CarInfo> selectAllCarByLimt(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		String hql = "from CarInfo WHERE cstate = '" + "可用" + "'OR cstate = '" + "有计划" + "'OR cstate = '" + "繁忙" + "'";
		list = (List<CarInfo>) this.getHibernateTemplate().execute(
				(HibernateCallback<CarInfo>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Integer saveCarInfo(CarInfo carInfo) {
		try {
			if (carInfo != null) {
				Serializable save = this.getHibernateTemplate().save(carInfo);
				if (save != null || save != "") {
					return 1;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public Integer updateCarInfo(CarInfo carInfo) {
		// TODO Auto-generated method stub
		try {
			if (carInfo != null) {
				this.getHibernateTemplate().update(carInfo);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public CarInfo findCarInfoById(Integer i) {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		try {
			String hql = "from CarInfo WHERE cid ='" + i + "'";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql);
			return list.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer selectCountCarInfoListByAble() {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		try {
			String hql = "from CarInfo WHERE cstate =  '可用'";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<CarInfo> selectAllCarInfoListByAble(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		String hql = "from CarInfo WHERE cstate = '可用'";
		list = (List<CarInfo>) this.getHibernateTemplate().execute(
				(HibernateCallback<CarInfo>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public CarInfo findCarInfoByCnum(String acnum) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		try {
			String hql = "from CarInfo WHERE cnum ='" + acnum + "'";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql);
			return list.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<CarInfo> selectCarInfoByName(String str) {
		List<CarInfo> list = null;
		try {
			String hql = "from CarInfo where cnum like ?";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql, '%' + str + '%');
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer selectCountCarInfoListByAble(String cumstr) {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		try {
			String hql = "from CarInfo WHERE cnum = '" +cumstr + "'";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<CarInfo> selectAllCarInfoListByAble(Integer page, Integer rows, String cumstr) {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		String hql = "from CarInfo WHERE cnum = '" +cumstr + "'";
		list = (List<CarInfo>) this.getHibernateTemplate().execute(
				(HibernateCallback<CarInfo>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
