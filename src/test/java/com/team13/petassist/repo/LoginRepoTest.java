package com.team13.petassist.repo;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class LoginRepoTest {

	 	@Value("${url_test1}")
	    String url;
	    @Value("${user_test1}")
	    String user ;
	    @Value("${passwordDb_test1}")
	    String passwordDb ;
	
	@Test
	void loginTestSucess() {
		
        String userName="sd";
        String password="sd";
        
		LoginRepo lr=LoginRepo.getInstance(url, user, passwordDb);
		try {
			Boolean loginFlag=lr.login(userName,password);
			
			assertEquals(loginFlag,true);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	@Test
	void loginTestFail() {
		
        String userName="12";
        String password="ab";
        
		LoginRepo lr=LoginRepo.getInstance(url, user, passwordDb);
		try {
			Boolean loginFlag=lr.login(userName,password);
			
			assertEquals(loginFlag,false);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	

}
