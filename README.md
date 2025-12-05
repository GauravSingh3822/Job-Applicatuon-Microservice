# 🧩 Job Application Management System – Microservices Architecture

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen)
![Microservices](https://img.shields.io/badge/Architecture-Microservices-blue)
![Java](https://img.shields.io/badge/Java-17-orange)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![Kubernetes](https://img.shields.io/badge/Kubernetes-Supported-326CE5)
![License](https://img.shields.io/badge/License-MIT-yellow)

A production-grade **Job Application Platform** built using **Spring Boot Microservices**, **Spring Cloud**, **Kafka**, **RabbitMQ**, **Docker**, and **Kubernetes**.  
This system handles **Jobs**, **Employers**, and **Applicants**, following modern distributed architecture principles.

---

## 🚀 Features

✅ **Fully Modular Microservices Architecture** - Independently deployable services  
✅ **Eureka Service Discovery** - Dynamic service registration and load balancing  
✅ **Spring Cloud Gateway** - Intelligent API routing with filters  
✅ **Centralized Config Server** - Configuration management with Git backend  
✅ **Event-Driven Architecture** - Kafka & RabbitMQ for asynchronous communication  
✅ **Resilience4J** - Circuit Breaker, Retry, Rate Limiter & Fallback patterns  
✅ **Distributed Tracing** - Zipkin integration for request tracking  
✅ **Containerized Deployment** - Docker & Docker Compose support  
✅ **Kubernetes Ready** - Horizontal Pod Autoscaling (HPA) enabled  
✅ **PostgreSQL Database** - Reliable relational data storage per service  

---

## 🏗️ Architecture Overview

```
                  ┌──────────────────┐
                  │   API Gateway    │
                  │  (Port: 8084)    │
                  └────────┬─────────┘
                           │
           ┌───────────────┼───────────────┐
           │               │               │
    ┌──────▼──────┐ ┌─────▼──────┐ ┌─────▼──────┐
    │ Job Service │ │  Employer  │ │ Applicant  │
    │ (Port: 8081)│ │  Service   │ │  Service   │
    │             │ │(Port: 8082)│ │(Port: 8083)│
    └──────┬──────┘ └─────┬──────┘ └─────┬──────┘
           │              │              │
    ┌──────▼──────────────▼──────────────▼──────┐
    │                                            │
    │    PostgreSQL • Kafka • RabbitMQ • Zipkin │
    │                                            │
    └────────────────────────────────────────────┘
           ▲
           │
    ┌──────┴──────┐
    │   Eureka    │
    │   Server    │
    │ (Port: 8761)│
    └─────────────┘
```

---

## 🛠️ Tech Stack

### **Backend Framework**
- **Java 17** - LTS version with modern language features
- **Spring Boot 3.2.0** - Microservices foundation
- **Spring Cloud** - Distributed system patterns
  - Eureka (Service Discovery)
  - Config Server (Centralized Configuration)
  - Gateway (API Gateway with routing)
  - OpenFeign (Declarative REST Client)
- **Spring Data JPA** - Database abstraction layer
- **Hibernate** - ORM implementation

### **Messaging & Streaming**
- **Apache Kafka** - Event streaming platform
- **RabbitMQ** - Message broker for async communication

### **Database**
- **PostgreSQL** - Production-grade relational database

### **Resilience & Monitoring**
- **Resilience4J** - Fault tolerance library
- **Zipkin** - Distributed tracing system
- **Spring Boot Actuator** - Health checks and metrics

### **DevOps & Deployment**
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **Kubernetes** - Container orchestration platform
- **Maven** - Build automation

---

## 📁 Project Structure

```
job-application-system/
│
├── api-gateway/                 # API Gateway (Port: 8084)
│   ├── src/
│   └── pom.xml
│
├── config-server/               # Centralized Config Server (Port: 8088)
│   ├── src/
│   └── pom.xml
│
├── service-registry/            # Eureka Discovery Server (Port: 8761)
│   ├── src/
│   └── pom.xml
│
├── job-service/                 # Job Management Service (Port: 8081)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   └── resources/
│   │   └── test/
│   └── pom.xml
│
├── employer-service/            # Employer Management Service (Port: 8082)
│   ├── src/
│   └── pom.xml
│
├── applicant-service/           # Applicant Management Service (Port: 8083)
│   ├── src/
│   └── pom.xml
│
├── docker-compose.yml           # Docker Compose configuration
├── k8s-manifests/               # Kubernetes deployment files
│   ├── deployment.yaml
│   ├── service.yaml
│   └── configmap.yaml
│
├── config-repo/                 # Git-based config repository
│   ├── job-service.yml
│   ├── employer-service.yml
│   └── applicant-service.yml
│
└── README.md
```

---

## ⚙️ Prerequisites

Before running this project, ensure you have the following installed:

- **Java 17** or higher
- **Maven 3.8+**
- **Docker** & **Docker Compose**
- **PostgreSQL** (if running locally without Docker)
- **Git**

---

## 🚀 Running Locally

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/GauravSingh3822/job-application-system.git
cd job-application-system
```

### 2️⃣ Start Infrastructure Services (PostgreSQL, Kafka, RabbitMQ, Zipkin)
```bash
docker-compose up -d
```

This will start:
- PostgreSQL (Port: 5432)
- Kafka (Port: 9092)
- RabbitMQ (Port: 5672, Management UI: 15672)
- Zipkin (Port: 9411)

### 3️⃣ Start Config Server
```bash
cd config-server
mvn clean install
mvn spring-boot:run
```
Config Server will be available at: `http://localhost:8088`

### 4️⃣ Start Eureka Discovery Server
```bash
cd service-registry
mvn clean install
mvn spring-boot:run
```
Eureka Dashboard: `http://localhost:8761`

### 5️⃣ Start API Gateway
```bash
cd api-gateway
mvn clean install
mvn spring-boot:run
```
API Gateway: `http://localhost:8084`

### 6️⃣ Start Each Microservice

**Job Service:**
```bash
cd job-service
mvn clean install
mvn spring-boot:run
```

**Employer Service:**
```bash
cd employer-service
mvn clean install
mvn spring-boot:run
```

**Applicant Service:**
```bash
cd applicant-service
mvn clean install
mvn spring-boot:run
```

---

## 🐳 Running with Docker

### Build Docker Images
```bash
docker build -t job-service:latest ./job-service
docker build -t employer-service:latest ./employer-service
docker build -t applicant-service:latest ./applicant-service
docker build -t api-gateway:latest ./api-gateway
docker build -t config-server:latest ./config-server
docker build -t service-registry:latest ./service-registry
```

### Run All Services with Docker Compose
```bash
docker-compose up --build
```

This command will:
- Build all Docker images
- Start all microservices
- Set up networking between containers
- Initialize databases

---

## ☸️ Deploying to Kubernetes

### 1️⃣ Apply Kubernetes Manifests
```bash
kubectl apply -f k8s-manifests/
```

### 2️⃣ Verify Deployments
```bash
kubectl get pods
kubectl get services
```

### 3️⃣ Access Services
```bash
# Port-forward API Gateway
kubectl port-forward service/api-gateway 8084:8084

# Port-forward Eureka Dashboard
kubectl port-forward service/service-registry 8761:8761
```

### 4️⃣ Enable Horizontal Pod Autoscaling
```bash
kubectl autoscale deployment job-service --cpu-percent=70 --min=2 --max=10
kubectl autoscale deployment employer-service --cpu-percent=70 --min=2 --max=10
kubectl autoscale deployment applicant-service --cpu-percent=70 --min=2 --max=10
```

---

## 📡 API Endpoints

### **Job Service** (via API Gateway)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/jobs` | Get all jobs |
| GET | `/api/jobs/{id}` | Get job by ID |
| POST | `/api/jobs` | Create new job |
| PUT | `/api/jobs/{id}` | Update job |
| DELETE | `/api/jobs/{id}` | Delete job |

### **Employer Service** (via API Gateway)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/employers` | Get all employers |
| GET | `/api/employers/{id}` | Get employer by ID |
| POST | `/api/employers` | Register employer |
| PUT | `/api/employers/{id}` | Update employer |
| DELETE | `/api/employers/{id}` | Delete employer |

### **Applicant Service** (via API Gateway)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/applicants` | Get all applicants |
| GET | `/api/applicants/{id}` | Get applicant by ID |
| POST | `/api/applicants` | Register applicant |
| PUT | `/api/applicants/{id}` | Update applicant |
| DELETE | `/api/applicants/{id}` | Delete applicant |

**Base URL:** `http://localhost:8084`

---

## 🔍 Monitoring & Observability

### **Eureka Dashboard**
Monitor all registered services:
```
http://localhost:8761
```

### **Zipkin Tracing**
View distributed traces:
```
http://localhost:9411
```

### **RabbitMQ Management**
Monitor message queues:
```
http://localhost:15672
Username: guest
Password: guest
```

### **Spring Boot Actuator**
Health checks and metrics for each service:
```
http://localhost:8081/actuator/health
http://localhost:8082/actuator/health
http://localhost:8083/actuator/health
```

---

## 🔐 Configuration Management

All service configurations are stored in the **Config Server** backed by a Git repository.

### Configuration Files Location
```
config-repo/
├── job-service.yml
├── employer-service.yml
├── applicant-service.yml
└── application.yml
```

### Refresh Configuration at Runtime
```bash
curl -X POST http://localhost:8081/actuator/refresh
```

---

## 🧪 Testing

### Run Unit Tests
```bash
mvn test
```

### Run Integration Tests
```bash
mvn verify
```

### Test with Postman
Import the Postman collection from `/postman` directory and test all API endpoints.

---

## 🛡️ Resilience Patterns

### **Circuit Breaker**
Prevents cascading failures when a service is down.

### **Retry Mechanism**
Automatically retries failed requests with exponential backoff.

### **Rate Limiter**
Controls the number of requests per second to prevent overload.

### **Fallback**
Provides default responses when services are unavailable.

---

## 📊 Database Schema

Each service has its own dedicated PostgreSQL database:

- **job-service-db** - Stores job postings
- **employer-service-db** - Stores employer information
- **applicant-service-db** - Stores applicant profiles

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 Author

**Gaurav Singh**  
📧 Email: gauravsingh233446@gmail.com  
🔗 LinkedIn: [linkedin.com/in/gaurav-singh-2b1665252](https://www.linkedin.com/in/gaurav-singh-2b1665252/)  
💻 GitHub: [github.com/GauravSingh3822](https://github.com/GauravSingh3822)

---

## 🌟 Acknowledgments

- Spring Boot Team for excellent documentation
- Spring Cloud for microservices patterns
- Apache Kafka & RabbitMQ communities
- Docker & Kubernetes projects

---

## 📝 Future Enhancements

- [ ] Add JWT Authentication & Authorization
- [ ] Implement GraphQL API
- [ ] Add Redis caching layer
- [ ] Implement saga pattern for distributed transactions
- [ ] Add comprehensive logging with ELK stack
- [ ] Implement API rate limiting with Redis
- [ ] Add Prometheus & Grafana for monitoring
- [ ] Implement Blue-Green deployment strategy

---

**⭐ If you found this project helpful, please give it a star!**
