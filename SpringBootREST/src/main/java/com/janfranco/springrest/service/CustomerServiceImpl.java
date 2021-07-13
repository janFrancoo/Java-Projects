package com.janfranco.springrest.service;

import com.janfranco.springrest.dao.CustomerRepository;
import com.janfranco.springrest.entity.Customer;
import com.janfranco.springrest.service.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    // @Transactional -> JPA handles it
    public List<Customer> getList() {
        List<Customer> customers = customerRepository.findAll();
        customers.forEach(customer -> customer.setLastName(customer.getLastName().toUpperCase()));
        return customers;
    }

    @Override
    public Customer getById(int id) {
        Optional<Customer> result = customerRepository.findById(id);
        return result.orElse(null);
    }
}
