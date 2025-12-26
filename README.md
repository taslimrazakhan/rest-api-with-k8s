# Spring Boot REST API for Kubernetes

A comprehensive Spring Boot REST API application designed for testing and deployment on Kubernetes clusters. This project includes CRUD operations for Users and Products, health checks, monitoring endpoints, and complete Kubernetes deployment manifests.

## 🚀 Features

- **RESTful API**: Complete CRUD operations for Users and Products
- **Health Checks**: Built-in health, readiness, and info endpoints
- **Database**: H2 in-memory database with JPA/Hibernate
- **Containerization**: Docker support with multi-stage builds
- **Kubernetes Ready**: Complete K8s manifests with auto-scaling
- **Monitoring**: Actuator endpoints for metrics and monitoring
- **Environment Profiles**: Development, Production, and Kubernetes profiles

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6+
- Docker (for containerization)
- Kubernetes cluster (for deployment)

## 🏗️ Project Structure

```
rest-api-with-k8s/
├── src/
│   └── main/
│       ├── java/com/example/restapik8s/
│       │   ├── RestApiK8sApplication.java
│       │   ├── controller/
│       │   │   ├── UserController.java
│       │   │   ├── ProductController.java
│       │   │   └── HealthController.java
│       │   ├── service/
│       │   │   ├── UserService.java
│       │   │   └── ProductService.java
│       │   ├── repository/
│       │   │   ├── UserRepository.java
│       │   │   └── ProductRepository.java
│       │   ├── model/
│       │   │   ├── User.java
│       │   │   └── Product.java
│       │   └── config/
│       │       └── DataInitializer.java
│       └── resources/
│           └── application.yml
├── k8s/
│   ├── configmap.yaml
│   ├── deployment.yaml
│   ├── service.yaml
│   ├── ingress.yaml
│   └── hpa.yaml
├── Dockerfile
├── .dockerignore
├── pom.xml
└── README.md
```

## 🛠️ Local Development

### Running Locally

1. **Clone and navigate to the project directory**:
   ```bash
   cd rest-api-with-k8s
   ```

2. **Run with Maven**:
   ```bash
   mvn spring-boot:run
   ```

3. **Or run with specific profile**:
   ```bash
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```

4. **Access the application**:
   - API Base URL: http://localhost:8080/api
   - H2 Console: http://localhost:8080/h2-console
   - Health Check: http://localhost:8080/api/health

### Building the Application

```bash
# Clean and package
mvn clean package

# Skip tests
mvn clean package -DskipTests

# Run tests only
mvn test
```

## 📡 API Endpoints

### Users API
- `GET /api/users` - Get all users
- `GET /api/users/{id}` - Get user by ID
- `GET /api/users/email/{email}` - Get user by email
- `GET /api/users/department/{dept}` - Get users by department
- `GET /api/users/active` - Get active users
- `GET /api/users/search?name={name}` - Search users by name
- `POST /api/users` - Create new user
- `PUT /api/users/{id}` - Update user
- `DELETE /api/users/{id}` - Delete user
- `PATCH /api/users/{id}/deactivate` - Deactivate user
- `GET /api/users/count` - Get user count

### Products API
- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `GET /api/products/category/{category}` - Get products by category
- `GET /api/products/available` - Get available products
- `GET /api/products/search?name={name}` - Search products by name
- `GET /api/products/price-range?minPrice={min}&maxPrice={max}` - Get products by price range
- `GET /api/products/under-price/{maxPrice}` - Get products under price
- `POST /api/products` - Create new product
- `PUT /api/products/{id}` - Update product
- `DELETE /api/products/{id}` - Delete product
- `PATCH /api/products/{id}/unavailable` - Mark product unavailable
- `GET /api/products/count` - Get product count
- `GET /api/products/count/available` - Get available product count

### Health & Monitoring
- `GET /api/health` - Health check endpoint
- `GET /api/info` - Application information
- `GET /api/ready` - Readiness probe endpoint
- `GET /actuator/health` - Spring Boot Actuator health
- `GET /actuator/info` - Spring Boot Actuator info
- `GET /actuator/metrics` - Application metrics

