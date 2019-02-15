package com.so.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "permission")
public class Permission implements Serializable {
	@Id
	@Column(name = "pid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;// 主键id
	@Column(name = "pname")
	private String pname;// 角色名（管理员、申请人、门卫）
	@Column(name = "pmid")
	private String pmid;// 对应的权限名

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPmid() {
		return pmid;
	}

	public void setPmid(String pmid) {
		this.pmid = pmid;
	}

	@Override
	public String toString() {
		return "Permission [pid=" + pid + ", pname=" + pname + ", pmid=" + pmid + "]";
	}

}
