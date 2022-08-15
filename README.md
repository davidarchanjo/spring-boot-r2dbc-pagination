![banner](./assets/banner.jpg)
# PREFACE
This Spring Data R2DBC demo project shows how to implement data paging by leveraging the usefullness of reactive and non-blocking connection.

<br>

# REQUIREMENT
- Java 15+
- Docker (optional)

<br>

# HOW TO TEST
## Boot up the application
```bash
$ ./mvnw spring-boot:run
```
From the above execution, we are using as the source database the [H2 database](https://www.h2database.com/html/main.html), which is a memory database engine. If you want to test the application with a physical database like PostgreSQL you simply need to go to the [pom.xml](./pom.xml) and replace the r2dbc's H2 dependency by:
```xml
<dependency>
    <groupId>io.r2dbc</groupId>
    <artifactId>r2dbc-postgresql</artifactId>
    <version>0.8.12.RELEASE</version>
    <scope>runtime</scope>
</dependency>
```
And then update [application-postgres.properties](./src/main/resources/application-postgres.properties) with the connection details for the PostgreSQL instance (`url`, `username`, `password`). For example, you can use the following docker command to spin up a PostgreSQL instance locally:
```bash
docker run --rm -p 5432:5432 -e POSTGRES_PASSWORD=postgres postgres:14-alpine -d postgres
```
With the PostgreSQL instance up and running, you can update the [application-postgres.properties](./src/main/resources/application-postgres.properties) as follows:
```properties
spring.r2dbc.url=r2dbc:postgresql://localhost:5432/postgres
spring.r2dbc.schema=public
spring.r2dbc.username=postgres
spring.r2dbc.password=postgres
```

After all those steps, you can start the application as follows:
```bash
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=postgres
```

<br>

## Request data with pagination
```bash
$ curl -X GET http://localhost:8080/product?page=0&size=5
```

<br>

## FOOTNOTES
- The Spring Data R2DBC implementation provides support to work with native query combined with pagination.
- The Spring Data R2DBC implementation does not map classes to database tables in the way is done on Spring Data JDBC, which uses Hibernate under the hood. Although the concept of _entity_ is applied, the "mapping engine" does not work declaratively in the way we are used to do with Hibernate/JPA, by making use of the @Entity annotation.