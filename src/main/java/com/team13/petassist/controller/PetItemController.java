package com.team13.petassist.controller;

import com.team13.petassist.entity.PetItems;
import com.team13.petassist.interfaces.IPetItems;
import com.team13.petassist.repo.PetItemRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.SQLException;
import java.util.List;

@Controller
public class PetItemController {

    @Value("${url}")
    String url;
    @Value("${user1}")
    String user;
    @Value("${passwordDb}")
    String password;

    @GetMapping("/pet-items/{userId}")
    public String getPetItems(Model model, @PathVariable("userId") String userId) throws SQLException {

        IPetItems petRepo = PetItemRepo.getInstance(url, user, password);
        List<PetItems> petItems = petRepo.getPetItems();
        model.addAttribute("petItems", petItems);
        model.addAttribute("userId", userId);

        return "pet-items";
    }
}