## 🐳 Docker

### Building Docker Image

```bash
# Build the image
docker build -t rest-api-k8s:latest .

# Run the container
docker run -p 8080:8080 rest-api-k8s:latest

# Run with environment variables
docker run -p 8080:8080 -e SPRING_PROFILES_ACTIVE=prod rest-api-k8s:latest
```

### Docker Compose (Optional)

Create a `docker-compose.yml`:

```yaml
version: '3.8'
services:
  rest-api:
    build: .
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - SERVER_PORT=8080
```

## ☸️ Kubernetes Deployment

### Prerequisites
- Kubernetes cluster running
- kubectl configured
- Docker image built and available

### Deploy to Kubernetes

1. **Apply all manifests**:
   ```bash
   kubectl apply -f k8s/
   ```

2. **Or apply individually**:
   ```bash
   kubectl apply -f k8s/configmap.yaml
   kubectl apply -f k8s/deployment.yaml
   kubectl apply -f k8s/service.yaml
   kubectl apply -f k8s/hpa.yaml
   ```

3. **Check deployment status**:
   ```bash
   kubectl get pods -l app=rest-api-k8s
   kubectl get services
   kubectl get deployments
   ```

4. **Access the application**:
   ```bash
   # Port forward to local machine
   kubectl port-forward service/rest-api-service 8080:80
   
   # Or use NodePort (if available)
   kubectl get service rest-api-nodeport
   ```

### Scaling

```bash
# Manual scaling
kubectl scale deployment rest-api-deployment --replicas=5

# Check HPA status
kubectl get hpa rest-api-hpa
kubectl describe hpa rest-api-hpa
```

### Ingress Setup (Optional)

If you have an Ingress controller:

```bash
kubectl apply -f k8s/ingress.yaml

# Add to /etc/hosts (for local testing)
echo "127.0.0.1 rest-api.local" | sudo tee -a /etc/hosts
```

## 🧪 Testing the API

### Sample User Creation
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test User",
    "email": "test@example.com",
    "department": "Engineering"
  }'
```

### Sample Product Creation
```bash
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Test Product",
    "description": "A test product",
    "price": 99.99,
    "category": "Electronics"
  }'
```

### Health Check
```bash
curl http://localhost:8080/api/health
```

## 🔧 Configuration

### Environment Variables

| Variable | Default | Description |
|----------|---------|-------------|
| SPRING_PROFILES_ACTIVE | dev | Active Spring profile |
| SERVER_PORT | 8080 | Application port |
| DATABASE_URL | jdbc:h2:mem:testdb | Database URL |
| LOG_LEVEL | INFO | Logging level |
| JAVA_OPTS | -Xmx512m -Xms256m | JVM options |

### Profiles

- **dev**: Development with H2 console enabled, debug logging
- **prod**: Production with minimal logging, security hardened
- **k8s**: Kubernetes optimized configuration

## 📊 Monitoring

The application includes several monitoring endpoints:

- **Health Checks**: Kubernetes liveness and readiness probes
- **Metrics**: Available via Spring Boot Actuator
- **Logging**: Configurable log levels per environment
- **Resource Limits**: CPU and memory limits defined in K8s manifests

## 🚨 Troubleshooting

### Common Issues

1. **Port already in use**:
   ```bash
   # Change port
   mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=8081"
   ```

2. **Docker build fails**:
   ```bash
   # Clean Maven cache
   mvn clean
   # Rebuild
   docker build --no-cache -t rest-api-k8s:latest .
   ```

3. **Kubernetes pods not starting**:
   ```bash
   kubectl describe pod <pod-name>
   kubectl logs <pod-name>
   ```

### Logs
```bash
# Application logs in Kubernetes
kubectl logs -f deployment/rest-api-deployment

# Specific pod logs
kubectl logs -f <pod-name>
```

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 📞 Support

For support and questions:
- Create an issue in the GitHub repository
- Check the troubleshooting section above
- Review the application logs for detailed error information