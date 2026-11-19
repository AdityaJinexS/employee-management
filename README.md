# Employee Management System (Spring Boot REST API)

A clean, lightweight, and professional **Employee Management System** developed using **Java**, **Spring Boot**, and **Maven**. This project demonstrates standard 3-layer architecture, RESTful API design, dependency injection, and Object-Oriented Programming (OOP) principles without unnecessary complexities or external database requirements.

---

## 📋 Table of Contents
1. [Project Overview](#project-overview)
2. [Technology Stack](#technology-stack)
3. [Project Architecture](#project-architecture)
4. [Project Structure](#project-structure)
5. [Prerequisites](#prerequisites)
6. [How to Run the Project](#how-to-run-the-project)
   - [Method 1: IntelliJ IDEA](#method-1-intellij-idea-recommended)
   - [Method 2: Eclipse IDE](#method-2-eclipse-ide)
   - [Method 3: Terminal / Command Prompt (Maven)](#method-3-terminal--command-prompt-maven)
7. [REST API Endpoints & Documentation](#rest-api-endpoints--documentation)
8. [Sample JSON Requests & Responses (Postman Testing)](#sample-json-requests--responses-postman-testing)
9. [Step-by-Step Postman Testing Guide](#step-by-step-postman-testing-guide)

---

## 1. Project Overview

The **Employee Management System** is built to manage organizational employee records efficiently through standard HTTP methods and includes a modern web dashboard.

Each employee record consists of:
- `id` (Long): Unique identifier for the employee.
- `name` (String): Full name of the employee.
- `department` (String): Department name (e.g., Engineering, HR, Finance).
- `salary` (Double): Monthly/Annual salary.

### Key Highlights:
- **Built-in Responsive Web UI**: Serves a sleek, single-page dashboard directly from Spring Boot at `http://localhost:8080/` to test all CRUD operations visually.
- **No External Database Required**: Uses a thread-safe in-memory data store (`ConcurrentHashMap`) to store and manipulate employee records.
- **Clean 3-Layer Architecture**: Separation of concerns across Controller, Service, and Repository layers.
- **Constructor Injection**: Recommended Spring practice for Dependency Injection.
- **Proper HTTP Status Codes**: Returns `200 OK`, `201 Created`, `400 Bad Request`, and `404 Not Found` appropriately.
- **Pure Java**: Standard Java constructors, getters, and setters without third-party annotations like Lombok.

---

## 2. Technology Stack

- **Language:** Java 17+ (Compatible with Java 17, 21, and 25)
- **Framework:** Spring Boot 3.2.5
  - `spring-boot-starter-web` (Spring MVC, REST, Embedded Tomcat)
  - `spring-boot-starter-test` (JUnit 5, Spring Boot Test)
- **Build Tool:** Apache Maven
- **API Testing:** Postman / cURL / Browser

---

## 3. Project Architecture

The application strictly follows the standard **Three-Layer Architecture**:

```
Client (Postman / Browser)
         │
         ▼ [HTTP Request]
┌─────────────────────────────────┐
│        Controller Layer         │  EmployeeController.java
│  (@RestController, Endpoints)   │  Handles HTTP requests & responses
└────────────────┬────────────────┘
                 │
                 ▼ [Method Calls]
┌─────────────────────────────────┐
│          Service Layer          │  EmployeeService.java
│         (Business Logic)        │  Contains core business rules
└────────────────┬────────────────┘
                 │
                 ▼ [Data Access]
┌─────────────────────────────────┐
│        Repository Layer         │  EmployeeRepository.java
│       (In-Memory Storage)       │  ConcurrentHashMap & AtomicLong ID
└────────────────┬────────────────┘
                 │
                 ▼
┌─────────────────────────────────┐
│           Model Layer           │  Employee.java
│       (Data Entity / POJO)      │  id, name, department, salary
└─────────────────────────────────┘
```

---

## 4. Project Structure

```
employee-management/
│
├── pom.xml
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/employee/
│   │   │       │
│   │   │       ├── EmployeeManagementApplication.java
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   └── EmployeeController.java
│   │   │       │
│   │   │       ├── model/
│   │   │       │   └── Employee.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   └── EmployeeService.java
│   │   │       │
│   │   │       └── repository/
│   │   │           └── EmployeeRepository.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   │           └── index.html
│   │
│   └── test/
│       └── java/
│           └── com/example/employee/
│               └── EmployeeManagementApplicationTests.java
│
└── README.md
```

---

## 5. Prerequisites

Before running the project, ensure you have:
1. **Java Development Kit (JDK 17 or higher)** installed:
   ```bash
   java -version
   ```
2. **Apache Maven 3.8+** (or use an IDE with bundled Maven like IntelliJ IDEA / Eclipse).

---

## 6. How to Run the Project

### Method 1: IntelliJ IDEA (Recommended)
1. Open IntelliJ IDEA.
2. Click **File -> Open...** and select the `employee-management` folder.
3. IntelliJ will automatically detect it as a Maven project and resolve the dependencies.
4. Navigate to:
   `src/main/java/com/example/employee/EmployeeManagementApplication.java`
5. Right-click on `EmployeeManagementApplication` and choose **Run 'EmployeeManagementApplication'** (or click the green Play icon next to the `main` method).
6. The application will start on **`http://localhost:8080`**.

### Method 2: Eclipse IDE
1. Open Eclipse.
2. Select **File -> Import... -> Maven -> Existing Maven Projects**.
3. Browse and select the `employee-management` directory, then click **Finish**.
4. Right-click on `EmployeeManagementApplication.java` -> **Run As -> Java Application** (or **Spring Boot App**).
5. Verify in the Eclipse Console that the server started on port 8080.

### Method 3: Terminal / Command Prompt (Maven)
1. Open a terminal / command prompt and navigate to the project directory:
   ```bash
   cd employee-management
   ```
2. Compile and run using Maven:
   ```bash
   mvn clean spring-boot:run
   ```
3. Or build the JAR package and run:
   ```bash
   mvn clean package
   java -jar target/employee-management-0.0.1-SNAPSHOT.jar
   ```

---

## 7. REST API Endpoints & Documentation

| HTTP Method | Endpoint | Description | Request Body | Success Status | Error Status |
|-------------|----------|-------------|--------------|----------------|--------------|
| **POST** | `/api/employees` | Create a new employee | JSON (Employee) | `201 Created` | `400 Bad Request` |
| **GET** | `/api/employees` | Retrieve all employees | None | `200 OK` | - |
| **GET** | `/api/employees/{id}` | Retrieve employee by ID | None | `200 OK` | `404 Not Found` |
| **PUT** | `/api/employees/{id}` | Update existing employee | JSON (Employee) | `200 OK` | `404 Not Found` / `400 Bad Request` |
| **DELETE** | `/api/employees/{id}` | Delete employee by ID | None | `200 OK` | `404 Not Found` |

---

## 8. Sample JSON Requests & Responses (Postman Testing)

The system automatically initializes with 3 default records for quick testing:
- ID `1`: Aditya Sharma (Software Engineering, 75000.0)
- ID `2`: Priya Patel (Human Resources, 55000.0)
- ID `3`: Rahul Verma (Finance, 65000.0)

---

### Operation 1: Create Employee (POST)
- **Method:** `POST`
- **URL:** `http://localhost:8080/api/employees`
- **Headers:** `Content-Type: application/json`
- **Request Body (raw JSON):**
  ```json
  {
    "name": "Neha Gupta",
    "department": "Quality Assurance",
    "salary": 60000.0
  }
  ```
- **Response Status:** `201 Created`
- **Response Body:**
  ```json
  {
    "id": 4,
    "name": "Neha Gupta",
    "department": "Quality Assurance",
    "salary": 60000.0
  }
  ```

---

### Operation 2: Retrieve All Employees (GET)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/employees`
- **Headers:** None
- **Response Status:** `200 OK`
- **Response Body:**
  ```json
  [
    {
      "id": 1,
      "name": "Aditya Sharma",
      "department": "Software Engineering",
      "salary": 75000.0
    },
    {
      "id": 2,
      "name": "Priya Patel",
      "department": "Human Resources",
      "salary": 55000.0
    },
    {
      "id": 3,
      "name": "Rahul Verma",
      "department": "Finance",
      "salary": 65000.0
    },
    {
      "id": 4,
      "name": "Neha Gupta",
      "department": "Quality Assurance",
      "salary": 60000.0
    }
  ]
  ```

---

### Operation 3: Retrieve Employee by ID (GET)
- **Method:** `GET`
- **URL:** `http://localhost:8080/api/employees/1`
- **Response Status:** `200 OK`
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "Aditya Sharma",
    "department": "Software Engineering",
    "salary": 75000.0
  }
  ```

**Non-existent Employee Scenario:**
- **URL:** `http://localhost:8080/api/employees/99`
- **Response Status:** `404 Not Found`

---

### Operation 4: Update Employee (PUT)
- **Method:** `PUT`
- **URL:** `http://localhost:8080/api/employees/1`
- **Headers:** `Content-Type: application/json`
- **Request Body (raw JSON):**
  ```json
  {
    "name": "Aditya Sharma",
    "department": "Cloud & DevOps",
    "salary": 90000.0
  }
  ```
- **Response Status:** `200 OK`
- **Response Body:**
  ```json
  {
    "id": 1,
    "name": "Aditya Sharma",
    "department": "Cloud & DevOps",
    "salary": 90000.0
  }
  ```

**Non-existent Employee Scenario:**
- **URL:** `http://localhost:8080/api/employees/99`
- **Response Status:** `404 Not Found`

---

### Operation 5: Delete Employee (DELETE)
- **Method:** `DELETE`
- **URL:** `http://localhost:8080/api/employees/2`
- **Response Status:** `200 OK`
- **Response Body:**
  ```text
  Employee with ID 2 has been successfully deleted.
  ```

**Non-existent Employee Scenario:**
- **URL:** `http://localhost:8080/api/employees/99`
- **Response Status:** `404 Not Found`
- **Response Body:**
  ```text
  Employee with ID 99 not found.
  ```

---

## 9. Step-by-Step Postman Testing Guide

1. **Launch Postman**.
2. **Create a New Collection**: Click on **Collections -> New Collection** and name it `Employee Management API`.
3. **Test POST (Create Employee)**:
   - Click **Add Request** -> Name it `Create Employee`.
   - Set HTTP Method to `POST`.
   - Set URL: `http://localhost:8080/api/employees`.
   - Under the **Headers** tab, ensure `Content-Type` is set to `application/json`.
   - Under the **Body** tab, select **raw** and choose **JSON** from the dropdown.
   - Paste the sample JSON request body and click **Send**.
   - Check that the status returned is `201 Created`.
4. **Test GET (All Employees)**:
   - Click **Add Request** -> Name it `Get All Employees`.
   - Set HTTP Method to `GET`.
   - Set URL: `http://localhost:8080/api/employees`.
   - Click **Send**. Status should be `200 OK`.
5. **Test GET (By ID)**:
   - Set HTTP Method to `GET`.
   - Set URL: `http://localhost:8080/api/employees/1`.
   - Click **Send**. Status should be `200 OK`.
6. **Test PUT (Update Employee)**:
   - Set HTTP Method to `PUT`.
   - Set URL: `http://localhost:8080/api/employees/1`.
   - Provide the updated JSON in the Body tab -> click **Send**. Status should be `200 OK`.
7. **Test DELETE (Delete Employee)**:
   - Set HTTP Method to `DELETE`.
   - Set URL: `http://localhost:8080/api/employees/2`.
   - Click **Send**. Status should be `200 OK`.
