package com.example.employee.service;

import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer containing business logic for Employee operations.
 */
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // Constructor injection for dependency injection
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    /**
     * Retrieve all employees.
     *
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    /**
     * Retrieve an employee by ID.
     *
     * @param id Employee ID
     * @return Optional of Employee
     */
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    /**
     * Create a new employee.
     *
     * @param employee Employee data to create
     * @return Created employee with assigned ID
     */
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    /**
     * Update an existing employee.
     *
     * @param id Employee ID to update
     * @param updatedDetails New employee details
     * @return Optional containing updated employee if found, otherwise empty
     */
    public Optional<Employee> updateEmployee(Long id, Employee updatedDetails) {
        Optional<Employee> existingEmployeeOpt = employeeRepository.findById(id);

        if (existingEmployeeOpt.isPresent()) {
            Employee existingEmployee = existingEmployeeOpt.get();
            existingEmployee.setName(updatedDetails.getName());
            existingEmployee.setDepartment(updatedDetails.getDepartment());
            existingEmployee.setSalary(updatedDetails.getSalary());

            Employee savedEmployee = employeeRepository.save(existingEmployee);
            return Optional.of(savedEmployee);
        }

        return Optional.empty();
    }

    /**
     * Delete an employee by ID.
     *
     * @param id Employee ID
     * @return true if deleted, false if not found
     */
    public boolean deleteEmployee(Long id) {
        return employeeRepository.deleteById(id);
    }
}
