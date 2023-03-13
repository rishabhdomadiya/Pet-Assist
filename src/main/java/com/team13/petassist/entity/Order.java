package com.team13.petassist.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.team13.petassist.entity.interfaces.IOrder;

public class Order implements IOrder {
	
	public String aWBNumber;
	public String itemId;
	public String userId;
	public String itemName;
	public String itemQuantity;
	public String itemDescription;
	public String itemWeight;
	public String userContact;
	public String deliveryAddress;
	public String zipCode;
	public String totalCost;
	public String orderStatus;

	public Order() {
		super();
	}

	public Order(String aWBNumber, String itemId, String userId, String itemName, String itemQuantity,
			String itemDescription, String itemWeight, String userContact, String deliveryAddress, String zipCode,
			String totalCost, String orderStatus) {
		super();
		this.aWBNumber = aWBNumber;
		this.itemId = itemId;
		this.userId = userId;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.itemDescription = itemDescription;
		this.itemWeight = itemWeight;
		this.userContact = userContact;
		this.deliveryAddress = deliveryAddress;
		this.zipCode = zipCode;
		this.totalCost = totalCost;
		this.orderStatus = orderStatus;
	}

	public Order(ResultSet rs) throws SQLException {
		aWBNumber = rs.getString("AWBNumber");
		itemId = rs.getString("ItemId");
		userId = rs.getString("UserId");
		itemName = rs.getString("itemName");
		itemQuantity = rs.getString("ItemQuantity");
		itemDescription = rs.getString("ItemDescription");
		itemWeight = rs.getString("ItemWeight");
		userContact = rs.getString("UserContact");
		deliveryAddress = rs.getString("DeliveryAddress");
		zipCode = rs.getString("ZipCode");

		totalCost = rs.getString("TotalCost");
		orderStatus = rs.getString("OrderStatus");
	}

	@Override
	public String getaWBNumber() {
		return aWBNumber;
	}

	@Override
	public void setaWBNumber(String aWBNumber) {
		this.aWBNumber = aWBNumber;
	}

	@Override
	public String getItemId() {
		return itemId;
	}

	@Override
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	@Override
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String getItemQuantity() {
		return itemQuantity;
	}

	@Override
	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	@Override
	public String getItemDescription() {
		return itemDescription;
	}

	@Override
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}

	@Override
	public String getItemWeight() {
		return itemWeight;
	}

	@Override
	public void setItemWeight(String itemWeight) {
		this.itemWeight = itemWeight;
	}

	@Override
	public String getUserContact() {
		return userContact;
	}

	@Override
	public void setUserContact(String userContact) {
		this.userContact = userContact;
	}

	@Override
	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	@Override
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	@Override
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String getTotalCost() {
		return totalCost;
	}

	@Override
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String getOrderStatus() {
		return orderStatus;
	}

	@Override
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
