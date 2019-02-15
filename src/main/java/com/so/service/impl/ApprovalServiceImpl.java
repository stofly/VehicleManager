package com.so.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.so.dao.ApprovalDao;
import com.so.entity.Approval;
import com.so.entity.CarInfo;
import com.so.service.ApprovalService;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

@Service("approvalService")
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	private ApprovalDao approvalDao;

	@Override
	public ManagerResult saveApproval(Approval approval) {
		// TODO Auto-generated method stub
		if (approval != null || approval.equals("")) {
			try {
				Integer flage = approvalDao.saveApproval(approval);
				if (flage != 0) {
					return new ManagerResult(200, "待申请成功", null);
				} else {
					return new ManagerResult(400, "待申请失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult findAllApproval(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer approvaltotal = approvalDao.selectCountApproval();
			List<Approval> approvalInfoList = approvalDao.selectAllApprovalByLimt(page, rows);
			result.setTotal(approvaltotal);
			result.setRows(approvalInfoList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Approval findApprovalById(Integer aid) {
		// TODO Auto-generated method stub
		try {
			if (aid != null || aid.equals("")) {
				Approval approval = approvalDao.selectApprovalById(aid);
				if (approval != null || approval.equals("")) {
					return approval;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;

	}

	@Override
	public ManagerResult updateApproval(Approval approval) {
		// TODO Auto-generated method stub
		if (approval != null || approval.equals("")) {
			try {
				Integer flage = approvalDao.updateApproval(approval);
				if (flage != 0) {
					return new ManagerResult(200, "审核成功", null);
				} else {
					return new ManagerResult(400, "审核失败", null);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getMessage();
			}
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult findApprovalListByName(Integer page, Integer rows, String username) {
		// TODO Auto-generated method stubEasyUIDataGridResult result = new
		// EasyUIDataGridResult();
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer approvaltotal = approvalDao.selectCountApprovalListByName(username);
			List<Approval> approvalInfoList = approvalDao.selectAllApprovalListByName(page, rows, username);
			result.setTotal(approvaltotal);
			result.setRows(approvalInfoList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public EasyUIDataGridResult findAllApprovalHistoryList(Integer page, Integer rows) {
		// TODO Auto-generated method stub
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		try {
			Integer approvaltotal = approvalDao.selectCountApprovalHistoryList();
			List<Approval> approvalInfoList = approvalDao.selectAllApprovalHistoryListByLimt(page, rows);
			result.setTotal(approvaltotal);
			result.setRows(approvalInfoList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

	@Override
	public Integer findAllApprovalWhereNo() {
		// TODO Auto-generated method stub
		try {
			Integer i = approvalDao.findAllApprovalWhereNo();
			return i;
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}
		return null;
	}

}
