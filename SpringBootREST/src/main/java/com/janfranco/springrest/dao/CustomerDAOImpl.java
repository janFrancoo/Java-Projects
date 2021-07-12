package com.janfranco.springrest.dao;

import com.janfranco.springrest.dao.abstracts.CustomerDAO;
import com.janfranco.springrest.entity.Customer;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Customer> getList() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Customer> query = currentSession.createQuery("from Customer", Customer.class);
        return query.getResultList();
    }
}
