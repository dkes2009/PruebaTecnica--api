# API Prueba Técnica: User and Ticket Management System

This project is a Spring Boot application that provides RESTful API endpoints for managing users and tickets, with JWT-based authentication.

The API Prueba Técnica is designed to handle user registration, authentication, and ticket management operations. It incorporates robust security measures, including JWT token-based authentication, and provides comprehensive logging for monitoring and troubleshooting.

## Repository Structure

```
.
├── Documentacion
│   └── ColleccionPostman
│       └── PruebaTecnica.postman_collection.json
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── ApiPruebatecnica
│       │           ├── ApiPruebatecnicaApplication.java
│       │           ├── Config
│       │           ├── Controller
│       │           ├── DTO
│       │           ├── Entity
│       │           ├── Repository
│       │           ├── Service
│       │           ├── ServiceImpl
│       │           └── utils
│       └── resources
│           ├── application-dev.properties
│           ├── application.properties
│           └── templates
│               └── index.html
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

Key Files:
- `ApiPruebatecnicaApplication.java`: Main entry point for the Spring Boot application
- `ConsumeController.java`: Handles API endpoints for user and ticket operations
- `JwtRequestFilter.java`: Intercepts and validates JWT tokens in incoming requests
- `WebSecurityConfig.java`: Configures security settings for the application
- `ConsumoServiceImpl.java`: Implements business logic for user and ticket management
- `JwtUtilService.java`: Provides utility methods for JWT token generation and validation
- `pom.xml`: Maven project configuration file

## Usage Instructions

### Installation

Prerequisites:
- Java 11
- Maven 3.6+

Steps:
1. Clone the repository
2. Navigate to the project root directory
3. Run `mvn clean install` to build the project and download dependencies

### Getting Started

1. Start the application:
   ```
   mvn spring-boot:run
   ```
2. The API will be available at `http://localhost:8080` (default port)

### Configuration

Key configuration files:
- `application.properties`: Main configuration file
- `application-dev.properties`: Development-specific configuration

Important properties:
- `application.name`: Application name used in JWT tokens
- `token.secrect.key`: Secret key for JWT token generation
- `token.time`: JWT token expiration time

### API Endpoints

1. User Management:
   - Create User: `POST /CrearUsuario`
   - Update User: `POST /ActualizarUsuario`
   - List Users: `POST /ListarUsuario`
   - Get User: `POST /ConsultarUsuario`

2. Ticket Management:
   - Create Ticket: `POST /CrearTicket`
   - Edit Ticket: `POST /EditarTicket`
   - Delete Ticket: `POST /EliminarTicket`

3. Authentication:
   - Authenticate: `POST /authenticate`

Example (Create User):
```json
POST /CrearUsuario
{
  "nombre": "John",
  "apellidos": "Doe"
}
```

### Testing & Quality

To run tests:
```
mvn test
```

### Troubleshooting

Common issues:

1. JWT Token Expiration
   - Error: "JWT token has expired"
   - Solution: Ensure the client is using a valid, non-expired token. Check the `token.time` property in the configuration.

2. Database Connection Issues
   - Error: "Could not connect to database"
   - Steps:
     1. Check database credentials in `application.properties`
     2. Ensure the database server is running and accessible
     3. Verify network connectivity to the database server

Debugging:
- Enable debug logging by adding `logging.level.root=DEBUG` to `application.properties`
- Check log files in the `Logs-ApiPruebatecnica` directory

Performance Optimization:
- Monitor database query performance
- Use JProfiler or VisualVM for performance profiling
- Consider caching frequently accessed data

## Data Flow

The request data flow through the application follows these steps:

1. Client sends a request to an API endpoint
2. `JwtRequestFilter` intercepts the request and validates the JWT token
3. If the token is valid, the request is passed to the appropriate controller
4. The controller validates the input data using the `Validaciones` class
5. The controller calls the corresponding service method
6. The service implements the business logic, interacting with repositories as needed
7. The repository performs database operations
8. The service processes the result and returns it to the controller
9. The controller formats the response and sends it back to the client

```
Client -> JwtRequestFilter -> Controller -> Service -> Repository -> Database
       <-                 <-            <-         <-            <-
```

Note: All operations are logged using the `Utils` class for monitoring and troubleshooting purposes.