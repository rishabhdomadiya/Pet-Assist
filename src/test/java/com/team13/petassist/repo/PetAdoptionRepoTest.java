package com.team13.petassist.repo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team13.petassist.entity.PetAdoptionForm;
import com.team13.petassist.interfaces.IPetAdoption;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PetAdoptionRepoTest {

	
	@Value("${url_test1}")
    String url;
    @Value("${user_test1}")
    String user ;
    @Value("${passwordDb_test1}")
    String passwordDb ;
    
    @Test
    void AdoptAPetTest() throws SQLException {
    	IPetAdoption petAdoptionRepo =  PetAdoptionRepo.getInstance(url, user, passwordDb,"","","","","","","","");
    	String result = petAdoptionRepo.adoptAPet();
		assertEquals(result,"viewpets");
    	
    }
    
    @Test
    void AdoptAPetNegativeTest() throws SQLException {

    	IPetAdoption petAdoptionRepo = PetAdoptionRepo.getInstance(url, user, passwordDb,"","","","","","","","");
    	String result = petAdoptionRepo.adoptAPet();
		assertNotEquals(result,"viewPets");
    }
    
    @Test
    void getAdoptionListByUserIdTest() throws SQLException {
    	String userId = "1";
    	IPetAdoption petAdoptionRepo = PetAdoptionRepo.getInstance1(url, user, passwordDb);
    	List<PetAdoptionForm> adoptionList = petAdoptionRepo.getAdoptionListByUserId(userId);
    	assertNotNull(adoptionList);
    }
    
    @Test
    void deleteAdoptionItemTest() throws SQLException {
    	String id = "7";
    	IPetAdoption petAdoptionRepo = PetAdoptionRepo.getInstance(url, user, passwordDb,"","","","","","","","");
    	int result = petAdoptionRepo.deleteAdoptionItem(id);
		assertEquals(result,0);
    }
    
    @Test
    void getPetReqByIdTest() throws SQLException{
    	IPetAdoption petAdoptionRepo = PetAdoptionRepo.getInstance1(url, user, passwordDb);
    	PetAdoptionForm ret = petAdoptionRepo.getPetReqById("1");
    	assertNotNull(ret);
    }
    
    @Test
    void updateOrderTest() throws SQLException{
    	IPetAdoption petAdoptionRepo = PetAdoptionRepo.getInstance1(url, user, passwordDb);
    	
    	PetAdoptionForm pf=petAdoptionRepo.getPetReqById("1");
    	pf.setAddress("HalifaxNew");
    	Boolean result = petAdoptionRepo.updatePetRequest(pf);
    	assertTrue(result);
    	
    }

}
