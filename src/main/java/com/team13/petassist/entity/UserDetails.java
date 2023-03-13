package com.team13.petassist.entity;

public class UserDetails {
	private static final UserDetails INSTANCE = new UserDetails();
	private String userId;
	private String userName;
	private String email;
	private String password;
	private String confirmPassword;
	private String age;
	private String contact;
	private String sex;
	private String address;
	private String userRole;
	private String userOTP;
	private String dob;
	
	private UserDetails() {
		super();
	}
	
	public static UserDetails getInstance() {
		return INSTANCE;
	}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getUserOTP() {
		return userOTP;
	}
	public void setUserOTP(String userOTP) {
		this.userOTP = userOTP;
	}
	@Override
	public String toString() {
		return "UserDetails [UserName=" + userName + ", Email=" + email + ", Password=" + password
				+ ", ConfirmPassword=" + confirmPassword + "]";
	}

}
