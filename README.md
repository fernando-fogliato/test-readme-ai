# Spring Boot CRUD Application

A simple Spring Boot REST API application that demonstrates CRUD (Create, Read, Update, Delete) operations with an in-memory H2 database.

## Features

- **RESTful API**: Complete CRUD operations for User management
- **In-Memory Database**: H2 database for development and testing
- **Validation**: Input validation using Bean Validation
- **Exception Handling**: Global exception handler for proper error responses
- **Database Console**: H2 web console for database inspection
- **Sample Data**: Pre-loaded sample users for testing


This section details the re-introduction of the Customer Management API.

---

## New Feature: Customer Management API

This update re-introduces a comprehensive set of endpoints for managing customer data. This includes a new `Customer` entity, dedicated repository, service layer, and a RESTful API controller, providing full CRUD (Create, Read, Update, Delete) capabilities.

### Features

*   **Customer Entity**: A new `Customer` entity (`com.example.springbootcrudapp.entity.Customer`) to store customer details including company name, contact person, email, phone, and address information.
*   **CRUD Operations**: Full Create, Read, Update, and Delete (CRUD) capabilities for customer records.
*   **Search Capabilities**: Retrieve customers by ID, email, or company name.
*   **Data Validation**: Input validation applied to customer data fields (e.g., `@NotBlank`, `@Email`, `@Size` constraints) to ensure data integrity.

### API Endpoints

All customer-related operations are accessible under the `/api/customers` base path.

| Method | Endpoint                       | Description                                      |
|

## Technologies Used

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Spring Web
- H2 Database
- Bean Validation
- Gradle

## Project Structure

```
src/
├── main/
│   ├── java/com/example/springbootcrudapp/
│   │   ├── SpringBootCrudApplication.java
│   │   ├── controller/
│   │   │   └── UserController.java
│   │   ├── entity/
│   │   │   └── User.java
│   │   ├── repository/
│   │   │   └── UserRepository.java
│   │   ├── service/
│   │   │   └── UserService.java
│   │   └── exception/
│   │       └── GlobalExceptionHandler.java
│   └── resources/
│       ├── application.properties
│       └── data.sql
├── build.gradle
├── settings.gradle
├── gradlew
├── gradlew.bat
└── gradle/
    └── wrapper/
        └── gradle-wrapper.properties
```

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle 8.4 or higher (or use the included wrapper)

### Installation

1. Clone the repository
2. Navigate to the project directory
3. Run the application using Gradle wrapper:

```bash
./gradlew bootRun
```

Or if you have Gradle installed globally:

```bash
gradle bootRun
```

The application will start on `http://localhost:8080`

### Building the Project

To build the project and create a JAR file:

```bash
./gradlew build
```

To clean the build directory:

```bash
./gradlew clean
```

To run tests:

```bash
./gradlew test
```

### H2 Database Console

Access the H2 database console at: `http://localhost:8080/h2-console`

- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Username**: `sa`
- **Password**: `password`

## API Endpoints

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/users` | Get all users |
| GET | `/api/users/{id}` | Get user by ID |
| POST | `/api/users` | Create a new user |
| PUT | `/api/users/{id}` | Update user by ID |
| DELETE | `/api/users/{id}` | Delete user by ID |
| GET | `/api/users/email/{email}` | Get user by email |
| GET | `/api/users/search?name={name}` | Search users by name |
| GET | `/api/users/phone/{phone}` | Get users by phone |

### Sample API Calls

#### Get All Users
```bash
curl -X GET http://localhost:8080/api/users
```

#### Create a New User
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "address": "123 Main St",
    "phone": "+1-555-0123"
  }'
```

#### Get User by ID
```bash
curl -X GET http://localhost:8080/api/users/1
```

#### Update User
```bash
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Smith",
    "email": "john.smith@example.com",
    "address": "456 Oak Ave",
    "phone": "+1-555-0124"
  }'
```

#### Delete User
```bash
curl -X DELETE http://localhost:8080/api/users/1
```

#### Search Users by Name
```bash
curl -X GET "http://localhost:8080/api/users/search?name=John"
```

## User Model

```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "address": "123 Main St, New York, NY",
  "phone": "+1-555-0101"
}
```

### Validation Rules

- **Name**: Required, 2-50 characters
- **Email**: Required, valid email format, unique
- **Address**: Optional, max 100 characters
- **Phone**: Optional

## Sample Data

The application comes with 5 pre-loaded sample users:
1. John Doe
2. Jane Smith
3. Mike Johnson
4. Sarah Wilson
5. David Brown

## Error Handling

The API provides meaningful error responses:

- **400 Bad Request**: Validation errors or business logic violations
- **404 Not Found**: Resource not found
- **500 Internal Server Error**: Unexpected server errors

Example error response:
```json
{
  "name": "Name is required",
  "email": "Email should be valid"
}
```

## Testing

You can test the API using:
- **cURL** (as shown in examples above)
- **Postman**
- **Browser** (for GET requests)
- **H2 Console** (for direct database queries)

## Configuration

Key configuration properties in `application.properties`:

- **Server Port**: 8080
- **Database URL**: `jdbc:h2:mem:testdb`
- **H2 Console**: Enabled at `/h2-console`
- **JPA**: DDL auto-creation enabled
- **SQL Logging**: Enabled for debugging

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is open source and available under the [MIT License](LICENSE). 