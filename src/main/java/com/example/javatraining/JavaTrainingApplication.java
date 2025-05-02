package com.example.javatraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 Functional Requirements
Tasks
- Users can:
  - Retrieve all tasks assigned to themselves.
  - Retrieve a specific task assigned to themselves.
  - Update the status of their own tasks only.
- Administrators can:
  - Create, delete, assign, and update any task.

Roles
- The system defines two fixed roles: ADMIN and USER.
- Only administrators can retrieve the list of all roles.

Users
- Only administrators can create new users.
- Only administrators can retrieve the full list of users.
- A user can retrieve only their own user account information.
 */


/*
Non-Functional Requirements:
- Business logic must reside in the service layer; controllers and repositories should not contain business logic.
- Manage database schema changes using Liquibase migrations.
- Implement Pagination/Sorting. (A page only consists not more than 5 entities) 
- Apply the DTO (Data Transfer Object) pattern for data exchange between layers.
- Use mappers for conversions between DTOs and entities.
- Enforce transactional boundaries to ensure data integrity.
- Utilize UUIDs to expose the external identity of domain objects.
- Implement comprehensive security validation.
- Use standardized return types instead of raw responses.
- Regularly remove unused code to maintain codebase cleanliness and readability.
- User roles must be verified by Spring Security using JWT tokens.
- Log when a request enters the controller, flows to the service layer, and triggers SQL queries.
    - Add library-level logging for greater visibility and troubleshooting.
    - Implement structured and detailed logging to trace request flow (from controller to service to database).
- Provide a Docker Compose file aligned with the application for streamlined deployment and development.
- Integrate Swagger UI to allow on-the-fly API testing and documentation.
*/

@SpringBootApplication
public class JavaTrainingApplication {
    public static void main(String[] args) {
        SpringApplication.run(JavaTrainingApplication.class, args);
    }
}
