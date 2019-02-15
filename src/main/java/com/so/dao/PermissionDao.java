package com.so.dao;

import java.util.List;

import com.so.entity.Menus;
import com.so.entity.Permission;

public interface PermissionDao {
	
	List<Permission> selectPermissionByPname(Permission permission);
}
