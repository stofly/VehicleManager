package com.so.dao;

import java.util.List;

import com.so.entity.UserInfo;

public interface UserDao {
	/**
	 * 
	 * @Title: insertUser
	 * @Description: 添加用户
	 * @param userInfo
	 * @throws Exception void
	 */
	public void insertUser(UserInfo userInfo) throws Exception;


	/**
	 * 
	 * @Title: updateUser
	 * @Description: 修改用户
	 * @param userInfo
	 * @return
	 * @throws Exception int
	 */
	public void updateUser(UserInfo userInfo) throws Exception;

	/**
	 * 
	 * @Title: selectUserByUser
	 * @Description: 根据条件查询用户
	 * @param userInfo
	 * @return
	 * @throws Exception User
	 */
	public UserInfo selectUserByUser(UserInfo userInfo) throws Exception;
	/**
	 * 
	 * @Title: selectCountUserInfo  
	 * @Description: 查询用户数量 
	 * @return
	 * Integer
	 */
	public Integer selectCountUserInfo();

	/**
	 * 
	 * @Title: selectAllUserInfoByLimt  
	 * @Description: 分页查询所有用户
	 * @param page
	 * @param rows
	 * @return
	 * List<UserInfo>
	 */
	public List<UserInfo> selectAllUserInfoByLimt(Integer page, Integer rows);

	/**
	 * 
	 * @Title: saveUserInfo  
	 * @Description: 添加用户信息 
	 * @param userInfo
	 * @return
	 * Integer
	 */
	public Integer saveUserInfo(UserInfo userInfo);
	/**
	 * 
	 * @Title: updateUserInfo  
	 * @Description:更新用户信息
	 * @param userInfo
	 * @return
	 * Integer
	 */

	public Integer updateUserInfo(UserInfo userInfo);

	/**
	 * 
	 * @Title: findUserInfoById  
	 * @Description:根据id查找
	 * @param i
	 * @return
	 * UserInfo
	 */
	public UserInfo findUserInfoById(Integer i);


	public Integer selectCountUserInfo(String selectname, String selecttype);


	public List<UserInfo> selectAllUserInfoByLimt(Integer page, Integer rows, String selectname, String selecttype);

}
