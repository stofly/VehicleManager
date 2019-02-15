/**
 * @author 石腾飞
 * @date 2018年11月11日上午10:53:29
 * @version 1.0
 * @company 河南喜乐融商贸有限公司
 * @Copyright: 2018 www.xlr.com Inc. All rights reserved. 
 */
package com.so.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 石腾飞
 * @date 2018年11月11日上午10:53:29
 */
public class Authorization {
	private String authorizationName;
	private List<String> menuids =new ArrayList<String>();
	
	/**
	 * @return the authorizationName
	 */
	public String getAuthorizationName() {
		return authorizationName;
	}



	/**
	 * @param authorizationName the authorizationName to set
	 */
	public void setAuthorizationName(String authorizationName) {
		this.authorizationName = authorizationName;
	}



	/**
	 * @return the menuids
	 */
	public List<String> getMenuids() {
		return menuids;
	}



	/**
	 * @param menuids the menuids to set
	 */
	public void setMenuids(List<String> menuids) {
		this.menuids = menuids;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Authorization [authorizationName=" + authorizationName + ", menuids=" + menuids + "]";
	}
	
	
}
