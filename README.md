# API de Gerenciamento de Livros Didáticos

## Descrição

Este projeto é uma API desenvolvida em Java com Spring Boot para o controle, cadastro e disponibilização de livros didáticos armazenados em um servidor local.
A aplicação permite o upload, listagem, filtragem e download de arquivos, além de fornecer mecanismos de autenticação e autorização baseados em JWT.

## Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Spring Security
* JWT (jjwt 0.13.x)
* Thymeleaf
* Hibernate
* PostgreSQL
* BCrypt
* Swagger / OpenAPI

---

## Configurações

Renomear `application.properties.example` para `application.properties`

```
    mv application.properties.example application.properties
```

Adicionar variaveis de ambiente

Exemplo de `application.properties`:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/filehandler
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update

secret-jwt=chave-secreta-bem-grande-com-no-minimo-32-bytes

default-admin-email=admin@email.com
default-admin-password=admin123
```

---

## Documentação da API

A documentação Swagger está disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

