package com.so.utils;

import java.io.Serializable;
import java.util.List;
/**
 * easyui分页
 * @author Administrator
 *
 */
public class EasyUIDataGridResult implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer total;
	
	private List<?> rows;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
