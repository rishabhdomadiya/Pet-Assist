package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.interfaces.IUserDetails;

public class UserDetailsRepo implements IUserDetails {
	
	private String email;
	private String userName;
	private String password;
	private String cPassword;
	private String url;
	private String dbUsername;
	private String dbPassword;
	private static UserDetailsRepo INSTANCE;
	
	public UserDetailsRepo(String userEmail, String userName, String userPassword,String userCPassword,String dbUsername, String dbpassword,String url )
	{
		this.email = userEmail;
		this.userName = userName;
		this.password = userPassword;
		this.cPassword = userCPassword;
		this.url = url;
		this.dbUsername = dbUsername;
		this.dbPassword = dbpassword;
		
	}
	
	public static UserDetailsRepo getInstance(String userEmail, String userName, String userPassword,String userCPassword,String dbUsername, String dbpassword,String url ) {
		if(INSTANCE == null) {
			INSTANCE = new UserDetailsRepo(userEmail, userName, userPassword, userCPassword, dbUsername, dbpassword, url );
		}
		return INSTANCE;
	}
	
	@Override
	public String SignUp() throws SQLException
	{

		Connection conn = DatabaseConnection.getInstance(dbPassword,url, dbUsername).connect();

		if (conn == null) {
			return null;
		}
		Statement stmt = conn.createStatement();
		String userExist = "SELECT * FROM CSCI5308_13_DEVINT.UserProfile WHERE UserName ='" + userName + "'OR Email ='" + email +"'"; 
		ResultSet rs = stmt.executeQuery(userExist);
		
		if(rs.next())
		{
			return "userexist";
		}
		
		String sqlquery = "INSERT INTO CSCI5308_13_DEVINT.UserProfile(UserName , Email, UserPassword, UserRole)" + 
				" VALUES ('"+ userName +"','" + email +"','" + password
				+ "','Customer');";
		
		stmt.executeUpdate(sqlquery);
		return "SignUpSuccess";
		
		
		
	}

}
