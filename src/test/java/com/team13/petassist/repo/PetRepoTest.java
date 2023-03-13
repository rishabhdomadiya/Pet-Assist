package com.team13.petassist.repo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.team13.petassist.entity.PetForm;
import com.team13.petassist.interfaces.IPet;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class PetRepoTest {

	
	@Value("${url_test1}")
    String url;
    @Value("${user_test1}")
    String user ;
    @Value("${passwordDb_test1}")
    String passwordDb;
    
    @Test
    void getAllPetsTest() throws SQLException {
    	IPet petRepo = PetRepo.getInstance(url, user, passwordDb);
    	List<PetForm> petForm = petRepo.getAllPets();
		assertNotNull(petForm);
    	
    }
    
    @Test
    void getFilteredPetsTest() throws SQLException {
    	String animalName = "Dog";
    	IPet petRepo = PetRepo.getInstance(url, user, passwordDb);
    	List<PetForm> petForm = petRepo.getFilteredPets(animalName);
		assertNotNull(petForm);
    }
    
	 @Test 
	 void getFilteredPetsNegativeTest() throws SQLException { String
	 animalName = "horse"; IPet petRepo = PetRepo.getInstance(url, user, passwordDb);
	 List<PetForm> petForm = petRepo.getFilteredPets(animalName);
	 assertTrue(petForm.isEmpty()); }
	 
    @Test
    void getDistinctAnimalsTest() throws SQLException {
    	IPet petRepo = PetRepo.getInstance(url, user, passwordDb);
    	List<PetForm> petForm = petRepo.getDistinctAnimals();
		assertNotNull(petForm);
    }
    
    @Test
    void getPetByIdTest() throws SQLException {
    	String id = "1";
    	IPet petRepo = PetRepo.getInstance(url, user, passwordDb);
    	List<PetForm> petForm = petRepo.getPetById(id);
		assertNotNull(petForm);
    } 
	
	  @Test void getPetByIdNegativeTest() throws SQLException { 
		  String id = "-1";
		  IPet petRepo = PetRepo.getInstance(url, user, passwordDb); 
		  List<PetForm> petForm = Arrays.asList();
		  petForm = petRepo.getPetById(id);
		  assertTrue(petForm.isEmpty()); }
	  
    @Test
    void getPetByUserIdTest() throws SQLException {
    	String userId = "1";
    	IPet petRepo = PetRepo.getInstance(url, user, passwordDb);
    	List<PetForm> petForm = petRepo.getPetByUserId(userId);
		assertNotNull(petForm);
    }

}
