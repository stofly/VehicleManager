package com.so.dao;

import java.util.List;

import com.so.entity.Menus;

public interface MenusDao {
	/**
	 * 
	 * @Title: selectMenusByPid
	 * @Description: 根据菜单id查询菜单
	 * @param pmid
	 * @return
	 * @throws Exception List<Menus>
	 */
	List<Menus> selectMenusByMenuid(String type) throws Exception;
	
	/**
	 * 
	 * @Title: selectMenusByPid  
	 * @Description: 根据pid查询子菜单
	 * @param pid
	 * @return
	 * List<Menus>
	 */
	List<Menus> selectMenusByPid(Integer pid);


}
