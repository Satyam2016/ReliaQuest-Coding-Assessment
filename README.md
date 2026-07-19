# Employee Management REST API

## Overview

This project is a Spring Boot REST API developed as part of the
ReliaQuest Entry-Level Java Challenge.

It exposes employee information through a REST API using a layered
architecture. As required by the challenge, no database is used;
employee data is stored in memory using a ConcurrentHashMap.

## Technology Stack

-   Java 21
-   Spring Boot
-   Spring Web
-   Spring Validation
-   Gradle
-   ConcurrentHashMap (In-Memory Storage)

## Project Structure

``` text
controller/
service/
repository/
dto/
model/
```

## Architecture

``` text
Controller
    ↓
Service
    ↓
Repository
    ↓
ConcurrentHashMap
```

## APIs

### GET /api/v1/employee

Returns all employees.

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

