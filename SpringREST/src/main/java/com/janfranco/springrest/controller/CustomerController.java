package com.janfranco.springrest.controller;

import com.janfranco.springrest.entity.Customer;
import com.janfranco.springrest.service.abstracts.CustomerService;
import com.janfranco.springrest.util.exception.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> getCustomerList() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable("id") int id) {
        Customer customer = customerService.getCustomer(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer not found");
        }
        return customer;
    }

    @PostMapping("/")
    public Customer addCustomer(@RequestBody Customer customer) {
        customer.setId(0); // to force new customer
        customerService.saveCustomer(customer);
        return customer;
    }

    @PutMapping("/")
    public Customer updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    public boolean deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return true;
    }
}
