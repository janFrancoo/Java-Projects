package com.janfranco.springcrud.service;

import com.janfranco.springcrud.dao.abstracts.CustomerDAO;
import com.janfranco.springcrud.entity.Customer;
import com.janfranco.springcrud.service.abstracts.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDAO customerDAO;

    @Override
    @Transactional
    public List<Customer> getCustomers() { return customerDAO.getCustomers(); }

    @Override
    @Transactional
    public Customer getCustomer(int id) {
        return customerDAO.getCustomer(id);
    }

    @Override
    @Transactional
    public void saveCustomer(Customer customer) {
        customerDAO.saveCustomer(customer);
    }

    @Override
    @Transactional
    public void deleteCustomer(int id) { customerDAO.deleteCustomer(id); }
}
