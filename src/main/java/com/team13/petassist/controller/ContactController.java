package com.team13.petassist.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.team13.petassist.entity.Contact;
import com.team13.petassist.interfaces.IContact;
import com.team13.petassist.repo.ContactRepo;

@Controller
public class ContactController {
	
	@Value("${url}")
    String url;
    @Value("${user1}")
    String user ;
    @Value("${passwordDb}")
    String password ;
    
    @RequestMapping(value = "/contact/{userId}", method = RequestMethod.GET)
    public String getContactInfo(@ModelAttribute(name = "contact") Contact contact, Model model, @PathVariable String userId) throws SQLException{
    	
    	IContact contactRepoObject = ContactRepo.getInstance(url, user, password);
    	contact = contactRepoObject.getContactDetails();
    	model.addAttribute("userId", userId);
    	model.addAttribute("contact", contact);
        return "contact";
    }
   
}
