package com.janfranco.springrest.controller;

import com.janfranco.springrest.entity.Customer;
import com.janfranco.springrest.service.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomerList() {
        return customerService.getList();
    }
}
