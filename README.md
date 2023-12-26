
# Spring Boot With Database ( SQL, NoSQL )

This project applied simple CRUD e-commerce API with an extendable Data Layer and allowed code extendability for connecting to either SQL as MySQL or NoSQL as MongoDB


## Problem

The problem that we face is that when we make the service layer tightly coupled with the data layer, it makes the code hard to maintain and develop. If you write the code for Service Layer Tight Coupled with Data Layer and for some reason you have a new change that says "We need to Switch from SQL to NoSQL DB" or vice versa, then that is the problem; the code is not able to be extendable for adding or changing the Data Layer to another Database


## Solution
To solve this problem, we need to refactor our code to be able to change or add any database by isolating the data layer from the service layer by sending only the objects that each of them (data layer, service layer) knows and the data layer converts the object to new object that he can store in the database (SQL, NoSQL)


## Architecture
As we see here can extend our Data Layer by adding any Database
![Spring-With-Database drawio (1)](https://github.com/youssefGamalMohamed/Spring-With-Database/assets/47324621/5d511efe-2f9e-41fb-975f-12d8efcb4729)


## Code
In the Service Layer when we need to tell the Data Layer which Database we need to use then we need to change only the Bean name to the Bean of the required Data Layer implementation as an example

- **ProductServiceImpl.java**
  here we need to change the qualifier name for the implementation of the database repository
    ```
    @Autowired
    @Qualifier("mongoProductRepoImpl") <--------------
    private ProductRepoInterface productRepo;
    ```
- **CategoryServiceImpl.java**
  and also here we will follow the same step
    ```
    @Autowired
    @Qualifier("mongoCategoryRepoImpl") <--------------
    private CategoryRepoInterface categoryRepo;
    ```

## Installation Prerequisites
- Programming Languages
  - > Java 17
    
- Databases
  - > MySQL 8
  - > MongoDB
    
- Build Tool
  - > Maven 


## Postman Collection 
- Download Postman Collection From [here](https://github.com/youssefGamalMohamed/Spring-With-Database/blob/0a7f08ea1c8ee4bc88e2437f85e990599afc1d55/Ecommerce.postman_collection.json)
