package com.rpajaziti.bookshop.dao;

import com.rpajaziti.bookshop.entity.Customer;

import java.util.List;

public interface CustomerDAO {

	List<Customer> getCustomers();

	void saveOrUpdateCustomer(Customer customer);

	Customer getCustomer(String id);
}
