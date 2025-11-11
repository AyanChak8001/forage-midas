🏦 Midas Core – Financial Transaction Processing System
📘 Overview

Midas Core is a backend application built as part of the J.P. Morgan Software Engineering Simulation (Forage).
The project simulates a financial transaction processing system where transactions are received, validated, recorded, and queried in real time.

It demonstrates key backend concepts such as Kafka integration, Spring Boot REST APIs, and database transaction management using H2.

🧩 Features

Receives and processes financial transactions using Kafka.

Validates and stores transactions in an H2 in-memory database.

Updates user balances after each valid transaction.

Provides a REST API to fetch user balances by user ID.

Ensures smooth data flow between components using Spring Boot and JPA.

⚙️ Tech Stack

Language: Java 17

Framework: Spring Boot

Message Queue: Apache Kafka

Database: H2 (in-memory)

Build Tool: Maven

Testing: JUnit 5

IDE Used: IntelliJ IDEA

🏗️ Architecture Overview

Kafka Listener: Receives transaction messages in JSON format.

Service Layer: Validates sender/recipient and updates balances.

Database Layer: Persists user and transaction records using JPA.

REST Controller: Exposes /balance?userId= endpoint to fetch current balances.

🚀 How to Run

Clone the repository

git clone https://github.com/AyanChak8001/forage-midas.git
cd forage-midas


Run the application

Open in IntelliJ IDEA or VS Code.

Ensure Java 17 and Maven are installed.

Start the Spring Boot application.

Test
Run the provided unit tests:

mvn test


You can use the built-in TaskOneTests–TaskFiveTests to validate each step.

📊 Project Tasks Summary

Kafka Integration – Implemented a listener to receive and deserialize transactions.

Transaction Validation & Persistence – Verified users, checked balances, and updated records in H2.

Balance API – Created a REST endpoint /balance to return user balance data.

🧠 Learning Outcomes

Hands-on experience integrating Kafka, Spring Boot, and H2 in a single system.

Improved understanding of real-time message processing and database transactions.

Learned structured backend development aligned with industry practices at J.P. Morgan.

📁 Repository Structure
forage-midas/
├── src/
│   ├── main/java/com/midascore/...
│   └── test/java/com/midascore/...
├── pom.xml
├── application.yml
└── README.md