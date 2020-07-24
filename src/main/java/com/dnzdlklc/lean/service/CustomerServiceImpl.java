package com.dnzdlklc.lean.service;

import com.dnzdlklc.lean.exception.CustomEntityNotFoundException;
import com.dnzdlklc.lean.model.dao.CustomerDAO;
import com.dnzdlklc.lean.model.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by denizdalkilic on 20/07/2020 @ 19:54.
 */
@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerDAO customerDAO;

    public Customer getCustomer(Long customerID) {
        return customerDAO.findById(customerID)
                .orElseThrow(() -> new CustomEntityNotFoundException("No customer found with ID: " + customerID));
    }
}