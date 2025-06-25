package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.entity.Customer;
import com.example.springbootcrudapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Get all customers
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get customer by ID
    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    // Create a new customer
    public Customer createCustomer(Customer customer) {
        // Check if email already exists
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already exists: " + customer.getEmail());
        }
        return customerRepository.save(customer);
    }

    // Update customer
    public Customer updateCustomer(Long id, Customer customerDetails) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        // Check if email is being changed and if it already exists
        if (!customer.getEmail().equals(customerDetails.getEmail()) && 
            customerRepository.existsByEmail(customerDetails.getEmail())) {
            throw new RuntimeException("Email already exists: " + customerDetails.getEmail());
        }

        customer.setCompanyName(customerDetails.getCompanyName());
        customer.setContactName(customerDetails.getContactName());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhone(customerDetails.getPhone());
        customer.setAddress(customerDetails.getAddress());
        customer.setCity(customerDetails.getCity());
        customer.setCountry(customerDetails.getCountry());
        customer.setCreditLimit(customerDetails.getCreditLimit());
        customer.setActive(customerDetails.getActive());

        return customerRepository.save(customer);
    }

    // Delete customer
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

    // Find customer by email
    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    // Search customers by company name
    public List<Customer> searchCustomersByCompanyName(String companyName) {
        return customerRepository.findByCompanyNameContainingIgnoreCase(companyName);
    }

    // Search customers by contact name
    public List<Customer> searchCustomersByContactName(String contactName) {
        return customerRepository.findByContactNameContainingIgnoreCase(contactName);
    }

    // Find customers by city
    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByCity(city);
    }

    // Find customers by country
    public List<Customer> getCustomersByCountry(String country) {
        return customerRepository.findByCountry(country);
    }

    // Find active customers
    public List<Customer> getActiveCustomers() {
        return customerRepository.findByActive(true);
    }

    // Find inactive customers
    public List<Customer> getInactiveCustomers() {
        return customerRepository.findByActive(false);
    }

    // Find customers by credit limit greater than
    public List<Customer> getCustomersByCreditLimitGreaterThan(Double creditLimit) {
        return customerRepository.findByCreditLimitGreaterThan(creditLimit);
    }

    // Find customers by phone
    public List<Customer> getCustomersByPhone(String phone) {
        return customerRepository.findByPhone(phone);
    }

    // Find customers by active status and country
    public List<Customer> getCustomersByActiveAndCountry(Boolean active, String country) {
        return customerRepository.findByActiveAndCountry(active, country);
    }

    // Activate customer
    public Customer activateCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customer.setActive(true);
        return customerRepository.save(customer);
    }

    // Deactivate customer
    public Customer deactivateCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customer.setActive(false);
        return customerRepository.save(customer);
    }
} 