package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.Contact;
import com.team13.petassist.interfaces.IContact;

public class ContactRepo implements IContact{
		
	private String userName;
	private String password;
	private String url;
	private static final String SELECT_EVENTS = "SELECT * FROM Contact";
	private static ContactRepo INSTANCE;
	
	private ContactRepo() {
		
	}
		
	private ContactRepo(String uname, String pwd, String url) {
		super();
		this.userName = uname;
		this.password = pwd;
		this.url = url;
	}
	
	public static ContactRepo getInstance(String url, String uname, String pwd) {
		if(INSTANCE == null) {
			INSTANCE = new ContactRepo(url,uname,pwd);
		}
		return INSTANCE;
	}
	
	@Override
	public Contact getContactDetails(){
		Contact contactO = Contact.getInstance();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
			
			PreparedStatement statement = conn.prepareStatement(SELECT_EVENTS);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) { 
				contactO.setFacebook(resultSet.getString(1));
				contactO.setInstagram(resultSet.getString(2));
				contactO.setYoutube(resultSet.getString(3));
				contactO.setTwitter(resultSet.getString(4));
				contactO.setEmailString(resultSet.getString(5));
				contactO.setContact(resultSet.getString(6));
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		
		return contactO;
	  
	}

}
