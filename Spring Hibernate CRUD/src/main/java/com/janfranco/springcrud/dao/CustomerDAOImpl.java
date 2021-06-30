package com.janfranco.springcrud.dao;

import com.janfranco.springcrud.dao.abstracts.CustomerDAO;
import com.janfranco.springcrud.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Customer> getCustomers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Customer> query = currentSession.createQuery("from Customer order by lastName", Customer.class);
        return query.getResultList();
    }

    @Override
    public Customer getCustomer(int id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, id);
    }

    @Override
    public void saveCustomer(Customer customer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(customer);
    }

    @Override
    public void deleteCustomer(int customerId) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("delete from Customer where id=:customerId");
        query.setParameter("customerId", customerId);
        query.executeUpdate();
    }
}
