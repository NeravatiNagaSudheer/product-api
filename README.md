# 🛒 Product API

A secure, production-ready RESTful backend API built with **Spring Boot** and **Spring Security**, featuring JWT-based authentication and role-based access control.

---

## 🚀 Features

- 🔐 JWT Authentication & Stateless Authorization
- 👥 Role-Based Access Control (USER, ADMIN)
- 📦 Full Product CRUD Operations (single & bulk)
- ✅ Input Validation with Jakarta Bean Validation
- 🗄️ PostgreSQL Database with JPA/Hibernate ORM
- 🔒 BCrypt Password Encoding (strength 12)
- 🧱 DTO-based API design (no entity exposure)
- ⚡ Stateless Session Management

---

## 🛠️ Tech Stack

| Layer | Technology |
|-------|-----------|
| Language | Java 17 |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| ORM | Spring Data JPA + Hibernate |
| Database | PostgreSQL |
| Build Tool | Maven |
| Testing | JUnit |

---

## 📁 Project Structure

```
src/main/java/com/example/productapi/
├── config/
│   ├── SecurityConfig.java       # Spring Security & JWT filter chain
│   └── JwtFilter.java            # JWT request filter
├── controller/
│   ├── AuthController.java       # Register & Login endpoints
│   └── ProductController.java    # Product CRUD endpoints
├── service/
│   └── ProductService.java       # Business logic
├── repository/
│   └── ProductRepository.java    # JPA repository
├── entity/
│   └── Product.java              # Product entity
├── dto/
│   ├── RequestDto.java           # Incoming request model
│   └── ResponseDto.java          # Outgoing response model
└── ProductapiApplication.java    # Main entry point
```

---

## 🔑 API Endpoints

### Auth Endpoints (Public)

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Login and receive JWT token |

### Product Endpoints (Protected)

| Method | Endpoint | Role Required | Description |
|--------|----------|--------------|-------------|
| GET | `/api/products/{id}` | USER, ADMIN | Get product by ID |
| GET | `/api/products/allProducts` | USER, ADMIN | Get all products |
| POST | `/api/products/singleProduct` | ADMIN | Create a single product |
| POST | `/api/products/bulk` | ADMIN | Create multiple products |
| PUT | `/api/products/{id}` | ADMIN | Update a product |
| DELETE | `/api/products/{id}` | ADMIN | Delete a product |

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- PostgreSQL 14+

### 1. Clone the Repository

```bash
git clone https://github.com/NeravatiNagaSudheer/product-api.git
cd product-api
```

### 2. Configure the Database

Open `src/main/resources/application.properties` and update:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/productdb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Configure JWT Secret

```properties
jwt.secret=your_secret_key_here
jwt.expiration=86400000
```

### 4. Build & Run

```bash
mvn clean install
mvn spring-boot:run
```

The API will start at `http://localhost:8080`

---

## 🔐 Authentication Flow

```
1. Register   →  POST /auth/register  →  { name, email, password, role }
2. Login      →  POST /auth/login     →  Returns JWT token
3. Use token  →  Add header: Authorization: Bearer <token>
4. Access     →  Protected endpoints based on role
```

### Example: Register

```json
POST /auth/register
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "secret123",
  "role": "USER"
}
```

### Example: Login

```json
POST /auth/login
{
  "email": "john@example.com",
  "password": "secret123"
}

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Example: Create Product (ADMIN only)

```json
POST /api/products/singleProduct
Authorization: Bearer <token>

{
  "name": "Laptop",
  "description": "High performance laptop",
  "price": 75000.00,
  "quantity": 10
}
```

---

## 🛡️ Security Design

- All passwords hashed with **BCrypt** (strength 12)
- JWT tokens validated on every request via a custom **JwtFilter**
- **Stateless** sessions — no server-side session storage
- CSRF protection disabled (safe for stateless JWT APIs)
- Endpoints protected by **HTTP method + role** combinations

---

## 🧪 Running Tests

```bash
mvn test
```

---

## 📬 Testing with Postman

1. Import the collection (coming soon)
2. Register a user via `/auth/register`
3. Login via `/auth/login` and copy the JWT token
4. Add the token as `Authorization: Bearer <token>` header
5. Access product endpoints based on your role

---

## 🔮 Future Improvements

- [ ] Add Swagger/OpenAPI documentation
- [ ] Implement refresh token mechanism
- [ ] Add Docker support
- [ ] Deploy to AWS EC2
- [ ] Add pagination for product listing
- [ ] Write comprehensive unit & integration tests

---

## 👨‍💻 Author

**Nearvati Naga Sudheer**  
Oracle Utilities C2M Developer | Java Backend Developer  
📧 sudheernaganearvati@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/naga-sudheer-nearvati-a8b66b196/)  
🐙 [GitHub](https://github.com/NeravatiNagaSudheer)
