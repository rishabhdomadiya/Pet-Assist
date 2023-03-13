package com.team13.petassist.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.petassist.entity.PetForm;
import com.team13.petassist.entity.UserDetails;
import com.team13.petassist.interfaces.IPet;
import com.team13.petassist.repo.LoginRepo;
import com.team13.petassist.repo.PetRepo;

@Controller
public class ViewPetByIdController {
	
    @Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password ;
	
   @RequestMapping(value = "/viewpets/{userId}/adoptapet/{id}")
    public String getPetById(@ModelAttribute(name="petForm") PetForm petForm, Model model,@PathVariable("id") String id, @PathVariable("userId") String userId) throws SQLException, ParseException {
    	
    	IPet petRepo = PetRepo.getInstance(url, user, password);
    	LoginRepo loginRepo=LoginRepo.getInstance(url, user, password);
    	UserDetails uDetails =  UserDetails.getInstance();
    	uDetails = loginRepo.getUserDetailsById(userId);
    	model.addAttribute("user", uDetails.getUserName());
    	model.addAttribute("email", uDetails.getEmail());
    	List<PetForm> petListById = petRepo.getPetById(id);
    	model.addAttribute("id", id);
    	model.addAttribute("userId", userId);
    	model.addAttribute("petListById", petListById);
    	return "adoptapet";
    }

}
