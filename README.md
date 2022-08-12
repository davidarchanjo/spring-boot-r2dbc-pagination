# spring-boot-r2dbc-pagination
Spring Data R2DBC pagination demo project

# REQUIREMENT
- Java 11+
- Maven (optional)

# HOW TO TEST
## Boot up the application
```bash
$ ./mvnw spring-boot:run
```

## Request data with pagination
```bash
$ curl -X GET http://localhost:8080/product?page=0&size=5
```
