package com.janfranco.springrest.dao;

/*
import com.janfranco.springrest.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> getList() {
        TypedQuery<Customer> query = entityManager.createQuery("from Customer", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getCustomer(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        Customer dbCustomer = entityManager.merge(customer);
        customer.setId(dbCustomer.getId());
    }

    @Override
    public void deleteCustomer(int id) {
        Query query = entityManager.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", id);
        query.executeUpdate();
    }
}
*/
