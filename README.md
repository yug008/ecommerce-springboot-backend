This repository contains the backend implementation of an e-commerce application built using Spring Boot.
It exposes REST APIs for managing products and demonstrates clean backend architecture using modern Spring practices.

⚠️ Image upload functionality is currently under development.

🚀 Features

• Product CRUD operations

• RESTful API design

• Spring Data JPA with Hibernate

• H2 in-memory database for development

• Exception handling

• API testing using Postman

🛠 Tech Stack

• Language: Java

• Framework: Spring Boot

• ORM: Spring Data JPA / Hibernate

• Database: H2 (in-memory)

• Build Tool: Maven

📁Project Structure:

src
├── main
│   ├── java
│   │   └── com.example.ecommerce
│   └── resources
│       └── application.properties
└── test

⚙️ Setup & Run:

• Clone the repository - git clone https://github.com/yug008/ecommerce-springboot-backend.git

• Run the application - mvn spring-boot:run

• Application runs on: http://localhost:8080

• H2 Console (if enabled): http://localhost:8080/h2-console

🔗 Related Repository:

• Frontend (React): https://github.com/yug008/ecommerce-react-frontend

🔧 Known Limitations:

• Image upload feature is a work in progress

• Frontend is maintained in a separate repository

📌 Future Improvements:

• Complete multipart image upload support

• Add authentication and authorization

• Switch to MySQL or PostgreSQL for production

• Add Swagger / OpenAPI documentation

👤 Author

Yug Mehta

Java & Spring Boot Developer


