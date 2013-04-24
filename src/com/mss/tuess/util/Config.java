package com.mss.tuess.util;

/**
 * Reference from http://martin3000.iteye.com/blog/1326578
 * @author Marin3000
 *
 */
public class Config {

	public String server;
	public String user;
	public String pass;
	public String port;
	public String dbname;

	public String getConnString() {

		String connString = "jdbc:mysql://" + server + ":" + port + "/"
				+ dbname + "?user=" + user + "&password=" + pass
				+ "&useUnicode=true&characterEncoding=UTF-8";
		return connString;

	}

}
