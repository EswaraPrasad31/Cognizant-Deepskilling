# Microservices and SSO Security Portfolio

This workspace contains three distinct, production-ready microservices and security projects built using **Spring Boot 3**, **Spring Cloud (2023.0.3)**, **Java 21/26**, and **Spring Security 6**.

## Workspace Structure

The workspace has been organized into three separate directories, each containing a complete, importable Maven project:

```
Week5_Microservices/
├── 01_Centralized_SSO_Auth_Exercises/      # Standalone exercises on OIDC, Resource Server, and JWT Security
├── 02_API_Gateway_Edge_Services_Platform/  # 6-Module Secure Gateway & Edge Services Platform with Resilience4j
└── 03_Retail_Management_Platform/          # 10-Module Enterprise Retail Management Platform with Config Server
```

---

## Project Details

### 🔑 01. Centralized SSO Auth Exercises
A collection of three core exercises demonstrating foundational concepts of single sign-on (SSO), centralized authentication, and token security:
*   **`exercise-1-oauth2-client`**: Centralized authentication using OAuth 2.1/OIDC (Google/GitHub integration) in a Client Role.
*   **`exercise-2-resource-server`**: Resource server configuration to validate JWT tokens issued by external authorization servers.
*   **`exercise-3-jwt-security`**: Custom JSON Web Token (JWT) generation, validation, and filter interceptors using modern `io.jsonwebtoken` (JJWT) API.

### 🛡️ 02. API Gateway and Edge Services Platform
A production-quality, 6-module microservices platform with advanced edge service patterns:
*   **Services**:
    *   `discovery-server` (Eureka Service Registry)
    *   `api-gateway` (Spring Cloud Gateway, Routing, Global Logging Filter, Resilience4j Circuit Breaker/Time Limiter, and Custom Random Load Balancer configuration)
    *   `auth-server` (Authorization server using JPA, custom user details, and JWT token issuance)
    *   `user-service`, `product-service`, `order-service` (Spring Boot JPA resource microservices)
*   **Key Features**: Resilience4j fallbacks, random client-side load balancing, JWT authentication, and Swagger API documentation.

### 🛒 03. Retail Management Platform
A comprehensive, enterprise-level 10-module microservices ecosystem designed for high scale:
*   **Services**:
    *   `config-server` (Centralized configuration management)
    *   `discovery-server` (Eureka Service Registry)
    *   `api-gateway` (Spring Cloud Gateway router)
    *   `auth-service` (Centralized user authentication & JWT tokens)
    *   `user-service`, `product-service`, `inventory-service`, `order-service`, `billing-service`, `payment-service` (Functional business domain services)
*   **Key Features**: Centralized external configuration profiles, full JWT-based request interception, Mockito-tested services, Resilience4j circuit breakers, and Docker-compose orchestration ready.

---

## How to Import & Run in IntelliJ IDEA

1.  **Open IntelliJ IDEA** (Ultimate or Community Edition).
2.  Select **Open** or **Import Project**.
3.  Navigate to the specific folder you wish to work on (e.g., `03_Retail_Management_Platform`) and select its `pom.xml`.
4.  IntelliJ will automatically import all modules as a multi-module Maven project.
5.  Ensure your **Project SDK** is configured to **JDK 21** or newer (we recommend Java 21/Java 26).
6.  Build the project:
    ```bash
    mvn clean install
    ```
7.  Run the services in the recommended startup order (Eureka Registry -> Config Server -> Gateway -> Services).
