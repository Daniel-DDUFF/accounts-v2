package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.model.Customer;
import com.eazybytes.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class CustomersController {

    @Autowired
    CustomerService customerService;

    @Operation(summary = "Get all customers")
    @GetMapping("/customers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @Operation(summary = "Get customer by ID")
    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable int id){
        return customerService.getCustomerById(id);
    }

    @Operation(summary = "Create new customer")
    @PostMapping("/newCustomer")
    public void newCustomer(@RequestBody Customer newCustomer){
        newCustomer.setCreateDt(LocalDate.now());
        customerService.save(newCustomer);
    }

    @Operation(summary = "Update customer by ID")
    @PutMapping("/updateCustomer/{id}")
    public String updateCustomer(@PathVariable("id") int id, @RequestBody Customer updateCustomer){
        return customerService.update(id, updateCustomer);
    }

    @Operation(summary = "Delete customer by ID")
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable("id") int id){
        return customerService.deleteCustomer(id);
    }

    @Operation(summary = "Check if customer exists by ID")
    @GetMapping("/customerExist/{id}")
    public boolean customerExist(@PathVariable("id") int id){
        return customerService.customerExist(id);
    }
}