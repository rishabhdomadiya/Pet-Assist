package com.team13.petassist.controller;

import com.team13.petassist.entity.UserDetails;
import com.team13.petassist.repo.LoginRepo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.*;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm() {
    	System.out.println("login");
    	
        return "login";
    }
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getLandingPage() {
        return "index";
    }
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String getHome() {
        return "home";
    }
    
    @Value("${url}")
    String url;
    @Value("${user1}")
    String user ;
    @Value("${passwordDb}")
    String passwordDb ;
    

    @RequestMapping(value = {"/login"}, method = RequestMethod.POST , params = "Login")
    public String login(@ModelAttribute(name = "login") UserDetails login, Model model) throws SQLException {
    	System.out.println("loginform");
        String userName = login.getUserName();
        String password = login.getPassword();

        UserDetails uDetails =  UserDetails.getInstance();
        LoginRepo lr=LoginRepo.getInstance(url, user, passwordDb);

        Boolean loginFlag=lr.login(userName,password);
		
		uDetails = lr.getUserDetails(userName, password); 
		String userId = uDetails.getUserId();
        boolean isCustomer = uDetails.getUserRole().equals("Customer");
		model.addAttribute("userId", userId);
		model.addAttribute("userName", userName);
		model.addAttribute("password", password);
		model.addAttribute("isCustomer", isCustomer);
		ForgetPasswordController.userEmail = login.getEmail();
		
        if (loginFlag==null) {
            return "login";
        }

       
        else if (loginFlag==true){
            return "home";
        }
        
        else {
        	model.addAttribute("invalidCredentials", true);
            return "login";
        }  
    }
    
    @RequestMapping(value = {"/logout"})
    public String logoutDo()
    {
        return "redirect:/index";
    }
    
   
    
    @RequestMapping(value = "/login", method = RequestMethod.POST , params = "Forget Password")
    public String ForgetPassword(@ModelAttribute(name = "login") UserDetails login, Model model) throws SQLException {
    	System.out.println("heloo");
    	model.addAttribute("UserDetails", UserDetails.getInstance());	
    	return "ForgetPassword";
    }
}
