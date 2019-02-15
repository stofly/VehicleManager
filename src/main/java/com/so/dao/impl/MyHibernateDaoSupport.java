package com.so.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

@Component("myHibernateDaoSupport")
public class MyHibernateDaoSupport extends HibernateDaoSupport {

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory){
        super.setSessionFactory(sessionFactory);
    }
}