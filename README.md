# Banking System

The Banking System project aims to simulate a real-world banking environment, allowing users to manage their accounts securely and efficiently. Users can register for an account, log in to access their banking features, and perform transactions like depositing and withdrawing money or transferring funds to other accounts. The application ensures data integrity and security by using JDBC to connect to a MySQL database, where all user data and transaction history are stored. This project is designed for educational purposes, helping students and developers understand basic banking operations, database interactions, and Java programming concepts.

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Database Setup](#database-setup)
- [How to Run the Project](#how-to-run-the-project)

## Overview
This Banking System is a Java application that provides basic banking functionalities, including user registration, login, balance inquiry, fund crediting, debiting, and transferring funds. The application interacts with a MySQL database to store user account information and transaction records.

## Features
- **User Registration**: Allows users to create an account with full name, email, password, and security PIN.
- **User Login**: Users can log in using their email and password.
- **Account Management**:
  - View account balance
  - Credit funds to the account
  - Debit funds from the account
  - Transfer funds to another account
- **Data Persistence**: All user data and transactions are stored in a MySQL database.

## Technologies Used
- Java
- MySQL
- JDBC (Java Database Connectivity)
- Maven (optional, if you want to manage dependencies)

## Database Setup
To set up the database for this application, follow these steps:

1. Create a MySQL database named `banking`.
2. Execute the following SQL command to create the `user` and `accounts` table:

   ```sql
   CREATE TABLE accounts (
    account_number BIGINT NOT NULL PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    balance DECIMAL(30,2) NOT NULL,
    security_pin CHAR(4) NOT NULL
   );

   CREATE TABLE user (
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL
   );


## How to Run the Project
1. Clone this repository to your local machine:
   ```bash
   git clone https://github.com/yourusername/banking-system.git
   cd banking-system
2. Ensure you have Java and MySQL installed on your machine.
3. Compile and run the Java application:
   ```bash
   javac -cp . BankingApp.java User.java Account.java AccountManager.java
   java -cp . BankingApp
