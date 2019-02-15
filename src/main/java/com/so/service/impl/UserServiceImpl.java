package com.so.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.UserDao;
import com.so.entity.DriverInfo;
import com.so.entity.UserInfo;
import com.so.service.UserService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public ManagerResult findUserInfo(UserInfo userInfo) throws Exception {
		// TODO Auto-generated method stub
		try {
			UserInfo userinfos = userDao.selectUserByUser(userInfo);
			if (userinfos.getUstates().equals("正常")) {
				if (userinfos == null || userinfos.equals("")) {
					return new ManagerResult(400, "用户或密码错误", null);
				} else if (!userinfos.getType().equals(userInfo.getType())) {
					return new ManagerResult(300, "权限错误", null);
				} else {
					return new ManagerResult(200, null, userinfos);
				}
			} else {
				return new ManagerResult(400, "该用户被冻结", null);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public void updateUserPwdByIdAndUserName(UserInfo userInfo) {
		// TODO Auto-generated method stub
		try {
			if (userInfo != null || userInfo.equals("")) {
				userDao.updateUser(userInfo);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	@Override
	public EasyUIDataGridResult findAllUserInfo(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer userInfototal = userDao.selectCountUserInfo();
			List<UserInfo> userInfofoList = userDao.selectAllUserInfoByLimt(page, rows);
			result.setTotal(userInfototal);
			result.setRows(userInfofoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return result;

	}

	@Override
	public ManagerResult saveUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		if (userInfo != null || userInfo.equals("")) {
			try {
				Integer flage = userDao.saveUserInfo(userInfo);
				if (flage != 0) {
					return new ManagerResult(200, "添加成功", null);
				} else {
					return new ManagerResult(400, "添加失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public ManagerResult updateUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub
		if (userInfo != null || userInfo.equals("")) {
			try {
				Integer flage = userDao.updateUserInfo(userInfo);
				if (flage != 0) {
					return new ManagerResult(200, "修改成功", null);
				} else {
					return new ManagerResult(400, "修改失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public ManagerResult deteleUserInfo(Integer i) {
		// TODO Auto-generated method stub
		if (i != null || i.equals("")) {
			try {
				UserInfo userInfo = userDao.findUserInfoById(i);
				userInfo.setUstates("冻结");
				Integer flage = userDao.updateUserInfo(userInfo);
				if (flage != 0) {
					return new ManagerResult(200, "删除成功", null);
				} else {
					return new ManagerResult(400, "删除失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult findAllUserInfo(Integer page, Integer rows, String selectname, String selecttype) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer userInfototal = userDao.selectCountUserInfo(selectname,selecttype);
			List<UserInfo> userInfofoList = userDao.selectAllUserInfoByLimt(page, rows,selectname,selecttype);
			result.setTotal(userInfototal);
			result.setRows(userInfofoList);
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return result;
	}
}
