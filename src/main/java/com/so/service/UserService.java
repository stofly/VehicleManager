package com.so.service;

import com.so.entity.UserInfo;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

public interface UserService {
	/**
	 * 
	 * @Title: findUserInfo
	 * @Description: 根据用户名和密码查询用户
	 * @param userInfo
	 * @return
	 * @throws Exception ManagerResult
	 */
	public ManagerResult findUserInfo(UserInfo userInfo) throws Exception;

	/**
	 * 
	 * @Title: updateUserPwdByIdAndUserName
	 * @Description: 根据id和姓名修改密码
	 * @param userInfo void
	 */
	public void updateUserPwdByIdAndUserName(UserInfo userInfo);

	/**
	 * 
	 * @Title: findAllUserInfo
	 * @Description: 查询所有用户
	 * @param page
	 * @param rows
	 * @return EasyUIDataGridResult
	 */
	public EasyUIDataGridResult findAllUserInfo(Integer page, Integer rows);

	/**
	 * 
	 * @Title: saveUserInfo
	 * @Description: 添加用户
	 * @param userInfo
	 * @return ManagerResult
	 */
	public ManagerResult saveUserInfo(UserInfo userInfo);

	/**
	 * 
	 * @Title: updateUserInfo
	 * @Description: 更新用户
	 * @param userInfo
	 * @return ManagerResult
	 */
	public ManagerResult updateUserInfo(UserInfo userInfo);

	/**
	 * 
	 * @Title: deteleUserInfo
	 * @Description: 删除用户
	 * @param parseInt
	 * @return ManagerResult
	 */
	public ManagerResult deteleUserInfo(Integer i);

	public EasyUIDataGridResult findAllUserInfo(Integer page, Integer rows, String selectname, String selecttype);

}
