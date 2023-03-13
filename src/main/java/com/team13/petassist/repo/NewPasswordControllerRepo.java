package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import com.team13.petassist.config.DatabaseConnection;

public class NewPasswordControllerRepo{
	
	private String email;
	private String password;
	private String cPassword;
	private String url;
	private String dbUsername;
	private String dbPassword;
	
	
	public NewPasswordControllerRepo(String email, String url, String DBUsername, String DBpassword)
	{
		this.email = email;
		this.dbUsername = DBUsername;
		this.url = url;
		this.dbPassword = DBpassword;
	}
	
	public String EmailAuth() throws SQLException
	{

		Connection conn = DatabaseConnection.getInstance(dbPassword,url, dbUsername).connect();

		if (conn == null) {
			return null;
		}
		Statement stmt = conn.createStatement();
		String emailExist = "SELECT * FROM CSCI5308_13_DEVINT.UserProfile WHERE Email ='" + email + "'"; 
		ResultSet rs = stmt.executeQuery(emailExist);
		
		if(rs.next())
		{
			return "emailexist";
		}
		else
			return "doesntexist";
	}
	
	public String GenerateOTP()
	{
		Random rand = new Random();
		int number = rand.nextInt(9999);
		return String.format("%04d", number);
	}

}
