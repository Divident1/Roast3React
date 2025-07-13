package com.wecp.supplychainapplication.repository;


import com.wecp.supplychainapplication.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{
    // use JpaRepository to access the database
}
