package com.so.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.so.dao.UserDao;
import com.so.entity.UserInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserDaoImplTest {
	@Autowired
	private UserDao userDao;

	@Test
	public void testSelectAllUserInfoByLimtIntegerIntegerStringString() {
		List<UserInfo> selectAllUserInfoByLimt = userDao.selectAllUserInfoByLimt(1, 10, "李园青",null);
		for (UserInfo userInfo : selectAllUserInfoByLimt) {
			System.out.println(userInfo);
		}
	}

}
