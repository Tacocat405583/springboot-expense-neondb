# Expense Tracker - Spring Boot + Kotlin + NeonDB

This is a simple cloud-connected expense tracking application built with **Spring Boot**, **Kotlin**, and **PostgreSQL** (hosted on **NeonDB**). It supports switching between a **JSON file-based** data source and a **PostgreSQL database** with Spring profiles.

---

## Features

- RESTful API for managing expenses
- Cloud PostgreSQL (NeonDB) integration
- H2 console support for local development
- Spring profile support to switch between JSON and DB
- Built with Kotlin and Gradle

---

## Tech Stack

- **Spring Boot 3**
- **Kotlin**
- **Spring Data JPA**
- **NeonDB (PostgreSQL)**
- **H2 (in-memory DB for development)**
- **Gradle**
- **Jackson** (for JSON support)

---

## Trying it out 

### 1. Clone the repo

```bash
git clone https://github.com/your-username/expense-tracker-neondb.git
cd expense-tracker-neondb

````

## 2. Setup configuration

Copy the example configuration and fill in your own values:


| Variable           | Description                       |
| ------------------ | --------------------------------- |
| `NEON_DB_URL`      | JDBC URL for your NeonDB instance |
| `NEON_DB_USERNAME` | Your NeonDB username              |
| `NEON_DB_PASSWORD` | Your NeonDB password              |

3. Run the app
./gradlew bootRun

You can then access:

H2 Console (if enabled): http://localhost:8080/h2-console ~ then from here enter the name of your database

API endpoints: http://localhost:8080/api/expenses




thats about all
