package com.team13.petassist.entity;

public class Contact {
	
	private static final Contact INSTANCE = new Contact(); //using Instance to implement singleton design principle
	public String facebook;
	public String instagram;
	public String youtube;
	public String twitter;
	public String emailString;
	public String contactnumber;
	
	private Contact() {
		super();
	}
	
	public static Contact getInstance() {
		return INSTANCE;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getInstagram() {
		return instagram;
	}

	public void setInstagram(String instagram) {
		this.instagram = instagram;
	}

	public String getYoutube() {
		return youtube;
	}

	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getEmailString() {
		return emailString;
	}

	public void setEmailString(String emailString) {
		this.emailString = emailString;
	}

	public String getContact() {
		return contactnumber;
	}

	public void setContact(String contactnumber) {
		this.contactnumber = contactnumber;
	}

}
