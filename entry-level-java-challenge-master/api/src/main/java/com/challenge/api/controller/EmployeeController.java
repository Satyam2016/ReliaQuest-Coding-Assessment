package com.challenge.api.controller;

import com.challenge.api.dto.CreateEmployeeRequest;
import com.challenge.api.model.Employee;
import com.challenge.api.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{uuid}")
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        return employeeService.getEmployeeByUuid(uuid);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(
            @Valid @RequestBody CreateEmployeeRequest request) {

        return employeeService.createEmployee(request);
    }
}