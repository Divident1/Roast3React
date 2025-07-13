package com.wecp.supplychainapplication.service;


import com.wecp.supplychainapplication.entity.FoodItem;
import com.wecp.supplychainapplication.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodItem addFoodItem(FoodItem foodItem) {

        return foodItemRepository.save(foodItem);
        // complete this method
      
    }

    public FoodItem getFoodItemById(Long itemId) {
        return foodItemRepository.findById(itemId).orElseThrow();
        // complete this method
       
    }

    public List<FoodItem> getAllFoodItems() {
        // complete this method
        return foodItemRepository.findAll();
    
    }
}