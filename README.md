# JavaSpring 🚀

A RESTful API developed with Java and Spring Boot, focused on user management, authentication, and backend development.

## 🛠️ Technologies

- **Java 17**
- **Spring Boot 4**
- **Spring Data JPA**
- **Spring Security**
- **JWT Authentication**
- **BCrypt Password Encryption**
- **H2 Database**
- **Lombok**
- **Maven**

## 📌 Features

- User registration
- User data management
- Password hashing with BCrypt
- JWT-based authentication
- Custom authentication filter
- Data persistence using Spring Data JPA
- DTOs for handling API responses
- H2 in-memory database integration

## 🏗️ Project Structure

The project follows a layered organization to separate responsibilities:

- **Controller:** Handles HTTP requests and API responses.
- **Model:** Represents application data.
- **Repository:** Handles database operations.
- **DTO:** Transfers data between application layers.
- **Filter:** Handles JWT authentication.
- **Configuration:** Contains application configurations.

## ⚙️ How to Run

### Prerequisites

- Java 17 or higher
- IntelliJ IDEA (recommended)

### Steps

1. Clone the repository:

```bash
git clone https://github.com/higorvinicius10/JavaSpring.git
```

2. Navigate to the project directory:

```bash
cd JavaSpring
```

3. Run the application using Maven Wrapper:

**Windows:**

```bash
.\mvnw.cmd spring-boot:run
```

**Linux/macOS:**

```bash
./mvnw spring-boot:run
```

4. The application will be available at:

```text
http://localhost:8080
```

## 🗄️ Database

The project uses H2 Database in memory for development and testing.

H2 Console:

```text
http://localhost:8080/h2-console
```

## 🎯 Learning Goals

This project is part of my journey as a Backend Developer, focusing on:

- Object-Oriented Programming (OOP)
- REST API development
- Authentication and authorization
- Database integration
- Spring Boot ecosystem
- Clean code and software architecture

## 👨‍💻 Author

**Higor Vinicius**

Backend Developer | Java | Spring Boot

GitHub: [@higorvinicius10](https://github.com/higorvinicius10)

---

⭐ This project is continuously evolving as I improve my Java and backend development skills.
