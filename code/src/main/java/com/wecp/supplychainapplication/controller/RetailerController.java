package com.wecp.supplychainapplication.controller;

import com.wecp.supplychainapplication.entity.Retailer;
import com.wecp.supplychainapplication.service.RetailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retailers")
public class RetailerController {
    @Autowired
    private RetailerService retailerService;

    @PostMapping
    public Retailer addRetailer(@RequestBody Retailer retailer) {

        return retailerService.addRetailer(retailer);
        
        // implement this method
        
    }

    @GetMapping("/{retailerId}")
    public Retailer getRetailerById(@PathVariable Long retailerId) throws Exception {
        return retailerService.getRetailerById(retailerId);
    }

    @GetMapping
    public List<Retailer> getAllRetailers() {
        return retailerService.getAllRetailers();
        // implement this method
  

    }
}