package com.so.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.ApprovalDao;
import com.so.entity.Approval;
import com.so.entity.CarInfo;
import com.so.utils.PageHibernateCallback;

@Repository("approvalDao")
@Transactional
public class ApprovalDaoImpl extends MyHibernateDaoSupport implements ApprovalDao {

	@Override
	public Integer saveApproval(Approval approval) {
		// TODO Auto-generated method stub
		try {
			if (approval != null || approval.equals("")) {
				Serializable save = this.getHibernateTemplate().save(approval);
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
	public Integer selectCountApproval() {
		List<Approval> list = null;
		try {
			String hql = "from Approval WHERE astate = '待审核'";
			list = (List<Approval>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<Approval> selectAllApprovalByLimt(Integer page, Integer rows) {
		List<Approval> list = null;
		String hql = "from Approval WHERE astate = '待审核'";
		list = (List<Approval>) this.getHibernateTemplate().execute(
				(HibernateCallback<Approval>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Approval selectApprovalById(Integer aid) {
		// TODO Auto-generated method stub
		List<Approval> list = null;
		try {
			String hql = "from Approval WHERE aid = '" + aid + "'";
			list = (List<Approval>) this.getHibernateTemplate().find(hql);
			if (list != null || list.equals("")) {
				return list.get(0);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer updateApproval(Approval approval) {
		// TODO Auto-generated method stub
		try {
			if (approval != null) {
				this.getHibernateTemplate().update(approval);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public Integer selectCountApprovalListByName(String username) {
		// TODO Auto-generated method stub
		List<Approval> list = null;
		try {
			String hql = "from Approval WHERE approvalperson = '" + username + "'";
			list = (List<Approval>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<Approval> selectAllApprovalListByName(Integer page, Integer rows, String username) {
		// TODO Auto-generated method stub
		List<Approval> list = null;
		String hql = "from Approval WHERE approvalperson = '" + username + "'";
		list = (List<Approval>) this.getHibernateTemplate().execute(
				(HibernateCallback<Approval>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Integer selectCountApprovalHistoryList() {
		List<Approval> list = null;
		try {
			String hql = "from Approval WHERE astate = '通过' or astate ='未通过'";
			list = (List<Approval>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<Approval> selectAllApprovalHistoryListByLimt(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<Approval> list = null;
		String hql = "from Approval WHERE astate = '通过' or astate ='未通过'";
		list = (List<Approval>) this.getHibernateTemplate().execute(
				(HibernateCallback<Approval>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Integer findAllApprovalWhereNo() {
		// TODO Auto-generated method stub
		List<Approval> list = null;
		String hql = "from Approval WHERE astate = '待审核'";
		list = (List<Approval>) this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.size();
		}
		return null;
	}
}
