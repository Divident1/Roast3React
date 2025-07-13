package com.wecp.supplychainapplication.service;


import com.wecp.supplychainapplication.entity.Retailer;
import com.wecp.supplychainapplication.repository.RetailerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class RetailerService {
    @Autowired
    private RetailerRepository retailerRepository;

    public Retailer addRetailer(Retailer retailer) {
       // implement this method
       return retailerRepository.save(retailer);
    }

    public Retailer getRetailerById(Long retailerId) throws Exception {
        // implement this method
        return retailerRepository.findById(retailerId).orElseThrow(()->
        new EntityNotFoundException("Exceptionfound"));
    }

    public List<Retailer> getAllRetailers() {
        // implement this method
        return retailerRepository.findAll();
    }
}
