package com.team13.petassist.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.petassist.entity.PetForm;
import com.team13.petassist.interfaces.IPet;
import com.team13.petassist.repo.PetRepo;

@Controller
public class PetController {

    @Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password ;

    @RequestMapping(value = "/viewpets/{userId}")
    public String getAllPets(@ModelAttribute(name="petForm") PetForm petForm, Model model, String animalName, @PathVariable String userId) throws SQLException {
    	IPet petRepo = PetRepo.getInstance(url, user, password);
    	List<PetForm> petList = petRepo.getAllPets();
    	List<PetForm> animalList = petRepo.getDistinctAnimals();
    	List<PetForm> filteredPets = petRepo.getFilteredPets(animalName);
    	if(animalName != null) {
    		if(filteredPets.size() == 0) {
    			model.addAttribute("error", true);
    		}
    		model.addAttribute("petList", filteredPets);
    	}
    	else {
    		model.addAttribute("petList",petList);
    	}
    	model.addAttribute("userId", userId);
    	model.addAttribute("animalList",animalList);
    	model.addAttribute("animalName",animalName);
    	
    	return "viewpets";
    }
    
}
