package com.team13.petassist.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team13.petassist.repo.LoginRepo;

@Controller
public class HomeController {
	
	@Value("${url}")
    String url;
    @Value("${user1}")
    String user ;
    @Value("${passwordDb}")
    String passwordDb ;
	
	@RequestMapping(value = "/home/{userId}")
    public String getHome(Model model, @PathVariable String userId) throws SQLException {
		
		LoginRepo uDetails = LoginRepo.getInstance(url, user, passwordDb);
		Boolean isCustomer = uDetails.getUserRoleById(userId).equals("Customer");
		model.addAttribute("isCustomer", isCustomer);
    	model.addAttribute("userId", userId);
		return "home";
	}

}
