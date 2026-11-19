package com.example.employee.repository;

import com.example.employee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Repository layer for managing Employee data.
 * Uses an in-memory thread-safe ConcurrentHashMap for storage.
 */
@Repository
public class EmployeeRepository {

    private final Map<Long, Employee> employeeStorage = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public EmployeeRepository() {
        // Pre-populating sample employees for immediate testing
        save(new Employee("Aditya Sharma", "Software Engineering", 75000.0));
        save(new Employee("Priya Patel", "Human Resources", 55000.0));
        save(new Employee("Rahul Verma", "Finance", 65000.0));
    }

    /**
     * Retrieve all employees from storage.
     *
     * @return List of all employees
     */
    public List<Employee> findAll() {
        return new ArrayList<>(employeeStorage.values());
    }

    /**
     * Retrieve an employee by their ID.
     *
     * @param id Employee ID
     * @return Optional containing the employee if found, otherwise empty
     */
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(employeeStorage.get(id));
    }

    /**
     * Save an employee. If ID is not present or non-positive, a new ID is generated.
     *
     * @param employee Employee object to save
     * @return Saved employee with assigned ID
     */
    public Employee save(Employee employee) {
        if (employee.getId() == null || employee.getId() <= 0) {
            employee.setId(idGenerator.incrementAndGet());
        } else {
            // Update idGenerator if a manual higher ID is passed
            idGenerator.updateAndGet(current -> Math.max(current, employee.getId()));
        }
        employeeStorage.put(employee.getId(), employee);
        return employee;
    }

    /**
     * Check if an employee exists with the given ID.
     *
     * @param id Employee ID
     * @return true if exists, false otherwise
     */
    public boolean existsById(Long id) {
        return employeeStorage.containsKey(id);
    }

    /**
     * Delete an employee by their ID.
     *
     * @param id Employee ID
     * @return true if deleted, false if employee was not found
     */
    public boolean deleteById(Long id) {
        return employeeStorage.remove(id) != null;
    }
}
