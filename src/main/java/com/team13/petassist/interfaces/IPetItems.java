package com.team13.petassist.interfaces;

import com.team13.petassist.entity.PetItems;

import java.sql.SQLException;
import java.util.List;

public interface IPetItems {
    List<PetItems> getPetItems() throws SQLException;
    PetItems getPetItemByItemId(int itemId) throws SQLException;
}

