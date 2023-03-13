package com.team13.petassist.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.team13.petassist.entity.UserDetails;
import com.team13.petassist.repo.UserDetailsRepo;

import java.sql.*;


@Controller
public class UserDetailsController {
	
	@Value("${url}")
    String url;
	@Value("${user1}")
    String dbUsername ;
    @Value("${passwordDb}")
    String dbPassword ;
	
@GetMapping("/Signup")
public String signUpPage(Model theModel)
{
	theModel.addAttribute("UserDetails", UserDetails.getInstance());
	return "SignUpPage";
}

@PostMapping("/Signup")
public String signUpPageSubmit(@ModelAttribute UserDetails UserDetails, Model theModel) throws SQLException
{	
	String userUsername = UserDetails.getUserName();
	String userEmail = UserDetails.getEmail();
	String userPassword = UserDetails.getPassword();
	String userCpassword = UserDetails.getConfirmPassword();
	
	UserDetailsRepo Udr =  UserDetailsRepo.getInstance(userEmail,userUsername,userPassword,userCpassword, dbUsername, dbPassword, url);
	String SignUpFlag=Udr.SignUp();

	if (SignUpFlag==null) {
        return "SignUpPage";
    }
	
	if(!UserDetails.getPassword().equalsIgnoreCase(UserDetails.getConfirmPassword()))
	{
		theModel.addAttribute("PassNotMatch", true);
		theModel.addAttribute("UserDetails", UserDetails.getInstance());
		return "SignUpPage";
	}
	
	if (SignUpFlag.equalsIgnoreCase("userexist")) {
		theModel.addAttribute("UserExist", true);
		theModel.addAttribute("UserDetails", UserDetails.getInstance());
		return "SignUpPage";
    }
	
	if (SignUpFlag.equalsIgnoreCase("SignUpSuccess")) {
		theModel.addAttribute("SignUpSuccess", true);
	    return "login";
    }
	
	return "SignUpPage"; 
	
}

}
