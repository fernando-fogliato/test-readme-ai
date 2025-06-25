package com.example.springbootcrudapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Department name is required")
    @Size(min = 2, max = 50, message = "Department name must be between 2 and 50 characters")
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Size(max = 200, message = "Description must be less than 200 characters")
    @Column(name = "description")
    private String description;

    @NotBlank(message = "Manager name is required")
    @Size(min = 2, max = 50, message = "Manager name must be between 2 and 50 characters")
    @Column(name = "manager_name", nullable = false)
    private String managerName;

    @Column(name = "manager_email")
    private String managerEmail;

    @Column(name = "location")
    private String location;

    @Min(value = 0, message = "Budget must be positive")
    @Column(name = "budget")
    private Double budget;

    @Min(value = 0, message = "Employee count must be positive")
    @Column(name = "employee_count")
    private Integer employeeCount;

    @Column(name = "active")
    private Boolean active = true;

    // Default constructor
    public Department() {
    }

    // Constructor with parameters
    public Department(String name, String description, String managerName, String managerEmail,
                     String location, Double budget, Integer employeeCount, Boolean active) {
        this.name = name;
        this.description = description;
        this.managerName = managerName;
        this.managerEmail = managerEmail;
        this.location = location;
        this.budget = budget;
        this.employeeCount = employeeCount;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", managerName='" + managerName + '\'' +
                ", managerEmail='" + managerEmail + '\'' +
                ", location='" + location + '\'' +
                ", budget=" + budget +
                ", employeeCount=" + employeeCount +
                ", active=" + active +
                '}';
    }
} 