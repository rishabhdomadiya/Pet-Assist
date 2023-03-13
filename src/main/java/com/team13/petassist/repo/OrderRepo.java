package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.Order;
import com.team13.petassist.entity.PetItems;
import com.team13.petassist.entity.PlaceOrderForm;
import com.team13.petassist.entity.interfaces.IOrder;
import com.team13.petassist.interfaces.IOrderRepo;

public class OrderRepo implements IOrderRepo {

	private String uname;
	private String pwd;
	private String url;
	private static IOrderRepo INSTANCE;

	private static final String INSERT_EVENTS = "INSERT INTO Orders(ItemId , UserId, ItemName, ItemQuantity, ItemDescription, ItemWeight, UserContact, DeliveryAddress, ZipCode,TotalCost, OrderStatus) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
	private static final String RETRIVE_ORDER_BY_AWB = "SELECT * FROM Orders where AWBNumber=?;";
	private static final String RETRIVE_ORDER_BY_USER = "SELECT * FROM Orders where UserId=?;";
	private static final String RETRIVE_ALL_ORDERS = "SELECT * FROM Orders;";
	private static final String UPDATE_ORDERS= "Update Orders set ItemWeight=? , ItemQuantity=? , UserContact=?, DeliveryAddress=?,ZipCode=?,TotalCost=?,OrderStatus=?,ItemDescription=?;";
	
	private OrderRepo(String uname, String pwd, String url) {
		super();
		this.uname = uname;
		this.pwd = pwd;
		this.url = url;
	}

	public static synchronized IOrderRepo getInstance(String uname, String pwd, String url) {

		if (INSTANCE == null) {
			INSTANCE = new OrderRepo(uname, pwd, url);
		}
		return INSTANCE;
	}

	@Override
	public List<IOrder> getAllOrders(String userId) throws SQLException {

		List<IOrder> orderList = new ArrayList<IOrder>();
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();

		if (conn == null) {
			return null;
		}
		PreparedStatement statement;

		if (userId == null) {
			statement = conn.prepareStatement(RETRIVE_ALL_ORDERS);
		} else {
			statement = conn.prepareStatement(RETRIVE_ORDER_BY_USER);
			statement.setString(1, userId);
		}

		ResultSet resultSet = statement.executeQuery();

		while (resultSet.next()) {
			orderList.add(new Order(resultSet));
		}

		conn.close();
		return orderList;

	}

	@Override
	public IOrder getOrder(String id) throws SQLException {
		Order order = null;
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		if (conn == null) {
			return null;
		}

		PreparedStatement statement = conn.prepareStatement(RETRIVE_ORDER_BY_AWB);

		statement.setString(1, id);

		ResultSet resultSet = statement.executeQuery();

		if (resultSet.next()) {
			order = new Order(resultSet);
		}

		conn.close();
		return order;
	}

	@Override
	public String storeOrder(PetItems petItems, PlaceOrderForm placeOrderForm) throws SQLException {
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		if (conn == null) {
			return null;
		}

		String status = "Order Placed";
		double totalCost = (double) placeOrderForm.getItemQuantity() * petItems.getCost();

		PreparedStatement statement = conn.prepareStatement(INSERT_EVENTS);
		statement.setInt(1, petItems.getItemId());
		statement.setInt(2, placeOrderForm.getUserId());
		statement.setString(3, petItems.getItemName());
		statement.setInt(4, placeOrderForm.getItemQuantity());
		statement.setString(5, petItems.getItemDescription());
		statement.setDouble(6, petItems.getItemWeight());
		statement.setString(7, placeOrderForm.getUserContact());
		statement.setString(8, placeOrderForm.getAddress());
		statement.setString(9, placeOrderForm.getZipCode());
		statement.setDouble(10, totalCost);
		statement.setString(11, status);

		statement.executeUpdate();
		return "pet-items";
	}

	@Override
	public Boolean updateOrder(IOrder o) throws SQLException {
		
		
		
		Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
		if (conn == null) {
			return null;
		}
		PreparedStatement statement = conn.prepareStatement(UPDATE_ORDERS);
		statement.setString(1, o.getItemWeight());
		statement.setString(2, o.getItemQuantity());
		statement.setString(3, o.getUserContact());
		statement.setString(4, o.getDeliveryAddress());
		statement.setString(5, o.getZipCode());
		statement.setString(6, o.getTotalCost());
		statement.setString(7, o.getOrderStatus());
		statement.setString(8, o.getItemDescription());
		
		return statement.executeUpdate() > 0;

	}

}