package com.wecp.supplychainapplication.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Retailer {
    // implement retailer entity here

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long retailerId;
    private String retailerName;
    private String storeLocation;
    
    @OneToMany(mappedBy = "retailer" )
    @JsonIgnore
    private List<FoodItem> foodItems;

    public Long getRetailerId() {
        return retailerId;
    }

    public void setRetailerId(Long retailerId) {
        this.retailerId = retailerId;
    }

    public String getRetailerName() {
        return retailerName;
    }

    public void setRetailerName(String retailerName) {
        this.retailerName = retailerName;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }

    public List<FoodItem> getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(List<FoodItem> foodItems) {
        this.foodItems = foodItems;
    }

    public Retailer(Long retailerId, String retailerName, String storeLocation, List<FoodItem> foodItems) {
        this.retailerId = retailerId;
        this.retailerName = retailerName;
        this.storeLocation = storeLocation;
        this.foodItems = foodItems;
    }

    public Retailer() {
    }

   
    

    
    

   
    
}