package com.so.dao;

import java.util.List;

import com.so.entity.IoRecord;

public interface InAndOutDao {

	Integer selectCountIoRecord();

	List<IoRecord> selectAllIoRecordByLimt(Integer page, Integer rows);

	IoRecord findIoRecordById(Integer i);

	Integer deleteIoRecord(IoRecord ioRecord);

	Integer saveIoRecord(IoRecord ioRecord);

}
