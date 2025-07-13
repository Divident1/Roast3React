package com.wecp.supplychainapplication.repository;


import com.wecp.supplychainapplication.entity.Retailer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RetailerRepository extends JpaRepository<Retailer, Long> {
    // use JpaRepository to access the database
}