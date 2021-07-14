package com.janfranco.SpringCRUDThymeleaf.dao;

import com.janfranco.SpringCRUDThymeleaf.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {

    // Spring Data JPA handles custom operations by @Query annotation or by method name
    List<Customer> findAllByOrderByLastNameDesc();
}
