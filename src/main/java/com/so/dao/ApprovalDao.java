package com.so.dao;

import java.util.List;

import com.so.entity.Approval;

public interface ApprovalDao {

	Integer saveApproval(Approval approval);
	
	Integer selectCountApproval();

	List<Approval> selectAllApprovalByLimt(Integer page, Integer rows);

	Approval selectApprovalById(Integer aid);

	Integer updateApproval(Approval approval);

	Integer selectCountApprovalListByName(String username);

	List<Approval> selectAllApprovalListByName(Integer page, Integer rows, String username);

	Integer selectCountApprovalHistoryList();

	List<Approval> selectAllApprovalHistoryListByLimt(Integer page, Integer rows);

	Integer findAllApprovalWhereNo();



}
