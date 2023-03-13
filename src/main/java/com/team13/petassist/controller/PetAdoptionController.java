package com.team13.petassist.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.team13.petassist.entity.PetAdoptionForm;
import com.team13.petassist.interfaces.IPetAdoption;
import com.team13.petassist.repo.PetAdoptionRepo;

@Controller
public class PetAdoptionController {
	
	  	@Value("${url}")
	    String url;
	    @Value("${user1}")
	    String user;
	    @Value("${passwordDb}")
	    String password ;
	    
	    @PostMapping("/adoptapet")
	    public String petAdoptionForm(@ModelAttribute(name="petAdoptionForm") PetAdoptionForm petAdoptionForm, Model model) throws SQLException{
	    	
	    	String petId = petAdoptionForm.getPetId();
	    	String userId = petAdoptionForm.getUserId();
	    	String petParent = petAdoptionForm.getPetParent();
	    	String emailId = petAdoptionForm.getEmailId();
	    	String contact = petAdoptionForm.getContact();
	    	String address = petAdoptionForm.getAddress();
	    	String reason = petAdoptionForm.getReason();
	    	String status = "Submitted";

	    	IPetAdoption petAdoptionRepo = PetAdoptionRepo.getInstance(url, user, password,petId,userId,petParent,emailId,contact,address,reason,status);
	    	String adoptionFlag = petAdoptionRepo.adoptAPet();
	    	
	    	if(adoptionFlag == "viewpets") {
	    		return "redirect:/viewpets/"+userId;
	    	}	
	    	else {
		    		return "redirect:/viewpets/"+userId+"/adoptapet/"+userId;
		    	}
	    }    
}
