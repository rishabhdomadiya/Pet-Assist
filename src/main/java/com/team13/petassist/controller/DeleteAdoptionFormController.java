package com.team13.petassist.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.petassist.entity.PetAdoptionForm;
import com.team13.petassist.interfaces.IPetAdoption;
import com.team13.petassist.repo.PetAdoptionRepo;

@Controller
public class DeleteAdoptionFormController {
	
	@Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password ;
    
	@RequestMapping(value ="/trackapplications/{userId}/deleteadoption/{id}")
	public String deleteAdoption(@ModelAttribute(name = "petAdoptionForm") PetAdoptionForm petAdoptionForm, Model model,@PathVariable("id") String id,@PathVariable("userId") String userId) throws SQLException {

		IPetAdoption adoptionRepo = PetAdoptionRepo.getInstance1(url,user,password);
    	adoptionRepo.deleteAdoptionItem(id);
    	return "redirect:/trackapplications/"+userId;
	}

}
