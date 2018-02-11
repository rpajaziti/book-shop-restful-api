package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.dao.CustomerDAO;
import com.rpajaziti.bookshop.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    @Transactional
    public List<Customer> getCustomers() {
        return customerDAO.getCustomers();
    }

    @Override
    @Transactional
    public Customer saveOrUpdateCustomer(Customer customer) {
        customer.setCreatedAt(System.currentTimeMillis() / 1000L);
        customerDAO.saveOrUpdateCustomer(customer);
        return customer;
    }

    @Override
    @Transactional
    public Customer getCustomer(String id) {
        return customerDAO.getCustomer(id);
    }
}