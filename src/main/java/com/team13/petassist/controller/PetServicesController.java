package com.team13.petassist.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.petassist.entity.PetForm;
import com.team13.petassist.interfaces.IPetForm;
import com.team13.petassist.repo.PetServicesRepo;

@Controller
public class PetServicesController {
	
	@Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password ;
	
    @RequestMapping(value = "/PetServices/{userId}")
    public String PetServicePage(@ModelAttribute PetForm petForm,Model theModel,@PathVariable String userId) throws SQLException
	{
    	PetServicesRepo psRepo = new PetServicesRepo(url, user, password);
		
		String animal = petForm.getAnimal();
		System.out.println(animal);
		List<PetForm> petModel= new ArrayList<PetForm>();
    	
    	if(animal != null && !animal.equalsIgnoreCase("Select an Option")) {
    		theModel.addAttribute("PetSerF", psRepo.getFilteredServices(animal));
    		theModel.addAttribute("AnimalName", animal);
    	}
    	else {
    		petModel= psRepo.getGroomDefaultServcies();
    		theModel.addAttribute("PetSer", petModel);
    	}
    	IPetForm petFormm = new PetForm();
    	theModel.addAttribute("PetServices", petFormm);
		return "PetServices";
	}
    
	

}
