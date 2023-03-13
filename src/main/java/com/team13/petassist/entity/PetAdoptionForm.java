package com.team13.petassist.entity;

public class PetAdoptionForm {
	
	private static PetAdoptionForm INSTANCE;
	private String id;
	private String petId;
	private String userId;
	private String petParent;
	private String emailId;
	private String contact;
	private String address;
	private String reason;
	private String status;
	private String animal;
	private String breed;
	
	private PetAdoptionForm() {
		super();
	}
	
	public static PetAdoptionForm getInstance() {
		INSTANCE = new PetAdoptionForm();
		return INSTANCE;
	}
	public static PetAdoptionForm getInstance1() {
		INSTANCE = new PetAdoptionForm();
		return INSTANCE;
	}
	

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPetId() {
		return petId;
	}
	
	public void setPetId(String petId) {
		this.petId = petId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getPetParent() {
		return petParent;
	}
	
	public void setPetParent(String petParent) {
		this.petParent = petParent;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getReason() {
		return reason;
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAnimal() {
		return animal;
	}
	
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	
	public String getBreed() {
		return breed;
	}
	
	public void setBreed(String breed) {
		this.breed = breed;
	}
	
}
