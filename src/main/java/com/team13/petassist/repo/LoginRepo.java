package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.UserDetails;

public class LoginRepo {
	private String uname;
	private String pwd;
	private String url;
	private static LoginRepo INSTANCE;

	private LoginRepo(String uname, String pwd, String url) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.url = url;
	}
	
	public static LoginRepo getInstance(String url, String uname, String pwd) {
		if(INSTANCE == null) {
			INSTANCE = new LoginRepo(url,uname,pwd);
		}
		return INSTANCE;
	}

	public Boolean login(String userName, String userPassword) throws SQLException {

		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		if (conn == null) {
			return null;
		}
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(
				"select * from UserProfile where UserName='" + userName + "' and UserPassword='" + userPassword + "'");

		if (rs.next()) {
			return true;
		}

		else
			return false;
	}
	
	public UserDetails getUserDetails(String userName, String userPassword) throws SQLException {


		UserDetails udetails = UserDetails.getInstance();
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();

		if (conn == null) {
			return null;
		}
		
		PreparedStatement statement = conn.prepareStatement("select * from UserProfile where UserName='" + userName + "' and UserPassword='" + userPassword + "'");
		ResultSet resultSet = statement.executeQuery();
	  
		while(resultSet.next()) { 
			udetails.setUserId(resultSet.getString(1));
			udetails.setUserRole(resultSet.getString(10));
		}
		return udetails;	
	}
	
	public UserDetails getUserDetailsById(String userId) throws SQLException, ParseException {

		UserDetails udetails = UserDetails.getInstance();
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();

		if (conn == null) {
			return null;
		}
		
		PreparedStatement statement = conn.prepareStatement("select * from UserProfile where UserId='" + userId +  "'");
		ResultSet resultSet = statement.executeQuery();
	  
		while(resultSet.next()) { 
			

			udetails.setUserName(resultSet.getString(2));
			udetails.setEmail(resultSet.getString(3));
			udetails.setAge(resultSet.getString(5));
			udetails.setSex(resultSet.getString(7));
			udetails.setAddress(resultSet.getString(9));
			udetails.setUserRole(resultSet.getString(10));
		} 
		return udetails;	
	}
	
	public String getUserRoleById(String userId) throws SQLException{
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		String ret = "";
		if (conn == null) {
			return null;
		}
		PreparedStatement statement = conn.prepareStatement("select UserRole from UserProfile where UserId='" + userId +  "'");
		ResultSet resultSet = statement.executeQuery();
		while(resultSet.next()) {
			ret=resultSet.getString("UserRole");
		}
		conn.close();
		return ret;
	  
	}
	
}
