package com.so.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carinfo")
public class CarInfo {
	@Id
	@Column(name = "cid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;// 车辆表主键
	@Column(name = "cnum", length = 10)
	private String cnum;// 车辆牌号
	@Column(name = "ctype", length = 30)
	private String ctype;// 车辆类型
	@Column(name = "crecord", length = 255)
	private String crecord;// 车辆记录
	@Column(name = "cplan", length = 255)
	private String cplan;// 车辆计划
	@Column(name = "cstate", length =30)
	private String cstate;// 车辆状态

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getCnum() {
		return cnum;
	}

	public void setCnum(String cnum) {
		this.cnum = cnum;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

	public String getCrecord() {
		return crecord;
	}

	public void setCrecord(String crecord) {
		this.crecord = crecord;
	}

	public String getCplan() {
		return cplan;
	}

	public void setCplan(String cplan) {
		this.cplan = cplan;
	}

	public String getCstate() {
		return cstate;
	}

	public void setCstate(String cstate) {
		this.cstate = cstate;
	}

	@Override
	public String toString() {
		return "CarInfo [cid=" + cid + ", cnum=" + cnum + ", ctype=" + ctype + ", crecord=" + crecord + ", cplan="
				+ cplan + ", cstate=" + cstate + "]";
	}

}
