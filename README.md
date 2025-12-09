# 🚗 Rental Car

A Spring Boot application for managing car rentals.

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen?logo=spring)
![Maven](https://img.shields.io/badge/Maven-3.6+-blue?logo=apache-maven)
![License](https://img.shields.io/badge/License-Demo-lightgrey)

## 📝 Description

Demo project for learning test with Spring Boot.

## 📋 Prerequisites

- ☕ Java 17
- 📦 Maven 3.6+

## 🛠️ Technology Stack

- 🌱 Spring Boot 3.5.7
- ☕ Java 17
- 📦 Maven
- 🧪 JUnit 5
- 📊 JaCoCo (Code Coverage)
- 🗄️ H2 Database
- 🔧 Lombok

## 🚀 Getting Started

### 🔨 Build the Project

```bash
mvn clean install
```

### ▶️ Run the Application

```bash
mvn spring-boot:run
```

Or run the JAR file:

```bash
java -jar target/rentalCar-0.0.1-SNAPSHOT.jar
```

### 🧪 Run Tests

```bash
mvn test
```

### 📊 Generate Test Coverage Report (JaCoCo)

```bash
mvn clean test jacoco:report
```

The coverage report will be generated at:
```
target/site/jacoco/index.html
```

Open the HTML file in your browser to view the detailed coverage report.

## 📊 JaCoCo Test Coverage Configuration

This project uses **JaCoCo Maven Plugin** (version 0.8.12) for code coverage analysis.

### Configuration Details

- **Plugin Version**: 0.8.12
- **Exclusions**: 
  - `**/model/**` - Model classes excluded from coverage
  - `**/entity/**` - Entity classes excluded from coverage

### Maven Goals

- **prepare-agent**: Prepares the JaCoCo agent for test execution (runs automatically during test phase)
- **report**: Generates the HTML coverage report (runs during verify phase)

### Commands

```bash
# Run tests with coverage
mvn clean test

# Generate coverage report
mvn jacoco:report

# Run tests and generate report in one command
mvn clean test jacoco:report

# Full build with coverage report
mvn clean verify
```

### Viewing Coverage Reports

After running the coverage report, open:
```
target/site/jacoco/index.html
```

The report includes:
- Overall coverage percentage
- Line coverage
- Branch coverage
- Method coverage
- Class coverage
- Detailed coverage by package and class

## 📁 Project Structure

```
src/
├── main/
│   ├── java/br/com/systemit/rentalCar/
│   │   └── RentalCarApplication.java
│   └── resources/
│       └── application.properties
└── test/
    └── java/br/com/systemit/rentalCar/
        └── RentalCarApplicationTests.java
```

## 📄 License

This project is a demo/learning project.
