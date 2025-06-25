package com.example.springbootcrudapp.repository;

import com.example.springbootcrudapp.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    // Find department by name
    Optional<Department> findByName(String name);

    // Find departments by manager name containing (case-insensitive)
    List<Department> findByManagerNameContainingIgnoreCase(String managerName);

    // Find departments by location
    List<Department> findByLocation(String location);

    // Find active departments
    List<Department> findByActive(Boolean active);

    // Custom query to find departments by budget greater than
    @Query("SELECT d FROM Department d WHERE d.budget > :budget")
    List<Department> findByBudgetGreaterThan(@Param("budget") Double budget);

    // Custom query to find departments by employee count greater than
    @Query("SELECT d FROM Department d WHERE d.employeeCount > :employeeCount")
    List<Department> findByEmployeeCountGreaterThan(@Param("employeeCount") Integer employeeCount);

    // Find departments by manager email
    @Query("SELECT d FROM Department d WHERE d.managerEmail = :managerEmail")
    List<Department> findByManagerEmail(@Param("managerEmail") String managerEmail);

    // Check if department exists by name
    boolean existsByName(String name);

    // Find departments by active status and location
    List<Department> findByActiveAndLocation(Boolean active, String location);

    // Find departments by name containing (case-insensitive)
    List<Department> findByNameContainingIgnoreCase(String name);

    // Find departments by description containing (case-insensitive)
    List<Department> findByDescriptionContainingIgnoreCase(String description);
} 