package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.entity.Customer;
import com.example.springbootcrudapp.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // GET /api/customers - Get all customers
    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/{id} - Get customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomerById(id);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /api/customers - Create a new customer
    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody Customer customer) {
        try {
            Customer savedCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/customers/{id} - Update customer
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @Valid @RequestBody Customer customerDetails) {
        try {
            Customer updatedCustomer = customerService.updateCustomer(id, customerDetails);
            return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/customers/{id} - Delete customer
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        try {
            customerService.deleteCustomer(id);
            return new ResponseEntity<>("Customer deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/customers/email/{email} - Get customer by email
    @GetMapping("/email/{email}")
    public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        if (customer.isPresent()) {
            return new ResponseEntity<>(customer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/customers/search/company?name={name} - Search customers by company name
    @GetMapping("/search/company")
    public ResponseEntity<List<Customer>> searchCustomersByCompanyName(@RequestParam String name) {
        List<Customer> customers = customerService.searchCustomersByCompanyName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/search/contact?name={name} - Search customers by contact name
    @GetMapping("/search/contact")
    public ResponseEntity<List<Customer>> searchCustomersByContactName(@RequestParam String name) {
        List<Customer> customers = customerService.searchCustomersByContactName(name);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/city/{city} - Get customers by city
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Customer>> getCustomersByCity(@PathVariable String city) {
        List<Customer> customers = customerService.getCustomersByCity(city);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/country/{country} - Get customers by country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Customer>> getCustomersByCountry(@PathVariable String country) {
        List<Customer> customers = customerService.getCustomersByCountry(country);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/active - Get active customers
    @GetMapping("/active")
    public ResponseEntity<List<Customer>> getActiveCustomers() {
        List<Customer> customers = customerService.getActiveCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/inactive - Get inactive customers
    @GetMapping("/inactive")
    public ResponseEntity<List<Customer>> getInactiveCustomers() {
        List<Customer> customers = customerService.getInactiveCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/credit-limit?min={amount} - Get customers with credit limit greater than amount
    @GetMapping("/credit-limit")
    public ResponseEntity<List<Customer>> getCustomersByCreditLimit(@RequestParam Double min) {
        List<Customer> customers = customerService.getCustomersByCreditLimitGreaterThan(min);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/phone/{phone} - Get customers by phone
    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Customer>> getCustomersByPhone(@PathVariable String phone) {
        List<Customer> customers = customerService.getCustomersByPhone(phone);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // GET /api/customers/filter?active={active}&country={country} - Get customers by active status and country
    @GetMapping("/filter")
    public ResponseEntity<List<Customer>> getCustomersByActiveAndCountry(
            @RequestParam Boolean active, 
            @RequestParam String country) {
        List<Customer> customers = customerService.getCustomersByActiveAndCountry(active, country);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    // PUT /api/customers/{id}/activate - Activate customer
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateCustomer(@PathVariable Long id) {
        try {
            Customer customer = customerService.activateCustomer(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/customers/{id}/deactivate - Deactivate customer
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateCustomer(@PathVariable Long id) {
        try {
            Customer customer = customerService.deactivateCustomer(id);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
} 