package com.so.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.DriverDao;
import com.so.entity.DriverInfo;
import com.so.utils.PageHibernateCallback;

@Repository("driverDao")
@Transactional
public class DriverDaoImpl extends MyHibernateDaoSupport implements DriverDao {

	@Override
	public Integer selectCountDriver() {
		// TODO Auto-generated method stub
		List<DriverInfo> list = null;
		try {
			String hql = "from DriverInfo WHERE driverstate = '" + "可用" +"'OR driverstate = '" + "繁忙" + "'";
			list = (List<DriverInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<DriverInfo> selectAllDriverByLimt(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<DriverInfo> list = null;
		String hql = "from DriverInfo WHERE driverstate = '" + "可用" + "'OR driverstate = '" + "繁忙" + "'";
		list = (List<DriverInfo>) this.getHibernateTemplate()
				.execute((HibernateCallback<DriverInfo>) new PageHibernateCallback(hql, new Object[] {},
						rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public DriverInfo findDriverInfoById(Integer i) {
		// TODO Auto-generated method stub
		List<DriverInfo> list = null;
		try {
			String hql = "from DriverInfo WHERE did ='" + i + "'";
			list = (List<DriverInfo>) this.getHibernateTemplate().find(hql);
			return list.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer saveDriverInfo(DriverInfo driverInfo) {
		// TODO Auto-generated method stub
		try {
			if (driverInfo != null) {
				Serializable save = this.getHibernateTemplate().save(driverInfo);
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
	public Integer updateDriverInfo(DriverInfo driverInfo) {
		// TODO Auto-generated method stub
		try {
			if (driverInfo != null) {
				this.getHibernateTemplate().update(driverInfo);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public List<DriverInfo> findAllDriverInfo() {
		// TODO Auto-generated method stub
		List<DriverInfo> list = null;
		try {
			String hql = "from DriverInfo WHERE driverstate = '可用'";
			list = (List<DriverInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list;
	}

	@Override
	public DriverInfo findDriverInfoByDrivername(String adrivername) {
		List<DriverInfo> list = null;
		try {
			String hql = "from DriverInfo WHERE drivername = '"+adrivername+"'";
			list = (List<DriverInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.get(0);
	}

}
