package com.team13.petassist.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.PetAdoptionForm;
import com.team13.petassist.interfaces.IPetAdoption;

public class PetAdoptionRepo implements IPetAdoption {

	private static final String INSERT_ADOPTIONS = "INSERT INTO PetAdoption(PetId , UserId, PetParent, EmailAddress, UserContact, Address, Reason, AdoptionStatus) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SELECT_ADOPTIONS_BY_USERID = "SELECT p.Animals, p.Breed, a.ListId, a.UserContact, a.Address, a.Reason FROM Pet p, PetAdoption a WHERE a.PetId = p.PetId and a.AdoptionStatus = 'Submitted' AND a.UserId =?;";
	private static final String SELECT_ALL_ADOPTIONS = "SELECT p.Animals, p.Breed, a.ListId, a.UserContact, a.Address, a.Reason, a.AdoptionStatus FROM Pet p, PetAdoption a WHERE a.PetId = p.PetId";
	private static final String DELETE_ADOPTION = "DELETE FROM PetAdoption WHERE ListId =?;";
	private static final String SELECT_PETREQ_BY_ID = "SELECT  p.Animals, p.Breed, a.ListId, a.PetId, a.UserId, a.PetParent, a.EmailAddress, a.UserContact, a.Address, a.Reason, a.AdoptionStatus FROM Pet p, PetAdoption a WHERE a.PetId = p.PetId AND a.ListId =?; ";
	private static final String UPDATE_ADOPTION_DETAILS = "Update PetAdoption set Address=?,UserContact=?,EmailAddress=?,PetParent=?,AdoptionStatus=?,Reason=? where ListId =? ;";
	private static PetAdoptionRepo INSTANCE;
	private static PetAdoptionRepo INSTANCE1;
	private String userName;
	private String password;
	private String url;
	private String petId;
	private String userId;
	private String petParent;
	private String emailId;
	private String contact;
	private String address;
	private String reason;
	private String status;

	private PetAdoptionRepo() {

	}

	private PetAdoptionRepo(String uname, String pwd, String url) {
		this.userName = uname;
		this.password = pwd;
		this.url = url;
	}

	private PetAdoptionRepo(String uname, String pwd, String url, String petId, String userId, String petParent,
			String emailId, String contact, String address, String reason, String status) {
		super();
		this.userName = uname;
		this.password = pwd;
		this.url = url;
		this.petId = petId;
		this.userId = userId;
		this.petParent = petParent;
		this.emailId = emailId;
		this.contact = contact;
		this.address = address;
		this.reason = reason;
		this.status = status;
	}

	public static PetAdoptionRepo getInstance1(String url, String uname, String pwd) {
		if (INSTANCE1 == null) {
			INSTANCE1 = new PetAdoptionRepo(url, uname, pwd);
		}
		return INSTANCE1;
	}

	public static PetAdoptionRepo getInstance(String url, String uname, String pwd, String petId, String userId,
			String petParent, String emailId, String contact, String address, String reason, String status) {
		if (INSTANCE == null) {
			INSTANCE = new PetAdoptionRepo(url, uname, pwd, petId, userId, petParent, emailId, contact, address, reason,
					status);
		}
		return INSTANCE;
	}

	@Override
	public String adoptAPet() {
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
			PreparedStatement statement = conn.prepareStatement(INSERT_ADOPTIONS);
			statement.setString(1, petId.toString());
			statement.setString(2, userId.toString());
			statement.setString(3, petParent);
			statement.setString(4, emailId);
			statement.setString(5, contact);
			statement.setString(6, address);
			statement.setString(7, reason);
			statement.setString(8, status);
			statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewpets";
	}

	@Override
	public List<PetAdoptionForm> getAdoptionListByUserId(String UserId) {
		List<PetAdoptionForm> adoptionList = new ArrayList<PetAdoptionForm>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
			PreparedStatement statement = conn.prepareStatement(SELECT_ADOPTIONS_BY_USERID);
			statement.setString(1, UserId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PetAdoptionForm pfo = PetAdoptionForm.getInstance();
				pfo.setAnimal(resultSet.getString(1));
				pfo.setBreed(resultSet.getString(2));
				pfo.setId(resultSet.getString(3));
				pfo.setContact(resultSet.getString(4));
				pfo.setAddress(resultSet.getString(5));
				pfo.setReason(resultSet.getString(6));
				adoptionList.add(pfo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adoptionList;
	}

	@Override
	public List<PetAdoptionForm> getAllAdoptionRequests() {
		List<PetAdoptionForm> adoptionList = new ArrayList<PetAdoptionForm>();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return null;
			}
			PreparedStatement statement = conn.prepareStatement(SELECT_ALL_ADOPTIONS);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PetAdoptionForm pfo = PetAdoptionForm.getInstance();
				pfo.setAnimal(resultSet.getString(1));
				pfo.setBreed(resultSet.getString(2));
				pfo.setId(resultSet.getString(3));
				pfo.setContact(resultSet.getString(4));
				pfo.setAddress(resultSet.getString(5));
				pfo.setReason(resultSet.getString(6));
				pfo.setStatus(resultSet.getString(7));
				adoptionList.add(pfo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return adoptionList;
	}

	@Override
	public int deleteAdoptionItem(String id) {
		int result = 0;
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			if (conn == null) {
				return 0;
			}
			PreparedStatement statement = conn.prepareStatement(DELETE_ADOPTION);
			statement.setString(1, id);
			result = statement.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public PetAdoptionForm getPetReqById(String id) {
		PetAdoptionForm pfo = PetAdoptionForm.getInstance();
		try {
			Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
			PreparedStatement statement = conn.prepareStatement(SELECT_PETREQ_BY_ID);
			statement.setString(1, id);
			ResultSet resultSet = statement.executeQuery();

			if (resultSet.next()) {

				pfo.setAnimal(resultSet.getString(1));
				pfo.setBreed(resultSet.getString(2));
				pfo.setId(resultSet.getString(3));// list id
				pfo.setPetId(resultSet.getString(4));
				pfo.setUserId(resultSet.getString(5));
				pfo.setPetParent(resultSet.getString(6));
				pfo.setEmailId(resultSet.getString(7));
				pfo.setContact(resultSet.getString(8));
				pfo.setAddress(resultSet.getString(9));
				pfo.setReason(resultSet.getString(10));
				pfo.setStatus(resultSet.getString(11));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pfo;
	}

	@Override
	public Boolean updatePetRequest(PetAdoptionForm o) throws SQLException {

		Connection conn = DatabaseConnection.getInstance(url, userName, password).connect();
		PreparedStatement statement = conn.prepareStatement(UPDATE_ADOPTION_DETAILS);
		statement.setString(1, o.getAddress());
		statement.setString(2, o.getContact());
		statement.setString(3, o.getEmailId());
		statement.setString(4, o.getPetParent());
		statement.setString(5, o.getStatus());
		statement.setString(6, o.getReason());
		statement.setString(7, o.getId());
		Boolean updated = statement.executeUpdate() > 0;
		return updated;
	}

}
