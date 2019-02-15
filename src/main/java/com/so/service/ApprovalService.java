package com.so.service;

import com.so.entity.Approval;
import com.so.utils.EasyUIDataGridResult;
import com.so.utils.ManagerResult;

public interface ApprovalService {
	/**
	 * 
	 * @Title: saveApproval
	 * @Description:保存申请信息
	 * @param approval
	 * @return ManagerResult
	 */
	ManagerResult saveApproval(Approval approval);

	/**
	 * 
	 * @Title: findAllApproval
	 * @Description:分页查询信息
	 * @param page
	 * @param rows
	 * @return EasyUIDataGridResult
	 */
	EasyUIDataGridResult findAllApproval(Integer page, Integer rows);
	/**
	 * 
	 * @Title: findApprovalById  
	 * @Description: 根据id查询审批记录 
	 * @param aid
	 * @return
	 * Approval
	 */
	Approval findApprovalById(Integer aid);
	/**
	 * 
	 * @Title: updateApproval  
	 * @Description: 更新记录
	 * @param approval
	 * @return
	 * EasyUIDataGridResult
	 */
	ManagerResult updateApproval(Approval approval);
	
	EasyUIDataGridResult findApprovalListByName(Integer page, Integer rows, String username);

	EasyUIDataGridResult findAllApprovalHistoryList(Integer page, Integer rows);

	Integer findAllApprovalWhereNo();

}
