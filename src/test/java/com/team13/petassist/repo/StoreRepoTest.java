package com.team13.petassist.repo;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.team13.petassist.entity.Store;
import com.team13.petassist.interfaces.IStore;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class StoreRepoTest {

	@Value("${url_test1}")
    String url;
    @Value("${user_test1}")
    String user ;
    @Value("${passwordDb_test1}")
    String passwordDb ;
    

    @Test
    public void getStoreIdTest() throws SQLException {
    	IStore storeRepo = StoreRepo.getInstance(url, user, passwordDb);
    	List<Store> storeList = storeRepo.getStoreId();
		assertNotNull(storeList);
    }
    
    @Test
    void getFilteredStoreTest() throws SQLException {
    	String locationName = "b";
    	IStore storeRepo = StoreRepo.getInstance(url, user, passwordDb);
    	List<Store> storeList = storeRepo.getFilteredStores(locationName);
		assertNotNull(storeList);
    }
	
    @Test
    void getFilteredStoreNegativeTest() throws SQLException {
    	String locationName = "street b";
    	IStore storeRepo = StoreRepo.getInstance(url, user, passwordDb);
    	List<Store> storeList = storeRepo.getFilteredStores(locationName);
		assertTrue(storeList.isEmpty());
    }

}
