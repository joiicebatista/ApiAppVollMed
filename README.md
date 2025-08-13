# VollMed API

Documentation and quickstart guide to run, test, and evolve the API.

## Overview

REST API for managing doctors and patients (create, list, update, and soft-delete), inspired by the "Voll Med" project. Returns JSON, follows HTTP conventions, and uses Bean Validation.

## Stack

* Java 21
* Spring Boot 3 (Web, Validation, Data JPA)
* MySQL 8
* Flyway (migrations)
* Lombok
* Spring Boot DevTools (dev)
* Springdoc OpenAPI (Swagger UI)

## Requirements

* JDK 21
* Maven 3.9+
* MySQL 8 running locally

## Configuration

Create a local database and user (or use root):

```sql
CREATE DATABASE vollmed_api;
```

`src/main/resources/application.properties` (example):

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api?useSSL=false&serverTimezone=UTC
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true

spring.flyway.enabled=true

server.port=8080
```

> Tip: validate credentials in the terminal with `mysql -u <user> -p` before starting the app.

## Database

Minimum modeling:

* **medicos**: id, nome, email, telefone, crm (unique), especialidade, endereco
* **pacientes**: id, nome, email, telefone, cpf (unique), endereco

Address mapped as `@Embeddable` with: logradouro, bairro, cep, cidade, uf, complemento, numero.

## Migrations (Flyway)

Files under `src/main/resources/db/migration`:

* follow the standard to create

  ``` V<number>__<descricption>.sql ```

## Endpoints

Base URL: `http://localhost:8080`

**Doctors**

* `GET /medicos` – paginated list
* `GET /medicos/{id}` – details
* `POST /medicos` – create (201 + Location header)
* `PUT /medicos/{id}` – full update
* `DELETE /medicos/{id}` – delete (204)

**Patients** (if applicable)

* `GET /pacientes`
* `GET /pacientes/{id}`
* `POST /pacientes`
* `PUT /pacientes/{id}`
* `DELETE /pacientes/{id}`

## Validation & errors

Bean Validation on DTOs:

* `@NotBlank` for required fields
* `@Pattern("\\d{8}")` for CEP
* `@Pattern("\\d{4,6}")` for CRM

Common errors & responses:

* **400** invalid JSON or validation failed
* **404** resource not found
* **409** conflict (e.g., `crm` already exists)
* **500** unexpected internal error

Global handler example: `@RestControllerAdvice` to standardize messages.

## Contributing

* Open an issue describing the change
* Create a `feature/description` branch
* Submit a PR with description, evidence, and test checklist


Define the project license (e.g., MIT).
