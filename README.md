# Quarkus CRUD API

This project is a simple CRUD (Create, Read, Update, Delete) API implemented using the Quarkus framework. It serves as a learning exercise for Java developers familiar with Spring Boot who want to explore Quarkus and its benefits.

## Introduction

The Quarkus CRUD API project provides a straightforward implementation of a CRUD API using Quarkus. It demonstrates the fundamental concepts of Quarkus, such as dependency injection, RESTful web services, data persistence.

The goal of this project is to help Java developers familiar with Spring Boot transition to Quarkus by comparing the two frameworks and showcasing the benefits of using Quarkus in terms of performance and resource consumption.

# Prerequisite:
- MySQL (8.0 Preferred)
- Java (17 Preferred)

## Project Structure

The project follows a standard Maven project structure. The main components of the project are as follows:

- `src/main/java`: Contains the Java source code of the application.
- `src/main/resources`: Contains the configuration files and static resources.
- `src/test/java`: Contains the test cases for the application.
- `src/test/resources`: Contains the test configuration files.
-------------------------------------------------------------------------------------
- `src/main/java/com/harisraza/resources`:  Contains the resources (controllers) classes responsible for handling the API endpoints.
- `src/main/java/com/harisraza/services`: Contains the service interfaces and inside `/impl` directory contains their respective implementation classes for implementing business logic.
- `src/main/java/com/harisraza/exceptions`: Contains the exception classes and inside `/handles` directory contains their's respective handling classes to handle exception when thrown.

The Quarkus CRUD API exposes the following endpoints:

API Endpoints for Note App:
Retrieve a Note by ID

Endpoint: GET /api/note/{id}
Description: Retrieve a note by its unique ID.
Create a New Note

Endpoint: POST /api/note
Description: Create a new note.
Update an Existing Note

Endpoint: PUT /api/note/{id}
Description: Update an existing note by its ID.
Delete a Note

Endpoint: DELETE /api/note/{id}
Description: Delete a note by its ID.
Get All Notes

Endpoint: GET /api/notes
Description: Retrieve all notes.
Search Notes by Title

Endpoint: GET /api/notes/search?title={title}
Description: Search notes by title.
Update Note Content

Endpoint: PATCH /api/note/{id}
Description: Update the content of a note identified by its ID.
Get Total Number of Notes

Endpoint: GET /api/notes/count
Description: Retrieve the total number of notes.
Get Notes Summary

Endpoint: GET /api/notes/summary
Description: Retrieve a summary of all notes, e.g., their IDs and titles.
Get Recent Notes

Endpoint: GET /api/notes/recent
Description: Retrieve notes created recently, e.g., within the last week.
Get Average Note Length

Endpoint: GET /api/notes/average-length
Description: Calculate and retrieve the average length of note content.
Add Tags to a Note

Endpoint: POST /api/note/{id}/tags
Description: Add tags to a note identified by its ID.
Get Tags of a Note

Endpoint: GET /api/note/{id}/tags
Description: Retrieve tags associated with a note.

The API follows RESTful conventions and returns JSON responses.

# Configuration
Go to the `src/main/resources/` and edit the `application.properties` to configure MySQL Database name and credentials.

## Running the Application
To run the Quarkus CRUD API, execute the following command:

if maven installed:
```mvn compile quarkus:dev```
else: 
```./mvnw compile quarkus:dev```

Have fun coding!


