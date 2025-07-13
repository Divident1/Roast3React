package com.wecp.supplychainapplication.entity;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class FoodItem {
    // implement the FoodItem entity here
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;
    private String itemName;
    private String type;

    @ManyToOne
    @JoinColumn(name="retailer_IDD")
    private Retailer retailer;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Retailer getRetailer() {
        return retailer;
    }

    public void setRetailer(Retailer retailer) {
        this.retailer = retailer;
    }

    public FoodItem(Long itemId, String itemName, String type, Retailer retailer) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.type = type;
        this.retailer = retailer;
    }

    public FoodItem() {
    }

}