package com.example.springbootcrudapp.controller;

import com.example.springbootcrudapp.entity.Department;
import com.example.springbootcrudapp.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/departments")
@CrossOrigin(origins = "*")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // GET /api/departments - Get all departments
    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/{id} - Get department by ID
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id) {
        Optional<Department> department = departmentService.getDepartmentById(id);
        if (department.isPresent()) {
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // POST /api/departments - Create a new department
    @PostMapping
    public ResponseEntity<?> createDepartment(@Valid @RequestBody Department department) {
        try {
            Department savedDepartment = departmentService.createDepartment(department);
            return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // PUT /api/departments/{id} - Update department
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable Long id, @Valid @RequestBody Department departmentDetails) {
        try {
            Department updatedDepartment = departmentService.updateDepartment(id, departmentDetails);
            return new ResponseEntity<>(updatedDepartment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // DELETE /api/departments/{id} - Delete department
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable Long id) {
        try {
            departmentService.deleteDepartment(id);
            return new ResponseEntity<>("Department deleted successfully", HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/departments/name/{name} - Get department by name
    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name) {
        Optional<Department> department = departmentService.getDepartmentByName(name);
        if (department.isPresent()) {
            return new ResponseEntity<>(department.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // GET /api/departments/search/name?name={name} - Search departments by name
    @GetMapping("/search/name")
    public ResponseEntity<List<Department>> searchDepartmentsByName(@RequestParam String name) {
        List<Department> departments = departmentService.searchDepartmentsByName(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/search/manager?name={name} - Search departments by manager name
    @GetMapping("/search/manager")
    public ResponseEntity<List<Department>> searchDepartmentsByManagerName(@RequestParam String name) {
        List<Department> departments = departmentService.searchDepartmentsByManagerName(name);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/search/description?description={description} - Search departments by description
    @GetMapping("/search/description")
    public ResponseEntity<List<Department>> searchDepartmentsByDescription(@RequestParam String description) {
        List<Department> departments = departmentService.searchDepartmentsByDescription(description);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/location/{location} - Get departments by location
    @GetMapping("/location/{location}")
    public ResponseEntity<List<Department>> getDepartmentsByLocation(@PathVariable String location) {
        List<Department> departments = departmentService.getDepartmentsByLocation(location);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/active - Get active departments
    @GetMapping("/active")
    public ResponseEntity<List<Department>> getActiveDepartments() {
        List<Department> departments = departmentService.getActiveDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/inactive - Get inactive departments
    @GetMapping("/inactive")
    public ResponseEntity<List<Department>> getInactiveDepartments() {
        List<Department> departments = departmentService.getInactiveDepartments();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/budget?min={amount} - Get departments with budget greater than amount
    @GetMapping("/budget")
    public ResponseEntity<List<Department>> getDepartmentsByBudget(@RequestParam Double min) {
        List<Department> departments = departmentService.getDepartmentsByBudgetGreaterThan(min);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/employees?min={count} - Get departments with employee count greater than count
    @GetMapping("/employees")
    public ResponseEntity<List<Department>> getDepartmentsByEmployeeCount(@RequestParam Integer min) {
        List<Department> departments = departmentService.getDepartmentsByEmployeeCountGreaterThan(min);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/manager-email/{email} - Get departments by manager email
    @GetMapping("/manager-email/{email}")
    public ResponseEntity<List<Department>> getDepartmentsByManagerEmail(@PathVariable String email) {
        List<Department> departments = departmentService.getDepartmentsByManagerEmail(email);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // GET /api/departments/filter?active={active}&location={location} - Get departments by active status and location
    @GetMapping("/filter")
    public ResponseEntity<List<Department>> getDepartmentsByActiveAndLocation(
            @RequestParam Boolean active, 
            @RequestParam String location) {
        List<Department> departments = departmentService.getDepartmentsByActiveAndLocation(active, location);
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    // PUT /api/departments/{id}/activate - Activate department
    @PutMapping("/{id}/activate")
    public ResponseEntity<?> activateDepartment(@PathVariable Long id) {
        try {
            Department department = departmentService.activateDepartment(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/departments/{id}/deactivate - Deactivate department
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateDepartment(@PathVariable Long id) {
        try {
            Department department = departmentService.deactivateDepartment(id);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/departments/{id}/budget - Update department budget
    @PutMapping("/{id}/budget")
    public ResponseEntity<?> updateDepartmentBudget(@PathVariable Long id, @RequestBody Double budget) {
        try {
            Department department = departmentService.updateDepartmentBudget(id, budget);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // PUT /api/departments/{id}/employees - Update department employee count
    @PutMapping("/{id}/employees")
    public ResponseEntity<?> updateDepartmentEmployeeCount(@PathVariable Long id, @RequestBody Integer employeeCount) {
        try {
            Department department = departmentService.updateDepartmentEmployeeCount(id, employeeCount);
            return new ResponseEntity<>(department, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
} 