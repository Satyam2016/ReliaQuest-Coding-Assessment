package com.challenge.api.repository;

import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    @PostConstruct
    public void initialize() {
        loadMockEmployees();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees.values());
    }

    @Override
    public Optional<Employee> findByUuid(UUID uuid) {
        return Optional.ofNullable(employees.get(uuid));
    }

    @Override
    public Employee save(Employee employee) {
        employees.put(employee.getUuid(), employee);
        return employee;
    }

    private void loadMockEmployees() {

        save(createMockEmployee(
                "John",
                "Doe",
                85000,
                30,
                "Software Engineer",
                "john.doe@example.com"));

        save(createMockEmployee(
                "Jane",
                "Smith",
                95000,
                28,
                "Backend Developer",
                "jane.smith@example.com"));

        save(createMockEmployee(
                "Alex",
                "Johnson",
                105000,
                35,
                "Engineering Manager",
                "alex.johnson@example.com"));
    }


    private Employee createMockEmployee(
            String firstName,
            String lastName,
            Integer salary,
            Integer age,
            String jobTitle,
            String email) {

        Employee employee = new EmployeeImpl();

        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setFullName(firstName + " " + lastName);
        employee.setSalary(salary);
        employee.setAge(age);
        employee.setJobTitle(jobTitle);
        employee.setEmail(email);
        employee.setContractHireDate(Instant.now());
        employee.setContractTerminationDate(null);

        return employee;
    }
}