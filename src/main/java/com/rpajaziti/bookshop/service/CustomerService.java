package com.rpajaziti.bookshop.service;

import com.rpajaziti.bookshop.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer saveOrUpdateCustomer(Customer customer);

    Customer getCustomer(String id);
}
