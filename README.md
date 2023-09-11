# ![image](https://github.com/Sagarshivalingappaathani/LibrarySoftware/assets/121311033/dcc06a76-979b-485c-bc8e-db308851f6ae)Library Management System

This is a Java-based Library Management System application designed to help manage books and user records in a library. It allows administrators and users to perform various tasks such as viewing available books, searching for books, borrowing books, returning books, and more.

## Features

- **Admin Panel:** Administrators can log in to the system and perform administrative tasks.
- **User Panel:** Registered users can borrow and return books.

## Usage

- **Admin Login:**
  - To access the admin panel, log in using the admin username and password.
- **User Login:**
  - Registered library users can log in using their username and password.

## Functionality

- **Admin Panel:**
  - Add, edit, or delete books.
  - View a list of all books.
  - View a list of issued books and user information.
  - View a list of returned books and user information.
  - Calculate fines for overdue books.

- **User Panel:**
  - View available books.
  - Search for books by title.(added the real-time Search)
  - Borrow books.
  - Return books.

## Prerequisites

Before running the application, ensure that you have the following installed:

- Java Development Kit (JDK)
- MySQL Database Server
- MySQL Connector/J (JDBC Driver)

## Setup

1. **Database Setup:**

   - Create a MySQL database and note down the database name, username, and password.

2. **Database Configuration:**

   - Open the `LibrarySoftware.java` file.
   - Locate the following lines and update them with your database credentials:

     ```java
     private static final String DB_URL = "jdbc:mysql://localhost:3306/YOUR_DATABASE_NAME";
     private static final String DB_USERNAME = "YOUR_DATABASE_USERNAME";
     private static final String DB_PASSWORD = "YOUR_DATABASE_PASSWORD";
     public static String admin="ADMIN_NAME";
     public static String Pass="ADMIN_PASSWORD";
     ```

3. **Database Initialization:**

   - Run the application once. It will create the necessary database tables if they do not already exist.

4. **Run the Application:**

   - Compile and run the `LibrarySoftware.java` file to start the application.


## Contributing

Contributions to this project are welcome. Feel free to open issues or create pull requests.

