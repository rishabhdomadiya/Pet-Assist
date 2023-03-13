package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.Store;
import com.team13.petassist.interfaces.IStore;

public class StoreRepo implements IStore{
	
	public static final String SELECT_STORES = "SELECT * FROM Stores";
	public static StoreRepo INSTANCE;
	private String userName;
	private String password;
	private String url;

	private StoreRepo() {

	}

	private StoreRepo(String userName, String password, String url) {
		super();
		this.userName = userName;
		this.password = password;
		this.url = url;
	}
	
	public static StoreRepo getInstance(String url, String userName, String password) {
		if(INSTANCE == null) {
			INSTANCE = new StoreRepo(url,userName,password);
		}
		return INSTANCE;
	}

	@Override
	public List<Store> getStoreId() {
		List<Store> store = new ArrayList<Store>();
		
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn.prepareStatement(SELECT_STORES);
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				Store storeModel = Store.getInstance();
				storeModel.setStoreId(resultSet.getString(1));
				storeModel.setStoreName(resultSet.getString(2));
				storeModel.setStoreContact(resultSet.getString(4));
				storeModel.setTimings(resultSet.getString(5));
				storeModel.setServices(resultSet.getString(6));
				storeModel.setLocation(resultSet.getString(3));
				storeModel.setState(resultSet.getString(7));
				storeModel.setPincode(resultSet.getString(8));
				store.add(storeModel);
				
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return store;
	}

	@Override
	public List<Store> getFilteredStores(String locationName)  {
		List<Store> store = new ArrayList<Store>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
	
			PreparedStatement statement = conn
					.prepareStatement("select * from Stores where ((Location LIKE '" + locationName
							+ "%') OR (PinCode LIKE '" + locationName + "%') OR (State LIKE '" + locationName + "%'));");
			ResultSet resultSet = statement.executeQuery();
	
			while (resultSet.next()) {
				Store storeModel = Store.getInstance();
				storeModel.setStoreId(resultSet.getString(1));
				storeModel.setStoreName(resultSet.getString(2));
				storeModel.setStoreContact(resultSet.getString(4));
				storeModel.setTimings(resultSet.getString(5));
				storeModel.setServices(resultSet.getString(6));
				storeModel.setLocation(resultSet.getString(3));
				storeModel.setState(resultSet.getString(8));
				storeModel.setPincode(resultSet.getString(7));
				store.add(storeModel);
			}
			conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
			}
		return store;
	}

}
