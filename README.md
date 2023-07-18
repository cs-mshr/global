Configuration And Setup Instruction in the last [Setup](https://github.com/cs-mshr/global/blob/main/README.md#employee-management-application---configuration-and-setup)

### Employee Management Application

## Overview

Provide a brief introduction to the project, explaining its purpose and scope. Mention the features it offers and what problems it aims to solve.

## Table of Contents

- [Project Name](#project-name)
  - [Overview](#overview)
  - [Table of Contents](#table-of-contents)
  - [Technologies Used](#technologies-used)
  - [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
  - [API Documentation](#api-documentation)
    - [Add Employee](#add-employee)
    - [Get All Employees](#get-all-employees)
    - [Delete Employee](#delete-employee)
    - [Update Employee](#update-employee)
    - [Get nth Level Manager](#get-nth-level-manager)
    - [Get Employees with Pagination and Sorting](#get-employees-with-pagination-and-sorting)
  - [Advanced Level](#advanced-level)
    - [Send Email to Level 1 Manager on New Employee Addition](#send-email-to-level-1-manager-on-new-employee-addition)
  - [Hosting the Application](#hosting-the-application)
  - [Contributing](#contributing)
  - [License](#license)

## Technologies Used

List all the technologies used in the project, such as Spring Boot, MongoDB, Maven, etc.

## Getting Started

Explain how to set up and run the project locally. Include instructions for installing dependencies, configuring the database, and starting the application.

### Prerequisites

List all the prerequisites required for setting up the project, such as:

- JDK 11 or higher
- MongoDB server installed and running
- IDE (e.g., IntelliJ, Eclipse) or any text editor for code editing

### Installation

Provide step-by-step instructions on how to install and run the project. For example:

1. Clone the repository: `git clone https://github.com/your_username/employee-management-app.git`
2. Open the project in your IDE.
3. Set up the MongoDB connection in the `application.properties` file.
4. Build the project using Maven: `mvn clean install`
5. Run the Spring Boot application: `mvn spring-boot:run`

## API Documentation

Explain each API endpoint, its purpose, input data format (JSON), and response data format (JSON). Use examples to illustrate the request and response structures.

### Add Employee

- Method: POST
- Endpoint: `/employees`
- Request Body:
  ```json
  {
    "employeeName": "John Doe",
    "phoneNumber": "1234567890",
    "email": "johndoe@example.com",
    "reportsTo": "83d99a25-9d0e-4b23-8b1c-77c234d24f20",
    "profileImage": "https://example.com/profiles/johndoe.jpg"
  }
  ```
- Response:
  ```json
  {
    "id": "a8ad0a63-0dbf-49cc-934d-5f6887b2a875"
  }
  ```

### Get All Employees

- Method: GET
- Endpoint: `/employees`
- Response:
  ```json
  [
    {
      "id": "a8ad0a63-0dbf-49cc-934d-5f6887b2a875",
      "employeeName": "John Doe",
      "phoneNumber": "1234567890",
      "email": "johndoe@example.com",
      "reportsTo": "83d99a25-9d0e-4b23-8b1c-77c234d24f20",
      "profileImage": "https://example.com/profiles/johndoe.jpg"
    },
    {
      // Other employee objects...
    }
  ]
  ```

### Delete Employee

- Method: DELETE
- Endpoint: `/employees/{id}`

### Update Employee

- Method: PUT
- Endpoint: `/employees/{id}`
- Request Body:
  ```json
  {
    "employeeName": "John Doe",
    "phoneNumber": "9876543210",
    "email": "johndoe@example.com",
    "reportsTo": "8b1c-77c234d24f20",
    "profileImage": "https://example.com/profiles/johndoe.jpg"
  }
  ```

### Get nth Level Manager

- Method: GET
- Endpoint: `/employees/{id}/manager`
- Query Parameter: `n` (default: 1)
- Response:
  ```json
  {
    "id": "8b1c-77c234d24f20",
    "employeeName": "Manager Name",
    "phoneNumber": "9876543210",
    "email": "manager@example.com",
    "reportsTo": "f20",
    "profileImage": "https://example.com/profiles/manager.jpg"
  }
  ```
![image](https://github.com/cs-mshr/global/assets/95642555/b60c41ba-a590-414e-8455-37c1b5c78c23)


### Get Employees with Pagination and Sorting

- Method: GET
- Endpoint: `/employees/all`
- Query Parameters: `page` (default: 0), `size` (default: 10), `sort` (default: "employeeName")

  ![image](https://github.com/cs-mshr/global/assets/95642555/964ddecd-0804-4d78-91e2-17ee8bad13a5)


## Advanced Level

### Send Email to Level 1 Manager on New Employee Addition
  ![image](https://github.com/cs-mshr/global/assets/95642555/3fb501a1-7956-4044-9180-d0eee1724e71)


Explain the advanced functionality to send an email to the Level 1 manager when a new employee is added.





# Employee Management Application - Configuration and Setup

## Introduction

This document provides step-by-step instructions to configure and set up the Employee Management Application on your local development environment. The application is built using Spring Boot and MongoDB for data storage. Please follow the instructions carefully to ensure a smooth setup process.

## Prerequisites

Before proceeding with the setup, ensure that you have the following prerequisites installed on your system:

1. Java Development Kit (JDK) 11 or higher
2. Apache Maven
3. MongoDB

## Installation

Follow the steps below to install and set up the Employee Management Application:

### Step 1: Clone the Repository

Open your terminal or command prompt and clone the repository using the following command:

```
git clone https://github.com/your_username/employee-management-app.git
```

### Step 2: Set up MongoDB

Make sure you have MongoDB installed and running on your system. If not, download and install MongoDB from the official website: [https://www.mongodb.com/try/download/community](https://www.mongodb.com/try/download/community)

Once MongoDB is installed, start the MongoDB service using the appropriate command for your operating system.

### Step 3: Configure MongoDB Connection

Navigate to the `src/main/resources` directory of the cloned project and open the `application.properties` file.

Replace the following properties with your MongoDB configuration:

```properties
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=employee_db
```

### Step 4: Build the Application

Open your terminal or command prompt and navigate to the project's root directory.

Run the following Maven command to build the application:

```
mvn clean install
```

### Step 5: Run the Application

After a successful build, run the application using the following command:

```
mvn spring-boot:run
```

The application will start and will be accessible at [http://localhost:8080](http://localhost:8080)

Congratulations! You have successfully set up the Employee Management Application on your local development environment.

## API Documentation

For detailed API documentation, please refer to the [API Documentation](#api-documentation) section in the main documentation.

## Advanced Level

The application offers an advanced feature to send emails to Level 1 managers when a new employee is added. To enable this feature, you need to configure the email account used for sending notifications. Please follow these additional steps:

### Step 1: Set Up a New Email Account

Create a new Gmail account specifically for sending employee addition notifications.

### Step 2: Configure Email Credentials

Navigate to the `src/main/resources` directory of the cloned project and open the `application.properties` file.

Replace the following properties with the credentials of the newly created email account:

```properties
email.username=your_email@gmail.com
email.password=your_email_password
```

### Step 3: Enable Less Secure Apps

Go to the Google Account Security settings and enable "Allow less secure apps" for the newly created Gmail account. This is required to allow the application to send emails using the Gmail account.

## Hosting the Application

For instructions on hosting the application on a free platform (e.g., Heroku, AWS Free Tier), please refer to the [Hosting the Application](#hosting-the-application) section in the main documentation.

## Contributing

If you wish to contribute to the project, please follow the guidelines mentioned in the [Contributing](#contributing) section in the main documentation.

## License

The Employee Management Application is distributed under the [MIT License](LICENSE).

---

By following these instructions, you can successfully configure and set up the Employee Management Application on your local machine. If you encounter any issues during the setup process, please refer to the Troubleshooting section or feel free to reach out to the project maintainers for assistance. Happy coding!

