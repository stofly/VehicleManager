package com.so.utils;

import java.io.Serializable;


/**
 * 全局返回值
 */
public class ManagerResult implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    public static ManagerResult build(Integer status, String msg, Object data) {
        return new ManagerResult(status, msg, data);
    }

    public static ManagerResult ok(Object data) {
        return new ManagerResult(data);
    }

    public static ManagerResult ok() {
        return new ManagerResult(null);
    }

    public ManagerResult() {

    }

    public static ManagerResult build(Integer status, String msg) {
        return new ManagerResult(status, msg, null);
    }

    public ManagerResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ManagerResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ManagerResult [status=" + status + ", msg=" + msg + ", data=" + data + "]";
	}
    

}
