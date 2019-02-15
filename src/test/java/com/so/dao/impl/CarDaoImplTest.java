package com.so.dao.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.so.dao.CarDao;
import com.so.entity.CarInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class CarDaoImplTest {
	@Autowired
	private  CarDao carDao;

	@Test
	public void testSelectCarInfoByName() {
		List<CarInfo> selectCarInfoByName = carDao.selectCarInfoByName("豫");
		for (CarInfo carInfo : selectCarInfoByName) {
			System.out.println(carInfo);
		}
	}
	@Test
	public void testSelectCarInfoByName1() {
		 Integer selectCountCarInfoListByAble = carDao.selectCountCarInfoListByAble("豫D88881");
		 System.out.println(selectCountCarInfoListByAble);
	}

}
