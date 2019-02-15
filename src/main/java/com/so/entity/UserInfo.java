package com.so.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "userInfo")
public class UserInfo implements Serializable {
	@Id
	@Column(name = "uid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 用户id
	@Column(name = "username", length = 20)
	private String username;// 用户名
	@Column(name = "userpassword", length = 20)
	private String userpassword;// 用户密码
	@Column(name = "type", length = 20)
	private String type;// 用户类型
	@Column(name = "ustates", length = 20)
	private String ustates;// 用户类型

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpassword() {
		return userpassword;
	}

	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUstates() {
		return ustates;
	}

	public void setUstates(String ustates) {
		this.ustates = ustates;
	}

	@Override
	public String toString() {
		return "UserInfo [uid=" + uid + ", username=" + username + ", userpassword=" + userpassword + ", type=" + type
				+ ", ustates=" + ustates + "]";
	}
}