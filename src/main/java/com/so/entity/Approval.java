package com.so.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "approval")
public class Approval {
	@Id
	@Column(name = "aid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer aid;
	@Column(name = "approvalperson", length = 30)
	private String approvalperson;// 申请人
	@Column(name = "acnum", length = 10)
	private String acnum;// 车牌号码
	@Column(name = "actype", length = 30)
	private String actype;// 车辆类型
	@Column(name = "adrivername", length = 30)
	private String adrivername;// 司机
	@Column(name = "startime")
	private String startime;// 开始时间
	@Column(name = "endtime")
	private String endtime;// 返回时间
	@Column(name = "reason", length = 255)
	private String reason;// 事由
	@Column(name = "astate", length = 20)
	private String astate;// 审核状态（待审核，审核通过，审核未通过）
	@Column(name = "auditperson", length = 30)
	private String auditperson;// 审核人
	@Column(name = "adesc", length = 30)
	private String adesc;// 结果描述

	public Integer getAid() {
		return aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	public String getApprovalperson() {
		return approvalperson;
	}

	public void setApprovalperson(String approvalperson) {
		this.approvalperson = approvalperson;
	}

	public String getAcnum() {
		return acnum;
	}

	public void setAcnum(String acnum) {
		this.acnum = acnum;
	}

	public String getActype() {
		return actype;
	}

	public void setActype(String actype) {
		this.actype = actype;
	}

	public String getAdrivername() {
		return adrivername;
	}

	public void setAdrivername(String adrivername) {
		this.adrivername = adrivername;
	}

	public String getStartime() {
		return startime;
	}

	public void setStartime(String startime) {
		this.startime = startime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getAstate() {
		return astate;
	}

	public void setAstate(String astate) {
		this.astate = astate;
	}

	public String getAuditperson() {
		return auditperson;
	}

	public void setAuditperson(String auditperson) {
		this.auditperson = auditperson;
	}

	public String getAdesc() {
		return adesc;
	}

	public void setAdesc(String adesc) {
		this.adesc = adesc;
	}

	@Override
	public String toString() {
		return "Approval [aid=" + aid + ", approvalperson=" + approvalperson + ", acnum=" + acnum + ", actype=" + actype
				+ ", adrivername=" + adrivername + ", startime=" + startime + ", endtime=" + endtime + ", reason="
				+ reason + ", astate=" + astate + ", auditperson=" + auditperson + ", adesc=" + adesc + "]";
	}

}
