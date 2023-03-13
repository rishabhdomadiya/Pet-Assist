package com.team13.petassist.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.team13.petassist.entity.UserDetails;
import com.team13.petassist.repo.EditProfileRepo;

@Controller
public class EditProfileController {
	
	@Value("${url}")
    String url;
	@Value("${user1}")
    String dbUsername ;
    @Value("${passwordDb}")
    String dbPassword;
    public static String userId ;
    
    @GetMapping("/EditProfile/{userId}")
    public String EditProfile(Model theModel, @PathVariable String userId)
    {
    	String email = ForgetPasswordController.userEmail;
    	theModel.addAttribute("EditProfile", UserDetails.getInstance());
    	this.userId = userId;
    	System.out.println(userId);
    	theModel.addAttribute("email",email);
    	return "EditProfile";
    }
    
    @PostMapping("/EditProfile")
    public String UserEditProfile(@ModelAttribute UserDetails EditProfile, Model theModel) throws SQLException, ParseException
    {
    	
    	String email = EditProfile.getEmail();
    	String username = EditProfile.getUserName();
    	String password = EditProfile.getPassword();
    	int age = Integer.parseInt(EditProfile.getAge());
    	long userContact = Long.parseLong(EditProfile.getContact());
    	String sex = EditProfile.getSex();

    	String dob = EditProfile.getDob();
    	String address = EditProfile.getAddress();

    	Date newDob=new SimpleDateFormat("yyyy-MM-dd").parse(dob);  
    	
    	EditProfileRepo EPR =  EditProfileRepo.getInstance(email, username, password, age, userContact, sex, dob, address, url, dbUsername, dbPassword);
    	
    	String userUpdate = EPR.UpdateUserDetails();
    	
    	if(userUpdate.equalsIgnoreCase("EmailAlreadyExist"))
    	{
    		theModel.addAttribute("EmailExist",true);
    		theModel.addAttribute("EditProfile", UserDetails.getInstance());
        	return "EditProfile";
    	}
    	else if(userUpdate.equalsIgnoreCase("UserUpdated"))
    	{
    		theModel.addAttribute("UserUpdate","User updated successfully");
    		theModel.addAttribute("EditProfile", UserDetails.getInstance());
        	return "EditProfile";
    	}
    	else
    	{
    		theModel.addAttribute("UserNotUpdate",true);
    		theModel.addAttribute("EditProfile", UserDetails.getInstance());
        	return "EditProfile";
    	}
    }

}
