package com.so.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.MenusDao;
import com.so.entity.Menus;

@Repository("menusDao")
@Transactional
public class MenusDaoImpl extends MyHibernateDaoSupport implements MenusDao {

	@Override
	public List<Menus> selectMenusByMenuid(String type) throws Exception {
		// TODO Auto-generated method stub
		List<Menus> menusList = null;
		try {
			if (type != null) {
				String hql = "FROM Menus WHERE menuid IN (SELECT pmid FROM Permission WHERE pname = '"+type+"')";
				menusList = (List<Menus>) this.getHibernateTemplate().find(hql);
				return menusList;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<Menus> selectMenusByPid(Integer pid) {
		// TODO Auto-generated method stub
		List<Menus> menusList = null;
		try {
			if (pid != null) {
				String hql = "from Menus where pid='" + pid + "'";
				menusList = (List<Menus>) this.getHibernateTemplate().find(hql);
				return menusList;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}
}
