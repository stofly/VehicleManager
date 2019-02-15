package com.so.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.so.dao.SytermLogDao;
import com.so.entity.SytermLog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class SytermLogDaoImplTest {
	@Autowired
	private SytermLogDao sytermLogDao;

	@Test
	public void testSelectCountSytermLogStringStringString() {
		List<SytermLog> selectAllSytermLogByLimt = sytermLogDao.selectAllSytermLogByLimt(1, 10, "超级管理员", "2018-12-28 11:03:03", "2018-12-28 11:33:02");
		for (SytermLog sytermLog : selectAllSytermLogByLimt) {
			System.out.println(sytermLog);
		}
	}

	@Test
	public void testSelectAllSytermLogByLimtIntegerIntegerStringStringString() {
		fail("Not yet implemented");
	}

}
