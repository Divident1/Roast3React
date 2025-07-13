# Library Management System

This is a Spring Boot-based web application for a simplified Library Management System. The application allows users to manage book records, user accounts, and borrowing operations with basic security features.

## Features

- **User Registration and Authentication**: Users can register and log in to the system. Passwords are securely stored using BCrypt encoding.
- **Role-Based Access Control**: Users can have roles such as "USER" (who can borrow books) and "ADMIN" (who can manage books and users).
- **Book Management**: Admin users can create, update, delete, and view books in the library.
- **Borrowing Books**: Users can borrow and return books, with checks for availability.
- **User Account Management**: Users can view and update their account details.

## Project Structure

```
library-management-system
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── wecp
│   │   │           └── library_management_system_jwt
│   │   │               ├── LibraryManagementSystemApplication.java
│   │   │               ├── config
│   │   │               │   ├── JwtAuthenticationFilter.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── controller
│   │   │               │   ├── BookController.java
│   │   │               │   └── UserController.java
│   │   │               ├── dto
│   │   │               │   ├── AuthRequest.java
│   │   │               │   └── AuthResponse.java
│   │   │               ├── entity
│   │   │               │   ├── Book.java
│   │   │               │   └── User.java
│   │   │               ├── exception
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── repository
│   │   │               │   ├── BookRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── service
│   │   │               │   ├── BookService.java
│   │   │               │   ├── JwtService.java
│   │   │               │   └── UserService.java
│   │   │               └── util
│   │   │                   └── JwtUtil.java
│   │   └── resources
│   │       ├── application.properties
│   │       ├── static
│   │       └── templates
│   └── test
│       └── java
│           └── com
│               └── wecp
│                   └── library_management_system_jwt
│                       ├── controller
│                       │   ├── BookControllerTest.java
│                       │   └── UserControllerTest.java
│                       └── service
│                           ├── BookServiceTest.java
│                           └── UserServiceTest.java
├── pom.xml
└── README.md
```

## Setup Instructions

1. **Clone the Repository**: 
   ```
   git clone <repository-url>
   cd library-management-system
   ```

2. **Build the Project**: 
   ```
   mvn clean install
   ```

3. **Run the Application**: 
   ```
   mvn spring-boot:run
   ```

4. **Access the API**: The application will be available at `http://localhost:8080`.

## Usage Guidelines

- **User Registration**: POST to `/users/register` with a JSON body containing `username`, `password`, and `role`.
- **User Login**: POST to `/users/login` with a JSON body containing `username` and `password` to receive a JWT token.
- **Book Management**: Admin users can manage books using the `/books` endpoints.
- **Borrowing Books**: Users can borrow and return books using the `/books/{bookId}/borrow` and `/books/{bookId}/return` endpoints.

## Testing

Unit tests are provided for controllers and services. Run the tests using:
```
mvn test
```

## License

This project is licensed under the MIT License.