package com.challenge.api.service;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.model.EmployeeImpl;
import com.challenge.api.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeByUuid(UUID uuid) {
        return employeeRepository
                .findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Employee not found with UUID: " + uuid));
    }

    @Override
    public Employee createEmployee(CreateEmployeeRequest request) {
        Employee employee = buildEmployee(request);
        return employeeRepository.save(employee);
    }

    private Employee buildEmployee(CreateEmployeeRequest request) {

        Employee employee = new EmployeeImpl();

        employee.setUuid(UUID.randomUUID());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setFullName(buildFullName(
                request.getFirstName(),
                request.getLastName()));
        employee.setSalary(request.getSalary());
        employee.setAge(request.getAge());
        employee.setJobTitle(request.getJobTitle());
        employee.setEmail(request.getEmail());
        employee.setContractHireDate(Instant.now());
        employee.setContractTerminationDate(null);

        return employee;
    }

    private String buildFullName(String firstName, String lastName) {
        return firstName.trim() + " " + lastName.trim();
    }
}