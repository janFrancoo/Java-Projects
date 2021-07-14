package com.janfranco.SpringCRUDThymeleaf.service.abstracts;

import com.janfranco.SpringCRUDThymeleaf.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAll();
    Customer get(int id);
    void save(Customer customer);
    void delete(int id);
}
