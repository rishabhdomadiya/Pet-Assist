package com.team13.petassist.repo;

import com.team13.petassist.config.DatabaseConnection;
import com.team13.petassist.entity.PetItems;
import com.team13.petassist.interfaces.IPetItems;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetItemRepo implements IPetItems {
    private static final String SELECT_ALL_PET_SERVICES = "SELECT * FROM FoodItems";
    private static final String SELECT_PET_SERVICES_BY_ITEM_ID = "SELECT * FROM FoodItems WHERE ItemId =?";
    private final String uname;
    private final String pwd;
    private final String url;
    private static PetItemRepo INSTANCE;



    public PetItemRepo(String uname, String pwd, String url) {
        super();
        this.uname = uname;
        this.pwd = pwd;
        this.url = url;
    }

    public static PetItemRepo getInstance(String url, String uname, String pwd) {
        if(INSTANCE == null) {
            INSTANCE = new PetItemRepo(url,uname,pwd);
        }
        return INSTANCE;
    }


    @Override
    public List<PetItems> getPetItems() throws SQLException {
        List<PetItems> itemsList = new ArrayList<>();
        Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();

        PreparedStatement statement = conn.prepareStatement(SELECT_ALL_PET_SERVICES);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            itemsList.add(new PetItems(resultSet));
        }

        conn.close();
        return itemsList;
    }

    @Override
    public PetItems getPetItemByItemId(int itemId) throws SQLException {
        Connection conn = DatabaseConnection.getInstance(url, uname, pwd).connect();
        PreparedStatement statement = conn.prepareStatement(SELECT_PET_SERVICES_BY_ITEM_ID);
        statement.setInt(1, itemId);
        ResultSet resultSet = statement.executeQuery();
        PetItems petItems = null;

        while (resultSet.next()) {
            petItems = new PetItems(resultSet);
        }
        conn.close();
        return petItems;
    }

}
