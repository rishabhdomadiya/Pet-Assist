package com.team13.petassist.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.team13.petassist.entity.PetAdoptionForm;

public interface IPetAdoption {
	public String adoptAPet();
	public List<PetAdoptionForm> getAdoptionListByUserId(String UserId);
	 List<PetAdoptionForm> getAllAdoptionRequests();
	public int deleteAdoptionItem(String id);
	public PetAdoptionForm getPetReqById(String id);
	public Boolean updatePetRequest(PetAdoptionForm petForm) throws SQLException;

}
