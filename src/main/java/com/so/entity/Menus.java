package com.so.entity;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "menus")
public class Menus implements Serializable {
	@Id
	@Column(name = "menuid")
	private Integer menuid;
	@Column(name = "url", length = 255)
	private String url;
	@Column(name = "menuname", length = 50)
	private String menuname;
	@Column(name = "icon", length = 50)
	private String icon;
	@Column(name = "pid")
	private String pid;
	@Transient
	private List<Menus> menus = new LinkedList<Menus>();

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public List<Menus> getMenus() {
		return menus;
	}

	public void setMenus(List<Menus> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "Menus [menuid=" + menuid + ", url=" + url + ", menuname=" + menuname + ", icon=" + icon + ", pid=" + pid
				+ ", menus=" + menus + "]";
	}

}
