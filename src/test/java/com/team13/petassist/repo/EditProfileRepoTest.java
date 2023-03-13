package com.team13.petassist.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team13.petassist.controller.EditProfileController;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class EditProfileRepoTest {
	
	@Value("${url_test1}")
    String url;
    @Value("${user_test1}")
    String user ;
    @Value("${passwordDb_test1}")
    String passwordDb ;
    
    @Test
	void editProfileTestSucess() {
		
    	EditProfileController.userId = "19";
    	String email="bbcd@gmail.com";
        String userName="bbcd";
        String password="bbcd";
        int age = 9;
        long userContact = 989709899;
        String sex = "M";
        String dob = "2021-11-22";
        String address = "aaaa";
        
        EditProfileRepo ePR = EditProfileRepo.getInstance(email, userName, password,age, userContact, sex, dob, address, url, user, passwordDb);
		try {
			String editProfileFlag=ePR.UpdateUserDetails();
			
			assertEquals(editProfileFlag,"EmailAlreadyExist");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
    
    
    @Test
	void editProfileTestFail() {
		
    	EditProfileController.userId = "20";
    	String emails="dqwewqew@gmail.com";
        String userName="bbc";
        String password="bbc";
        int age = 9;
        long userContact = 989709899;
        String sex = "M";
        String dob = "2021-11-22";
        String address = "aaaa";
        
        EditProfileRepo ePR = EditProfileRepo.getInstance(emails, userName, password,age, userContact, sex, dob, address, url, user, passwordDb);
		try {
			String editProfileFlag=ePR.UpdateUserDetails();
			
			assertEquals(editProfileFlag,"UserUpdated");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}

}