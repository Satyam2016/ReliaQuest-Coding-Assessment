# Employee Management REST API

## Overview

This project is a Spring Boot REST API developed as part of the
ReliaQuest Entry-Level Java Challenge.

It exposes employee information through a REST API using a layered
architecture. As required by the challenge, no database is used;
employee data is stored in memory using a ConcurrentHashMap.

The project follows a layered architecture to keep responsibilities 
separated and the codebase easy to maintain and extend.

## Technology Stack

-   Java 21
-   Spring Boot
-   Spring Web
-   Spring Validation
-   Gradle
-   ConcurrentHashMap (In-Memory Storage)

## Project Structure

``` text
src/main/java
└── com.challenge.api
    ├── controller/
    │    └── EmployeeController
    │
    ├── service/
    │     ├── EmployeeService
    │     └── EmployeeServiceImpl
    │
    ├── repository/
    │     ├── EmployeeRepository
    │     └── InMemoryEmployeeRepository
    │
    ├── dto/
    │     └── CreateEmployeeRequest
    │
    └── model/
    |    ├── Employee
    |    └── EmployeeImpl
    └── EntryLevelJavaChallengeApplication
```

## Architecture

``` text
                    HTTP Request
                        │
                        ▼
                 EmployeeController
                        │
                        ▼
                  EmployeeService
                        │
                        ▼
                EmployeeServiceImpl
                        │
                        ▼
                 EmployeeRepository
                        │
                        ▼
              InMemoryEmployeeRepository
                        │
                        ▼
             ConcurrentHashMap (Mock Data)
```

## Design Decisions

### Layered Architecture
``` text
The application separates concerns into Controller, Service, and Repository layers to improve maintainability and readability.
```
### Dependency Injection
``` text
Constructor injection is used throughout the application to reduce coupling and improve testability.
```
### Repository Abstraction
``` text
A repository interface is introduced even though the application uses an in-memory implementation. This makes it easy to replace the storage mechanism with a database in the future without changing the service layer.
```
### DTO-Based API
``` text
The API accepts a dedicated request DTO instead of exposing the domain model directly. This keeps the API contract independent from the internal model.
```
### Mock Persistence
``` text
As specified in the challenge, employee data is stored using an in-memory ConcurrentHashMap initialized with sample employee records.
```


## APIs

### GET /api/v1/employee

Returns all employees.
!

### GET /api/v1/employee/{uuid}

Returns an employee by UUID.

### POST /api/v1/employee

Creates a new employee.

Sample JSON:

``` json
{
  "firstName":"Satyam",
  "lastName":"Kumar",
  "salary":120000,
  "age":22,
  "jobTitle":"Software Engineer",
  "email":"satyam@example.com"
}
```

## Validation

-   First name required
-   Last name required
-   Salary \> 0
-   Age \>= 18
-   Valid email
-   Job title required

## Running

Build:

``` bash
./gradlew build
```

From  IntelliJ Run:

``` bash
EntryLevelJavaChallengeApplication
```

Application: `http://localhost:8080`

