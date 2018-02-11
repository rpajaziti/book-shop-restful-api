package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Customer> getCustomers() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<Customer> theQuery =
                currentSession.createQuery("FROM Customer ORDER BY lastName",
                        Customer.class);

        return theQuery.getResultList();
    }

    @Override
    public void saveOrUpdateCustomer(Customer theCustomer) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(String id) {
        Session currentSession = sessionFactory.getCurrentSession();
        return currentSession.get(Customer.class, id);
    }
}