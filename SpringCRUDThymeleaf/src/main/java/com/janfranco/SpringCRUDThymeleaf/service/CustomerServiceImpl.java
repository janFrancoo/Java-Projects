package com.janfranco.SpringCRUDThymeleaf.service;

import com.janfranco.SpringCRUDThymeleaf.dao.CustomerDAO;
import com.janfranco.SpringCRUDThymeleaf.entity.Customer;
import com.janfranco.SpringCRUDThymeleaf.service.abstracts.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customers = customerDAO.findAllByOrderByLastNameDesc();
        customers.forEach(customer -> customer.setLastName(customer.getLastName().toUpperCase()));
        return customers;
    }

    @Override
    public Customer get(int id) {
        Optional<Customer> result = customerDAO.findById(id);
        return result.orElse(null);
    }

    @Override
    public void save(Customer customer) {
        customerDAO.save(customer);
    }

    @Override
    public void delete(int id) {
        customerDAO.deleteById(id);
    }
}
