package com.example.springbootcrudapp.service;

import com.example.springbootcrudapp.entity.Department;
import com.example.springbootcrudapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by ID
    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    // Create a new department
    public Department createDepartment(Department department) {
        // Check if department name already exists
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Department name already exists: " + department.getName());
        }
        return departmentRepository.save(department);
    }

    // Update department
    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

        // Check if name is being changed and if it already exists
        if (!department.getName().equals(departmentDetails.getName()) && 
            departmentRepository.existsByName(departmentDetails.getName())) {
            throw new RuntimeException("Department name already exists: " + departmentDetails.getName());
        }

        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        department.setManagerName(departmentDetails.getManagerName());
        department.setManagerEmail(departmentDetails.getManagerEmail());
        department.setLocation(departmentDetails.getLocation());
        department.setBudget(departmentDetails.getBudget());
        department.setEmployeeCount(departmentDetails.getEmployeeCount());
        department.setActive(departmentDetails.getActive());

        return departmentRepository.save(department);
    }

    // Delete department
    public void deleteDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        departmentRepository.delete(department);
    }

    // Find department by name
    public Optional<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    // Search departments by manager name
    public List<Department> searchDepartmentsByManagerName(String managerName) {
        return departmentRepository.findByManagerNameContainingIgnoreCase(managerName);
    }

    // Search departments by name
    public List<Department> searchDepartmentsByName(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }

    // Search departments by description
    public List<Department> searchDepartmentsByDescription(String description) {
        return departmentRepository.findByDescriptionContainingIgnoreCase(description);
    }

    // Find departments by location
    public List<Department> getDepartmentsByLocation(String location) {
        return departmentRepository.findByLocation(location);
    }

    // Find active departments
    public List<Department> getActiveDepartments() {
        return departmentRepository.findByActive(true);
    }

    // Find inactive departments
    public List<Department> getInactiveDepartments() {
        return departmentRepository.findByActive(false);
    }

    // Find departments by budget greater than
    public List<Department> getDepartmentsByBudgetGreaterThan(Double budget) {
        return departmentRepository.findByBudgetGreaterThan(budget);
    }

    // Find departments by employee count greater than
    public List<Department> getDepartmentsByEmployeeCountGreaterThan(Integer employeeCount) {
        return departmentRepository.findByEmployeeCountGreaterThan(employeeCount);
    }

    // Find departments by manager email
    public List<Department> getDepartmentsByManagerEmail(String managerEmail) {
        return departmentRepository.findByManagerEmail(managerEmail);
    }

    // Find departments by active status and location
    public List<Department> getDepartmentsByActiveAndLocation(Boolean active, String location) {
        return departmentRepository.findByActiveAndLocation(active, location);
    }

    // Activate department
    public Department activateDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setActive(true);
        return departmentRepository.save(department);
    }

    // Deactivate department
    public Department deactivateDepartment(Long id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setActive(false);
        return departmentRepository.save(department);
    }

    // Update department budget
    public Department updateDepartmentBudget(Long id, Double budget) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setBudget(budget);
        return departmentRepository.save(department);
    }

    // Update department employee count
    public Department updateDepartmentEmployeeCount(Long id, Integer employeeCount) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));
        department.setEmployeeCount(employeeCount);
        return departmentRepository.save(department);
    }
} 