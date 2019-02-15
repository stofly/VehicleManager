package com.so.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.SytermLogDao;
import com.so.entity.CarInfo;
import com.so.entity.SytermLog;
import com.so.utils.PageHibernateCallback;

@Repository("sytermLogDao")
@Transactional
public class SytermLogDaoImpl extends MyHibernateDaoSupport implements SytermLogDao {

	@Override
	public Integer selectCountSytermLog() {
		// TODO Auto-generated method stub
		List<SytermLogDao> list = null;
		try {
			String hql = "from SytermLog";
			list = (List<SytermLogDao>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<SytermLog> selectAllSytermLogByLimt(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<SytermLog> list = null;
		String hql = "from SytermLog";
		list = (List<SytermLog>) this.getHibernateTemplate()
				.execute((HibernateCallback<SytermLog>) new PageHibernateCallback(hql, new Object[] {},
						rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public void saveSystermLog(SytermLog systermLog) {
		// TODO Auto-generated method stub

		if (systermLog != null || !systermLog.equals("")) {
			try {
				this.getHibernateTemplate().save(systermLog);
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
	}

	@Override
	public SytermLog findSytermLogById(Integer i) {
		// TODO Auto-generated method stub
		List<SytermLog> list = null;
		try {
			String hql = "from SytermLog WHERE sid ='" + i + "'";
			list = (List<SytermLog>) this.getHibernateTemplate().find(hql);
			return list.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer deleteSytermLog(SytermLog sytermLog) {
		// TODO Auto-generated method stub
		try {
			if (sytermLog != null || sytermLog.equals("")) {
				this.getHibernateTemplate().delete(sytermLog);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public Integer selectCountSytermLog(String logType, String startTime, String endTime) {
		// TODO Auto-generated method stub
		List<SytermLogDao> list = null;
		try {
			String hql = "from SytermLog where 1=1";
			if (logType != null) {
				hql += "and stype ='" + logType + "'";
			}
			if (startTime != null) {
				hql += "and stime BETWEEN'" + startTime + "'";
			}
			if (endTime != null) {
				hql += "and '" + endTime + "'";
			}
			list = (List<SytermLogDao>) this.getHibernateTemplate().find(hql);
			if (list != null || list.equals("")) {
				return list.size();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<SytermLog> selectAllSytermLogByLimt(Integer page, Integer rows, String logType, String startTime,
			String endTime) {
		List<SytermLog> list = null;
		try {
			String hql = "from SytermLog where 1=1";
			if (logType != null) {
				hql += "and stype ='" + logType + "'";
			}
			if (startTime != null) {
				hql += "and stime BETWEEN'" + startTime + "'";
			}
			if (endTime != null) {
				hql += "and '" + endTime + "'";
			}
			list = (List<SytermLog>) this.getHibernateTemplate()
					.execute((HibernateCallback<SytermLog>) new PageHibernateCallback(hql, new Object[] {},
							rows * (page - 1), rows));
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
}
