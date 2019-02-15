package com.so.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "iorecord")
public class IoRecord implements Serializable {
	@Id
	@Column(name = "rid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rid;// 车辆表主键
	@Column(name = "inum")
	private String inum;// 车辆牌号
	@Column(name = "adrivername", length = 30)
	private String adrivername;// 司机
	@Column(name = "idrivernum", length = 12)
	private String idrivernum;// 司机驾驶证编号
	@Column(name = "keynum", length = 30)
	private String keynum;// 通行证编号
	@Column(name = "intime")
	private Date intime;// 进去或者出去时间
	@Column(name = "outtime")
	private Date outtime;// 进去或者出去时间
	@Column(name = "registerp", length = 20)
	private String registerp;// 登记人

	public Integer getRid() {
		return rid;
	}

	public void setRid(Integer rid) {
		this.rid = rid;
	}

	public String getInum() {
		return inum;
	}

	public void setInum(String inum) {
		this.inum = inum;
	}

	public String getAdrivername() {
		return adrivername;
	}

	public void setAdrivername(String adrivername) {
		this.adrivername = adrivername;
	}

	public Date getIntime() {
		return intime;
	}

	public void setIntime(Date intime) {
		this.intime = intime;
	}

	public Date getOuttime() {
		return outtime;
	}

	public void setOuttime(Date outtime) {
		this.outtime = outtime;
	}

	public String getIdrivernum() {
		return idrivernum;
	}

	public void setIdrivernum(String idrivernum) {
		this.idrivernum = idrivernum;
	}

	public String getKeynum() {
		return keynum;
	}

	public void setKeynum(String keynum) {
		this.keynum = keynum;
	}
	
	public String getRegisterp() {
		return registerp;
	}

	public void setRegisterp(String registerp) {
		this.registerp = registerp;
	}

	@Override
	public String toString() {
		return "IoRecord [rid=" + rid + ", inum=" + inum + ", adrivername=" + adrivername + ", idrivernum=" + idrivernum
				+ ", keynum=" + keynum + ", intime=" + intime + ", outtime=" + outtime + ", registerp=" + registerp
				+ "]";
	}


}
