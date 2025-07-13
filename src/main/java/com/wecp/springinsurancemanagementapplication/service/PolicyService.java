package com.wecp.springinsurancemanagementapplication.service;


import com.wecp.springinsurancemanagementapplication.entity.Policy;
import com.wecp.springinsurancemanagementapplication.repository.PolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class PolicyService {


    private PolicyRepository policyRepository;

    public Policy addPolicy(Policy policy) {
       // add policy to database
    }

    public void deletePolicy(Long policyId) {
       // delete policy from database
    }

    public List<Policy> getAllPolicies() {
      // get all policies from database
    }
}