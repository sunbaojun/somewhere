package com.eya.tools.generator.util;

public class JDBCInfo {

	private String userName;
	
	private String userPwd;
	
	private String url;
	
	private String driver;
	
	private String chartSet;

	public String getChartSet() {
		return chartSet;
	}

	public void setChartSet(String chartSet) {
		this.chartSet = chartSet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
}
