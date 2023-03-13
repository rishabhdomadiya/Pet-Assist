package com.team13.petassist.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetItems {

    public int itemId;
    public String category;
    public String itemName;
    public String brand;
    public String animals;
    public int itemQuantity;
    public String itemDescription;
    public Double itemWeight;
    public Double cost;


    public PetItems(ResultSet rs) throws SQLException {
        itemId = rs.getInt("ItemId");
        category = rs.getString("Category");
        itemName = rs.getString("ItemName");
        brand = rs.getString("Brand");
        animals = rs.getString("Animals");
        itemQuantity = rs.getInt("ItemQuantity");
        itemDescription = rs.getString("ItemDescription");
        itemWeight = rs.getDouble("ItemWeight");
        cost = rs.getDouble("Cost");
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAnimals() {
        return animals;
    }

    public void setAnimals(String animals) {
        this.animals = animals;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Double getItemWeight() {
        return itemWeight;
    }

    public void setItemWeight(Double itemWeight) {
        this.itemWeight = itemWeight;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }


}
