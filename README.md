# Projeto de Gerenciamento de Notas

Este é um projeto de exemplo para gerenciamento de notas, desenvolvido em Java utilizando Spring Boot. A aplicação expõe uma API REST para operações CRUD (Create, Read, Update, Delete) em notas.

## Sumário

- [Pré-requisitos](#pré-requisitos)
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Execução](#execução)
- [Documentação da API](#documentação-da-api)

## Pré-requisitos

Antes de começar, você precisará ter o seguinte instalado em sua máquina:

- Java 17 ou superior
- Maven 3.6.0 ou superior
- MySQL Server

## Instalação

1. Clone o repositório para sua máquina local:
    ```sh
    git clone https://github.com/seu-usuario/seu-repositorio.git
    cd seu-repositorio
    ```

2. Instale as dependências do projeto:
    ```sh
    mvn clean install
    ```

## Configuração

1. Configure o banco de dados no arquivo `src/main/resources/application.properties`:
    ```properties
    spring.application.name=Notas

    spring.datasource.url=jdbc:mysql://localhost:3306/nome-da-sua-database
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.hibernate.ddl-auto=update

    # Configuração de logging
    logging.level.root=INFO
    logging.level.com.example.notas=DEBUG
    logging.file.name=logs/app.log
    ```

2. Execute as queries SQL abaixo para criar o banco de dados e as tabelas necessárias:

    ```sql
    CREATE DATABASE NOTA;

    USE NOTA;

    CREATE TABLE nota (
        id BIGINT NOT NULL AUTO_INCREMENT,
        titulo VARCHAR(255) NOT NULL,
        conteudo TEXT NOT NULL,
        PRIMARY KEY (id)
    );
    ```

## Execução

1. Inicie a aplicação:
    ```sh
    mvn spring-boot:run
    ```

2. A aplicação estará disponível em: `http://localhost:8080`

## Documentação da API

A documentação da API está disponível no Swagger. Após iniciar a aplicação, acesse:

http://localhost:8080/swagger-ui/index.html





