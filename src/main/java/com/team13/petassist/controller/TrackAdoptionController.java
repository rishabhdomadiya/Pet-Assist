package com.team13.petassist.controller;

import java.sql.SQLException;
import java.util.List;

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
public class TrackAdoptionController {
	
	@Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password ;
    
    @RequestMapping(value = "/trackapplications/{userId}")
    public String getPetByUserId(@ModelAttribute(name = "petAdoptionForm") PetAdoptionForm petAdoptionForm, Model model, @PathVariable("userId") String userId) throws SQLException {

    	IPetAdoption adoptionRepo = PetAdoptionRepo.getInstance1(url,user,password);
    	List<PetAdoptionForm> adoptionList = adoptionRepo.getAdoptionListByUserId(userId);
    	model.addAttribute("userId", userId);
    	model.addAttribute("adoptionList", adoptionList);
    	return "trackapplications";
    }

}
