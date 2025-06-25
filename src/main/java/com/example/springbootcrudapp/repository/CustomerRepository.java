package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Find customer by email
    Optional<Customer> findByEmail(String email);

    // Find customers by company name containing (case-insensitive)
    List<Customer> findByCompanyNameContainingIgnoreCase(String companyName);

    // Find customers by contact name containing (case-insensitive)
    List<Customer> findByContactNameContainingIgnoreCase(String contactName);

    // Find customers by city
    List<Customer> findByCity(String city);

    // Find customers by country
    List<Customer> findByCountry(String country);

    // Find active customers
    List<Customer> findByActive(Boolean active);

    // Custom query to find customers by credit limit greater than
    @Query("SELECT c FROM Customer c WHERE c.creditLimit > :creditLimit")
    List<Customer> findByCreditLimitGreaterThan(@Param("creditLimit") Double creditLimit);

    // Custom query to find customers by phone
    @Query("SELECT c FROM Customer c WHERE c.phone = :phone")
    List<Customer> findByPhone(@Param("phone") String phone);

    // Check if customer exists by email
    boolean existsByEmail(String email);

    // Find customers by active status and country
    List<Customer> findByActiveAndCountry(Boolean active, String country);
} 