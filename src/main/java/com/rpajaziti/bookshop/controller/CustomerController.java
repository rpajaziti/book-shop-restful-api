package com.rpajaziti.bookshop.controller;

import com.rpajaziti.bookshop.custom.ResponseMessage;
import com.rpajaziti.bookshop.entity.Customer;
import com.rpajaziti.bookshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") String id) {
        return new ResponseEntity<>(customerService.getCustomer(id), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> saveCustomer(@RequestBody Customer customer) {
        customer = customerService.saveOrUpdateCustomer(customer);
        if (customer == null) {
            return new ResponseEntity<>(new ResponseMessage().setMessage("Bad Request"), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT, consumes = "application/json")
    public ResponseEntity<ResponseMessage> updateCustomer(@PathVariable("id") String id,
                                                          @RequestBody Customer customer) {
        if (customer.getId() == null) {
            customer.setId(id);
        }
        customerService.saveOrUpdateCustomer(customer);

        return new ResponseEntity<>(new ResponseMessage().setMessage("Updated Successfully."), HttpStatus.OK);
    }
}