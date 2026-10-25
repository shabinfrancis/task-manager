# Task Manager

A simple Spring Boot REST API for managing tasks. This project provides CRUD operations for tasks, including validation and structured error handling.

## Features

- Create a new task
- List all tasks
- Fetch a single task by ID
- Update an existing task
- Delete a task
- Input validation for task title, description, and priority
- Centralized exception handling for not-found errors
- In-memory storage for quick local development

## Tech Stack

- Java 17
- Spring Boot 4.1.0
- Maven
- Jakarta Validation
- RESTful API architecture

## Project Structure

```text
src/
├── main/
│   ├── java/com/example/app/
│   │   ├── controller/
│   │   │   └── TaskController.java
│   │   ├── dto/
│   │   │   ├── TaskRequest.java
│   │   │   └── TaskResponse.java
│   │   ├── exception/
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── TaskNotFoundException.java
│   │   ├── model/
│   │   │   └── Task.java
│   │   ├── service/
│   │   │   └── TaskService.java
│   │   └── TaskManagerApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/com/example/app/
        └── TaskManagerApplicationTests.java
```

## API Endpoints

Base URL: `http://localhost:8080`

### Create task

- Method: `POST`
- Endpoint: `/tasks`
- Request body:

```json
{
  "title": "Implement login page",
  "description": "Create a responsive login screen for the app",
  "priority": 2
}
```

### Get all tasks

- Method: `GET`
- Endpoint: `/tasks`

### Get task by ID

- Method: `GET`
- Endpoint: `/tasks/{id}`

### Update task

- Method: `PUT`
- Endpoint: `/tasks/{id}`
- Request body:

```json
{
  "title": "Implement login page",
  "description": "Create a responsive login screen and connect it to auth flow",
  "priority": 1
}
```

### Delete task

- Method: `DELETE`
- Endpoint: `/tasks/{id}`

## Validation Rules

The task request includes validation rules:

- `title` is required
- `title` must be between 3 and 100 characters
- `description` can be up to 500 characters
- `priority` must be at least 1

## Getting Started

### Prerequisites

- Java 17 or later
- Maven 3.9+

### Run the application

```bash
./mvnw spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

### Run tests

```bash
./mvnw test
```

## Notes

This project currently uses an in-memory list to store tasks, so data is lost when the application is restarted. It is suitable for learning, testing, and local development.

## License

This project does not currently include a license file.
