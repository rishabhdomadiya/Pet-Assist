package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.PetForm;
import com.team13.petassist.interfaces.IPetForm;

public class PetServicesRepo {
	private String uname;
	private String pwd;
	private String url;
	private static PetServicesRepo INSTANCE;
	
	public PetServicesRepo(String uname, String pwd, String url) {
		this.uname = uname;
		this.pwd = pwd;
		this.url = url;
		System.out.println(uname +" " + pwd + " " + url);
	}
	
	public static PetServicesRepo getInstance(String url, String uname, String pwd) {
		if(INSTANCE == null) {
			INSTANCE = new PetServicesRepo(uname,pwd,url);
		}
		return INSTANCE;
	}
	
	public List<PetForm> getGroomDefaultServcies() throws SQLException {
		List<PetForm> petFormListDefault = new ArrayList<PetForm>();

		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		if (conn == null) {
			return null;
		}
		System.out.println(uname +" " + pwd + " " + url);
		PreparedStatement groomStatement = conn.prepareStatement("SELECT AnimalName, Grooming_Tips, Nutritional_Tips, Products_Recommended  FROM CSCI5308_13_DEVINT.PetServices WHERE Default_Service = 'Y'");
		ResultSet resultSet = groomStatement.executeQuery();
		System.out.println(groomStatement);
		while(resultSet.next()) { 
			IPetForm petForm = new PetForm();
			petForm.setGroomServices(resultSet.getString(2));
			petForm.setAnimal(resultSet.getString(1));
			petForm.setNutServices(resultSet.getString(3));
			petForm.setProdServices(resultSet.getString(4));
			petFormListDefault.add((PetForm) petForm); 
			System.out.println(petForm.getAnimal());
		} 
		return petFormListDefault;
	}
	
	

	public List<PetForm> getFilteredServices(String animal) throws SQLException {
		List<PetForm> petFormListFilt = new ArrayList<PetForm>();
		List<Integer> randomNumb = new ArrayList<Integer>();
		Random rand = new Random();
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		if (conn == null) {
			return null;
		}
		PreparedStatement statement = conn.prepareStatement("SELECT AnimalName, Grooming_Tips, Nutritional_Tips, Products_Recommended FROM CSCI5308_13_DEVINT.PetServices WHERE AnimalName = '" + animal +"' AND Default_Service = 'N' ",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet resultSet = statement.executeQuery();
		resultSet.last();
	    int size = resultSet.getRow();
	    resultSet.beforeFirst();
	    while(randomNumb.size()  != 3)
	    {
	    	int random1 = rand.nextInt(size+1);
	    	if(random1 != 0)
	    	{
		    	if(!randomNumb.contains(random1))
		    		randomNumb.add(random1);    
	    	}
	    }
	    int i = 1 ;
	    
			while(resultSet.next()) { 
				if(randomNumb.contains(i))
				{
					IPetForm petForm = new PetForm();
					petForm.setGroomServices(resultSet.getString(2));
					petForm.setAnimal(resultSet.getString(1));
					petForm.setNutServices(resultSet.getString(3));
					petForm.setProdServices(resultSet.getString(4));
					petFormListFilt.add((PetForm) petForm); 
				}
				i++;
			} 
			return petFormListFilt;
		
	}

}
