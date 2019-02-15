package com.so.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.so.dao.UserDao;
import com.so.entity.DriverInfo;
import com.so.entity.UserInfo;
import com.so.utils.PageHibernateCallback;

@Repository("userDao")
@Transactional
public class UserDaoImpl extends MyHibernateDaoSupport implements UserDao {

	@Override
	public void insertUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		try {
			if (userInfo != null) {
				this.getHibernateTemplate().save(userInfo);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}

	@Override
	public void updateUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		try {
			if (userInfo != null) {
				this.getHibernateTemplate().update(userInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
	}

	@Override
	public UserInfo selectUserByUser(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		List<UserInfo> userList = null;
		try {
			if (userInfo != null) {
				String hql = "from UserInfo where username='" + userInfo.getUsername() + "'and userpassword='"
						+ userInfo.getUserpassword() + "'";
				userList = (List<UserInfo>) this.getHibernateTemplate().find(hql);
				if (userList.get(0) != null) {
					return userList.get(0);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer selectCountUserInfo() {
		// TODO Auto-generated method stub
		List<DriverInfo> list = null;
		try {
			String hql = "from UserInfo";
			list = (List<DriverInfo>) this.getHibernateTemplate().find(hql);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return list.size();
	}

	@Override
	public List<UserInfo> selectAllUserInfoByLimt(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		String hql = "from UserInfo";
		list = (List<UserInfo>) this.getHibernateTemplate().execute(
				(HibernateCallback<UserInfo>) new PageHibernateCallback(hql, new Object[] {}, rows * (page - 1), rows));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public Integer saveUserInfo(UserInfo userInfo) {
		try {
			if (userInfo != null || !userInfo.equals("")) {
				Serializable save = this.getHibernateTemplate().save(userInfo);
				if (save != null || save != "") {
					return 1;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public Integer updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		try {
			if (userInfo != null) {
				this.getHibernateTemplate().update(userInfo);
				return 1;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return 0;
	}

	@Override
	public UserInfo findUserInfoById(Integer i) {
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		try {
			String hql = "from UserInfo WHERE uid ='" + i + "'";
			list = (List<UserInfo>) this.getHibernateTemplate().find(hql);
			return list.get(0);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer selectCountUserInfo(String selectname, String selecttype) {
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		String hql = "from UserInfo where 1=1";
		if (selectname != null) {
			hql += "and username like'%" + selectname + "%'";
		}
		if (selecttype != null) {
			hql += "and type='" + selecttype + "'";
		}
		try {
			list = (List<UserInfo>) this.getHibernateTemplate().find(hql);
			return list.size();
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public List<UserInfo> selectAllUserInfoByLimt(Integer page, Integer rows, String selectname, String selecttype) {
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		try {
			String hql = "from UserInfo where 1=1";
			if (selectname != null) {
				hql += "and username ='" + selectname + "'";
			}
			if (selecttype != null) {
				hql += "and type='" + selecttype + "'";
			}
			list = (List<UserInfo>) this.getHibernateTemplate()
					.execute((HibernateCallback<UserInfo>) new PageHibernateCallback(hql, new Object[] {},
							rows * (page - 1), rows));
			if (list != null && list.size() > 0) {
				return list;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

}