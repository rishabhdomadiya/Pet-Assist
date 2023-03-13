package com.team13.petassist.entity;

public class Store {
	
	public static Store INSTANCE;
	public String storeId;
	public String storeName;
	public String location;
	public String storeContact;
	public String timings;
	public String Services;
	public String state;
	public String pincode;
	
	private Store() {
		super();
	}
	
	public static Store getInstance() {
		INSTANCE = new Store();
		return INSTANCE;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStoreContact() {
		return storeContact;
	}

	public void setStoreContact(String storeContact) {
		this.storeContact = storeContact;
	}

	public String getTimings() {
		return timings;
	}

	public void setTimings(String timings) {
		this.timings = timings;
	}

	public String getServices() {
		return Services;
	}

	public void setServices(String services) {
		Services = services;
	}
	
}
