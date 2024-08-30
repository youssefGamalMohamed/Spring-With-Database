
# Spring Boot With Database (SQL, NoSQL)

This project implements a simple CRUD e-commerce API with an extendable data layer that supports both SQL (MySQL) and NoSQL (MongoDB) databases.

## Problem

The problem we face is that when the service layer is tightly coupled with the data layer, it becomes difficult to maintain and develop the code. If you need to switch from an SQL to a NoSQL database (or vice versa), the code is not easily extendable to accommodate such changes.

## Solution

To address this issue, we refactor the code to isolate the data layer from the service layer. This allows for the addition or change of databases by sending objects between layers that each knows how to handle, with the data layer converting these objects to the appropriate format for storage in SQL or NoSQL databases.

## Architecture

The architecture allows for easy extension of the data layer by adding any database. 

![Spring-With-Database drawio (1)](https://github.com/youssefGamalMohamed/Spring-With-Database/assets/47324621/5d511efe-2f9e-41fb-975f-12d8efcb4729)

## Features

Implementing multi-database support (SQL, NoSQL) in the data layer with different approaches:
- **JOOQ With MySQL**
- **Spring Jdbc Template With MySQL**
- **Spring Data Jdbc With MySQL**
- **Spring Data JPA With**
   - **MySQL**
   - **MongoDB**

## Code

In the service layer, to specify which database to use, you need to change the bean name to the corresponding data layer implementation. For example:

- **ProductServiceImpl.java**
  Change the qualifier name to the appropriate database repository implementation:
    ```java
    @Autowired
    @Qualifier("mongoProductRepoImpl") <--------------
    private ProductRepoInterface productRepo;
    ```

- **CategoryServiceImpl.java**
  Follow the same step for the category repository:
    ```java
    @Autowired
    @Qualifier("mongoCategoryRepoImpl") <--------------
    private CategoryRepoInterface categoryRepo;
    ```

## Installation Prerequisites

- **Programming Languages**
  - Java 17
    
- **Databases**
  - MySQL 8
  - MongoDB
    
- **Build Tool**
  - Maven 

## Docker Compose

To run the application using Docker Compose, follow these steps:

1. **Ensure Docker and Docker Compose are installed** on your system. You can download Docker Desktop from [Docker's official website](https://www.docker.com/products/docker-desktop).

2. **Clone the repository** if you haven't already:
   ```bash
   git clone https://github.com/youssefGamalMohamed/spring-boot-database.git
   cd spring-boot-database
   ```

3. **Build and run the Docker containers** using Docker Compose:
   ```bash
   docker-compose up --build
   ```

   This command will build the Docker images and start the containers defined in the `docker-compose.yml` file.

4. **Access the application**:
   - The Spring Boot application should be available at `http://localhost:8080` by default.

5. **Stop the Docker containers**:
   To stop the running containers, use:
   ```bash
   docker-compose down
   ```

## Postman Collection 

- Download the Postman Collection from [here](https://github.com/youssefGamalMohamed/Spring-With-Database/blob/0a7f08ea1c8ee4bc88e2437f85e990599afc1d55/Ecommerce.postman_collection.json)
