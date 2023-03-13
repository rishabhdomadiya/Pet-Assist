package com.team13.petassist.repo;

import com.team13.petassist.entity.PetItems;
import com.team13.petassist.interfaces.IPetItems;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PetItemRepoTest {

    @Value("${url_test1}")
    String url;
    @Value("${user_test1}")
    String user;
    @Value("${passwordDb_test1}")
    String passwordDb;


    @Test
    public void getPetItemsTest() throws SQLException {
        IPetItems petItemRepo = PetItemRepo.getInstance(url, user, passwordDb);
        List<PetItems> petItems = petItemRepo.getPetItems();
        assertNotNull(petItems);
    }

    @Test
    public void getPetItemByItemIdTest() throws SQLException {
        int id = 1;
        IPetItems petItemRepo = PetItemRepo.getInstance(url, user, passwordDb);
        PetItems petItems = petItemRepo.getPetItemByItemId(id);
        assertNotNull(petItems);
    }
}