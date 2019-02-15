package com.so.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sytermlog")
public class SytermLog {
	@Id
	@Column(name = "sid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer sid;// 日志主键
	@Column(name = "sname", length = 30)
	private String sname;// 操作人姓名
	@Column(name = "stype", length = 20)
	private String stype;// 操作人类型
	@Column(name = "sip", length = 50)
	private String sip;// 操作人ip
	@Column(name = "sdsc", length = 30)
	private String sdsc;// 操作内容
	@Column(name = "stime")
	private Date stime;// 操作人ip

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getSip() {
		return sip;
	}

	public void setSip(String sip) {
		this.sip = sip;
	}

	public String getSdsc() {
		return sdsc;
	}

	public void setSdsc(String sdsc) {
		this.sdsc = sdsc;
	}

	public Date getStime() {
		return stime;
	}

	public void setStime(Date stime) {
		this.stime = stime;
	}

	@Override
	public String toString() {
		return "SytermLog [sid=" + sid + ", sname=" + sname + ", stype=" + stype + ", sip=" + sip + ", sdsc=" + sdsc
				+ ", stime=" + stime + "]";
	}

}
