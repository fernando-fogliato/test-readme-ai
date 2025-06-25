# Spring Boot CRUD Application

A simple Spring Boot REST API application that demonstrates CRUD (Create, Read, Update, Delete) operations with an in-memory H2 database.

## Features

- **RESTful API**: Complete CRUD operations for User management
- **In-Memory Database**: H2 database for development and testing
- **Validation**: Input validation using Bean Validation
- **Exception Handling**: Global exception handler for proper error responses
- **Database Console**: H2 web console for database inspection
- **Sample Data**: Pre-loaded sample users for testing


## gRPC API for Department Service

This application now exposes a gRPC API for managing Department entities, providing an alternative high-performance interface to the existing REST API.

### 1. New Features

A new gRPC service, `DepartmentService`, has been added, offering the following operations for Department entities:

*   **`GetAllDepartments`**: Retrieve a list of all departments.
*   **`GetDepartmentById`**: Retrieve a single department by its unique identifier.
*   **`CreateDepartment`**: Create a new department with provided details.
*   **`UpdateDepartment`**: Update an existing department's details based on its ID.
*   **`DeleteDepartment`**: Delete a department by its unique identifier.

### 2. Setup and Configuration

The gRPC server is integrated with Spring Boot and its port can be configured in your `application.properties` file.

**Example `application.properties` configuration:**

```properties
# gRPC Server Configuration
grpc.server.port=9090
grpc.server.enable=true
```

The `src/main/java/com/example/springbootcrudapp/config/GrpcConfig.java` file is available for any additional gRPC server-side configuration, though most common settings are handled via `application.properties`.

### 3. How to Use

#### 3.1. Protocol Definition

The service contract for the `DepartmentService` is defined using Protocol Buffers. You can find the full definition, including messages and RPC methods, in:

*   **`src/main/proto/department.proto`**

This `.proto` file is the authoritative source for the gRPC API and should be used to generate client stubs in your preferred programming language.

#### 3.2. Client-Side Usage

To interact with the gRPC Department service from a client application:

1.  **Generate Client Stubs**: Use a gRPC plugin (e.g., Protoc Gradle Plugin, Maven Plugin, or `protoc` command-line tool) to generate client-side code from the `department.proto` file.
2.  **Connect to the Service**: Create a `ManagedChannel` pointing to the gRPC server's host and port (e.g., `localhost:9090`).
3.  **Make RPC Calls**: Use the generated client stub to invoke the desired gRPC methods.

**Example Client Implementation:**

A sample gRPC client demonstrating how to connect and make various calls to the `DepartmentService` is provided at:

*   **`src/main/java/com/example/springbootcrudapp/client/DepartmentGrpcClient.java`**

This client showcases how to perform CRUD operations (get all, get by ID, create, update, delete) against the gRPC endpoint.

#### 3.3. Server-Side Implementation

The g

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