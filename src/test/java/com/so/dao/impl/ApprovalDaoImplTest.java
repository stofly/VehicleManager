package com.so.dao.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.aspectj.SpringConfiguredConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.so.dao.ApprovalDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ApprovalDaoImplTest {
	@Autowired
	private  ApprovalDao approvalDao;
	
	@Test
	public void testFindAllApprovalWhereNo() {
		Integer findAllApprovalWhereNo = approvalDao.findAllApprovalWhereNo();
		System.out.println(findAllApprovalWhereNo);
	}

}
