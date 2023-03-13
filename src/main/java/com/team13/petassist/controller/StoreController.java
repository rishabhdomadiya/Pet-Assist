package com.team13.petassist.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.team13.petassist.entity.Store;
import com.team13.petassist.interfaces.IStore;
import com.team13.petassist.repo.StoreRepo;

@Controller
public class StoreController {

	@Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password ;
    
    @RequestMapping(value = "/store/{userId}")
    public String getStores(@ModelAttribute(name="store") Store store, Model model, @PathVariable String userId, String locationName) throws SQLException{
    	
    	IStore storeRepo = StoreRepo.getInstance(url, user, password);
    	List<Store> storeModel = storeRepo.getStoreId();
    	List<Store> filteredStore = storeRepo.getFilteredStores(locationName);
    	if(locationName != null) {
    		if(filteredStore.size() == 0) {
    			model.addAttribute("error", true);
    		}
    		model.addAttribute("storeModel", filteredStore);
    		model.addAttribute("locationName", locationName);
    	}
    	else {
    		model.addAttribute("storeModel", storeModel);
    	}
    	return "store";
    }

}
