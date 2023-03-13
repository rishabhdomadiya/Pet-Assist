package com.team13.petassist.entity.interfaces;

public interface IOrder {

	String getaWBNumber();

	void setaWBNumber(String aWBNumber);

	String getItemId();

	void setItemId(String itemId);

	String getUserId();

	void setUserId(String userId);

	String getItemName();

	void setItemName(String itemName);

	String getItemQuantity();

	void setItemQuantity(String itemQuantity);

	String getItemDescription();

	void setItemDescription(String itemDescription);

	String getItemWeight();

	void setItemWeight(String itemWeight);

	String getUserContact();

	void setUserContact(String userContact);

	String getDeliveryAddress();

	void setDeliveryAddress(String deliveryAddress);

	String getZipCode();

	void setZipCode(String zipCode);

	String getTotalCost();

	void setTotalCost(String totalCost);

	String getOrderStatus();

	void setOrderStatus(String orderStatus);

}
