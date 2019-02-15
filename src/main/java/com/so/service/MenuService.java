package com.so.service;

import java.util.List;

import com.so.entity.Menus;

public interface MenuService {

	public List<Menus> findMenuByUserType(String type) throws Exception;

}
