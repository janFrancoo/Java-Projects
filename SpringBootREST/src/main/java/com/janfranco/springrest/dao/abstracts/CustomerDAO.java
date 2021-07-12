package com.janfranco.springrest.dao.abstracts;

import com.janfranco.springrest.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    List<Customer> getList();
}
