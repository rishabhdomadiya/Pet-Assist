package com.team13.petassist.controller;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.team13.petassist.entity.UserDetails;
import com.team13.petassist.repo.AuthorizationsRepo;
import com.team13.petassist.repo.ForgetPasswordRepo;
import java.sql.*;

@Controller
public class ForgetPasswordController{
	

	@Value("${url}")
    String url;
	@Value("${user1}")
    String dbUsername ;
    @Value("${passwordDb}")
    String dbpassword ;
    
    String randomOTP;
    public static String userEmail;
	
@GetMapping("/ForgetPassword")
public String ForgetPassPage(Model themodel)
{
	
	themodel.addAttribute("UserDetails", UserDetails.getInstance());
	return "ForgetPassword";
}

@PostMapping("/ForgetPassword")
public String ForgetPassPageSubmit(@ModelAttribute(name="ForgetPass") UserDetails ForgetPass, Model theModel) throws SQLException
{

	userEmail = ForgetPass.getEmail();
	
	AuthorizationsRepo aRP = AuthorizationsRepo.getInstance(userEmail, url, dbUsername, dbpassword);
	
	String emailCheck = aRP.EmailAuth();
	
	if(emailCheck.equalsIgnoreCase("doesntexist"))
	{
		theModel.addAttribute("UserDetails",UserDetails.getInstance());
		theModel.addAttribute("invalidCredentials",true);
		return "ForgetPassword";
	}
	
	randomOTP = aRP.GenerateOTP();
	theModel.addAttribute("OTP", randomOTP);
	theModel.addAttribute("email", userEmail);
	theModel.addAttribute("NewPass1", UserDetails.getInstance());
	return "NewUserPassword";
	
}



}
