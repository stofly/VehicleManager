package com.so.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "driverinfo")
public class DriverInfo {
	@Id
	@Column(name = "did")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer did;
	@Column(name = "drivername", length = 30)
	private String drivername;// 驾驶员姓名
	@Column(name = "drivertel", length = 11)
	private String drivertel;// 驾驶员联系方式
	@Column(name = "driverid", length = 12)
	private String driverid;// 驾照编号
	@Column(name = "driverage",length = 20)
	private String driverage;// 驾龄
	@Column(name = "drivertype", length = 20)
	private String drivertype;// 驾驶类型
	@Column(name = "drivergrade", length = 20)
	private String drivergrade;// 等级
	@Column(name = "driverstate", length = 50)
	private String driverstate;// 主要分为忙碌、有计划、可用

	public Integer getDid() {
		return did;
	}

	public void setDid(Integer did) {
		this.did = did;
	}

	public String getDrivername() {
		return drivername;
	}

	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}

	public String getDrivertel() {
		return drivertel;
	}

	public void setDrivertel(String drivertel) {
		this.drivertel = drivertel;
	}

	public String getDriverid() {
		return driverid;
	}

	public void setDriverid(String driverid) {
		this.driverid = driverid;
	}

	public String getDriverage() {
		return driverage;
	}

	public void setDriverage(String driverage) {
		this.driverage = driverage;
	}

	public String getDrivertype() {
		return drivertype;
	}

	public void setDrivertype(String drivertype) {
		this.drivertype = drivertype;
	}

	public String getDrivergrade() {
		return drivergrade;
	}

	public void setDrivergrade(String drivergrade) {
		this.drivergrade = drivergrade;
	}

	public String getDriverstate() {
		return driverstate;
	}

	public void setDriverstate(String driverstate) {
		this.driverstate = driverstate;
	}

	@Override
	public String toString() {
		return "DriverInfo [did=" + did + ", drivername=" + drivername + ", drivertel=" + drivertel + ", driverid="
				+ driverid + ", driverage=" + driverage + ", drivertype=" + drivertype + ", drivergrade=" + drivergrade
				+ ", driverstate=" + driverstate + "]";
	}

}
