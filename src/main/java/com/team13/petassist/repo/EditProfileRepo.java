package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.controller.EditProfileController;

public class EditProfileRepo{
	
	private String Email;
	private String username;
	private String Password;
	private int Age;
	private long UserContact;
	private String Sex;
	private String dob;
	private String Address;
	private String url;
	private String DBUsername;
	private String DBpassword;
	private static EditProfileRepo INSTANCE;
	

	public EditProfileRepo(String email, String username, String Password,int Age,long UserContact, String sex, String dob, String address,String url,String DBUsername,String DBpassword)
	{
		this.Email = email;
		this.username = username;
		this.Password = Password;
		this.Age = Age;
		this.UserContact = UserContact;
		this.Sex = sex;
		this.dob = dob;
		this.Address = address;
		this.url = url;
		this.DBpassword = DBpassword;
		this.DBUsername = DBUsername;
	}
	
	public static EditProfileRepo getInstance(String email, String username, String Password,int Age,long UserContact, String sex, String dob, String address,String url,String DBUsername,String DBpassword) {
		if(INSTANCE == null) 
		{
			INSTANCE = new EditProfileRepo(email, username, Password, Age, UserContact, sex, dob, address, url, DBUsername, DBpassword);
		}
		return INSTANCE;
	}
	
	public String UpdateUserDetails() throws SQLException
	{


		Connection conn = DatabaseConnection.getInstance(DBpassword,url, DBUsername).connect();
		if (conn == null)
		{
			return null;
		}
		Statement stmt = conn.createStatement();
		int dbuser = Integer.parseInt(EditProfileController.userId);
		System.out.println(Email + " " + dbuser );
		String emailExist = "SELECT * FROM CSCI5308_13_DEVINT.UserProfile WHERE Email = '" + Email + "' AND UserId <> " +dbuser +"" ;
		ResultSet rse = stmt.executeQuery(emailExist);
		System.out.println(emailExist);
		if(rse.next())
		{
			return "EmailAlreadyExist";
		}
		
		String userExist = "SELECT * FROM CSCI5308_13_DEVINT.UserProfile WHERE UserId = " + dbuser;
		ResultSet rs = stmt.executeQuery(userExist);
		
		if(rs.next())
		{
			String insertquery = "UPDATE CSCI5308_13_DEVINT.UserProfile"
					+ " SET UserName = '" + username + "', Email ='"  + Email + "', UserPassword ='" +Password+ "', Age=" +Age +", UserContact=" +UserContact+", Sex='" + Sex + "', DOB='" +dob+ "', Address ='" +Address+"'"  
					+ " WHERE UserId = " +dbuser ;
				System.out.println(insertquery)	;
			
			stmt.executeUpdate(insertquery);
			return "UserUpdated";
		}
		
		return "UserNotUpdated";
		
		
	}
	

}
