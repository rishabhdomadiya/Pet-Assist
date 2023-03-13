package com.team13.petassist.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private Connection conn = null;
	String url, user, password;
	private static DatabaseConnection INSTANCE;

	
	private DatabaseConnection(String password, String url, String user) {
		super();
		this.url = url;
		this.user = user;
		this.password = password;
	}
	
	public static DatabaseConnection getInstance(String url, String userName, String password) {
		if(INSTANCE == null) {
			INSTANCE = new DatabaseConnection(url,userName,password);
		}
		return INSTANCE;
	}

	public  Connection connect() throws SQLException {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			conn = DriverManager.getConnection(url, user, password);

		} catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		return conn;
	}
	
}
