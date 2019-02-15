package com.so.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.InAndOutDao;
import com.so.entity.CarInfo;
import com.so.entity.IoRecord;
import com.so.utils.PageHibernateCallback;

@Repository("inAndOutDao")
@Transactional
public class InAndOutDaoImpl extends MyHibernateDaoSupport implements InAndOutDao {

	@Override
	public Integer selectCountIoRecord() {
		// TODO Auto-generated method stub
		List<CarInfo> list = null;
		try {
			String hql = "from IoRecord";
			list = (List<CarInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<IoRecord> selectAllIoRecordByLimt(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<IoRecord> list = null;
		String hql = "from IoRecord";
		list = (List<IoRecord>) this.getHibernateTemplate().execute(
				(HibernateCallback<IoRecord>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Integer deleteIoRecord(IoRecord i) {
		// TODO Auto-generated method stub
		try {
			if (i != null || !i.equals("")) {
				this.getHibernateTemplate().delete(i);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public IoRecord findIoRecordById(Integer i) {
		// TODO Auto-generated method stub
		if (i != null || !i.equals("")) {
			String hql = "from IoRecord where rid='" + i + "'";
			List<IoRecord> ioRecordList = (List<IoRecord>) this.getHibernateTemplate().find(hql);
			if (ioRecordList != null && ioRecordList.size() > 0) {
				return ioRecordList.get(0);
			}
		}
		return null;
	}

	@Override
	public Integer saveIoRecord(IoRecord ioRecord) {
		// TODO Auto-generated method stub
		try {
			if (ioRecord != null) {
				Serializable save = this.getHibernateTemplate().save(ioRecord);
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

}
