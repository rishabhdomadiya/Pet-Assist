package com.team13.petassist.repo;

import java.sql.*;
import java.util.*;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.PetForm;
import com.team13.petassist.interfaces.IPet;


public class PetRepo implements IPet{
	
	private static final String SELECT_ALL_PETS = "SELECT * FROM Pet";	
	private static final String SELECT_DISTINCT_PETS = "SELECT distinct Animals FROM Pet";
	private static final String SELECT_PETS_BY_ID = "SELECT * FROM Pet WHERE PetId =?";
	private static final String SELECT_ITEMS_BY_USER = "SELECT p.* FROM Pet p JOIN PetAdoption pa ON p.PetId= pa.PetId WHERE pa.AdoptionStatus=\"Approved\" and pa.UserId=?";
	private static PetRepo INSTANCE;
	private String userName;
	private String password;
	private String url;

	private PetRepo() {

	}

	private PetRepo(String userName, String password, String url) {
		super();
		this.userName = userName;
		this.password = password;
		this.url = url;
	}
	
	public static PetRepo getInstance(String url, String uname, String pwd) {
		if(INSTANCE == null) {
			INSTANCE = new PetRepo(url,uname,pwd);
		}
		return INSTANCE;
	}

	@Override
	public List<PetForm> getAllPets() {
		List<PetForm> petList = new ArrayList<PetForm>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_PETS);
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				PetForm pfo = PetForm.getInstance();
				pfo.setId(resultSet.getString(1));
				pfo.setAnimal(resultSet.getString(2));
				pfo.setBreed(resultSet.getString(3));
				petList.add(pfo);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return petList;
	}

	@Override
	public List<PetForm> getFilteredPets(String animalName)  {
		List<PetForm> petList = new ArrayList<PetForm>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn
					.prepareStatement("SELECT * FROM Pet WHERE Animals Like '"+animalName+"%'");
			
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				PetForm pfo = PetForm.getInstance();
				pfo.setId(resultSet.getString(1));
				pfo.setAnimal(resultSet.getString(2));
				pfo.setBreed(resultSet.getString(3));
				petList.add(pfo);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return petList;
	}
	
	@Override
	public List<PetForm> getDistinctAnimals() {
		List<PetForm> petList = new ArrayList<PetForm>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn.prepareStatement(SELECT_DISTINCT_PETS);
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				PetForm pfo = PetForm.getInstance();
				pfo.setAnimal(resultSet.getString(1));
				petList.add(pfo);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return petList;
	}

	@Override
	public List<PetForm> getPetById(String id) {
		List<PetForm> petList = new ArrayList<PetForm>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn.prepareStatement(SELECT_PETS_BY_ID);
			statement.setString(1, id.toString());
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				PetForm pfo = PetForm.getInstance();
				pfo.setId(resultSet.getString(1));
				pfo.setAnimal(resultSet.getString(2));
				pfo.setBreed(resultSet.getString(3));
				petList.add(pfo);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return petList;
	}

	@Override
	public List<PetForm> getPetByUserId(String userId){
		List<PetForm> petList = new ArrayList<>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn
					.prepareStatement(SELECT_ITEMS_BY_USER);
			statement.setString(1, userId.toString());
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				PetForm petForm = PetForm.getInstance();
				petForm.setId(resultSet.getString(1));
				petForm.setAnimal(resultSet.getString(2));
				petForm.setBreed(resultSet.getString(3));
				petList.add(petForm);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return petList;
	}
	
}
