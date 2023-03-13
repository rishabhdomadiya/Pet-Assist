package com.team13.petassist.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.petassist.entity.PetForm;
import com.team13.petassist.interfaces.IPet;
import com.team13.petassist.repo.PetRepo;

@Controller
public class MyPetController {
	
	    @Value("${url}")
	    String url;
	    @Value("${user1}")
	    String user;
	    @Value("${passwordDb}")
	    String password ;
	
	    @RequestMapping(value = "/my-pets/{userId}")
	    public String getPetByUserId(Model model, @PathVariable("userId") String userId) throws SQLException {
	        
	    	IPet petRepo = PetRepo.getInstance(url, user, password);
	        List<PetForm> userPets = petRepo.getPetByUserId(userId);
	        model.addAttribute("userId", userId);
	        model.addAttribute("userPets", userPets);
	        return "my-pets";
	    }

}
