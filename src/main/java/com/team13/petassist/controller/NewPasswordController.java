package com.team13.petassist.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team13.petassist.entity.UserDetails;
import com.team13.petassist.repo.AuthorizationsRepo;
import com.team13.petassist.repo.ForgetPasswordRepo;

@Controller
public class NewPasswordController {
	
	@Value("${url}")
    String url;
	@Value("${user1}")
    String dbUsername ;
    @Value("${passwordDb}")
    String dbPassword ;

	
    @RequestMapping(value = "/NewUserPassword",method = RequestMethod.POST)
    public String newPassword(@ModelAttribute(name = "NewPass1") UserDetails NewPass1, Model theModel) throws SQLException {

	String password = NewPass1.getPassword();
	String cPassword = NewPass1.getConfirmPassword();
	String email = ForgetPasswordController.userEmail;
	
	if(!cPassword.equalsIgnoreCase(password)) {
		theModel.addAttribute("PassNotMatch",true); 
		theModel.addAttribute("NewPass1", UserDetails.getInstance()); 
	  return  "NewUserPassword"; 
	  }
	 
	ForgetPasswordRepo FPR = ForgetPasswordRepo.getInstance(email,password,cPassword, url, dbUsername, dbPassword);
	
	try
	{

		String setUserPass = FPR.updateUserDetails();
		theModel.addAttribute("PassUpdated", true);
	    return "login";
	}
	catch(Exception E)
	{
		theModel.addAttribute("UDNotUpdated",true);
		theModel.addAttribute("email", email);
		theModel.addAttribute("NewPass1", UserDetails.getInstance());
		return "NewUserPassword"; 
	}
	
}

}
