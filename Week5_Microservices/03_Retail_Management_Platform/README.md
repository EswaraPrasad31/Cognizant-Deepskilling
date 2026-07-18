# Enterprise Retail Management Platform

A highly integrated, production-grade microservices system built using Spring Boot 3.3.4, Spring Cloud 2023.0.3, and Java 21.

## Modules:
1. **Discovery Server (Eureka)** (Port `8761`)
2. **Config Server** (Port `8888`)
3. **API Gateway** (Port `8080`)
4. **Authentication Service** (Port `8000`)
5. **User Service** (Port `8081`)
6. **Product Service** (Port `8082`)
7. **Order Service** (Port `8083`)
8. **Inventory Service** (Port `8084`)
9. **Billing Service** (Port `8085`)
10. **Payment Service** (Port `8086`)

## Setup Instructions
1. Run `init.sql` to initialize MySQL databases.
2. Start services in order:
   - Config Server
   - Discovery Server
   - API Gateway
   - All other services
