package com.so.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.PermissionDao;
import com.so.entity.Permission;

@Repository("permissionDao")
@Transactional
public class PermissionDaoImpl extends MyHibernateDaoSupport implements PermissionDao {

	@Override
	public List<Permission> selectPermissionByPname(Permission permission) {
		// TODO Auto-generated method stub
		List<Permission> permissionList = null;
		try {
			if (permission.getPname() != null) {
				String hql = "from Permission where pname='" + permission.getPname() + "'";
				permissionList = (List<Permission>) this.getHibernateTemplate().find(hql);
				return permissionList;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

}
