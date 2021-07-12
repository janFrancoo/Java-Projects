package com.janfranco.springrest.service;

import com.janfranco.springrest.dao.abstracts.CustomerDAO;
import com.janfranco.springrest.entity.Customer;
import com.janfranco.springrest.service.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getList() {
        List<Customer> customers = customerDAO.getList();
        customers.forEach(customer -> customer.setLastName(customer.getLastName().toUpperCase()));
        return customers;
    }
}
