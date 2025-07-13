package com.wecp.springinsurancemanagementapplication.controller;


import com.wecp.springinsurancemanagementapplication.entity.Policy;
import com.wecp.springinsurancemanagementapplication.service.PolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/policies")
public class PolicyController {

    private PolicyService policyService;


    public Policy addPolicy(@RequestBody Policy policy) {
        // add policy
    }


    public void deletePolicy(@PathVariable Long policyId) {
       // delete policy
    }


    public List<Policy> getAllPolicies() {
        // get all policies
    }
}
