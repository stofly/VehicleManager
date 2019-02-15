package com.so.service.impl;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.MenusDao;
import com.so.dao.PermissionDao;
import com.so.entity.Menus;
import com.so.entity.Permission;
import com.so.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {
	@Autowired
	private PermissionDao permissionDao;
	@Autowired
	private MenusDao menusDao;

	@Override
	public List<Menus> findMenuByUserType(String type) throws Exception {
		// TODO Auto-generated method stub
		List<Menus> list = new LinkedList<Menus>();
		List<Menus> menusList = menusDao.selectMenusByMenuid(type);
		for (Menus menus : menusList) {
			List<Menus> menusList2 = menusDao.selectMenusByPid(menus.getMenuid());
			for (Menus menus2 : menusList2) {
				menus.getMenus().add(menus2);
			}
			list.add(menus);
		}
		return list;
	}
}
