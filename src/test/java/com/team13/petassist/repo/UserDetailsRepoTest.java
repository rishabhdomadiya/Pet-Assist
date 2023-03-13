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
public class UserDetailsRepoTest {
	
	@Value("${url_test1}")
    String url;
    @Value("${user_test1}")
    String user ;
    @Value("${passwordDb_test1}")
    String passwordDb ;
    
    @Test
	void userDetailsTestSucess() throws SQLException {
    	
    	String userName = "Chandanj";
    	String pass = "ChandanShukla";
    	String cPass = "ChandanShukla";
    	String userEmail = "Chandanj@gmail.com";
    	
    	UserDetailsRepo uDR = UserDetailsRepo.getInstance(userEmail,userName, pass, cPass, user, passwordDb, url);
    	String usersign = uDR.SignUp();
    	assertEquals(usersign,"userexist");
    }
    
    @Test
	void userDetailsTestFail() throws SQLException {
    	
    	String userName = "bbc";
    	String pass = "ChandanShukla";
    	String cPass = "ChandanShukla";
    	String userEmail = "Chandan@gmail.com";
    	
    	UserDetailsRepo uDR = UserDetailsRepo.getInstance(userEmail,userName, pass, cPass, user, passwordDb, url);
    	String usersign = uDR.SignUp();
    	assertEquals(usersign,"userexist");
    }

}
