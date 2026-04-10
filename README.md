# Sistema de Cadastro de Pessoas e Tarefas

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-%23005C0F.svg?style=for-the-badge&logo=Thymeleaf&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2-Database-blue?style=for-the-badge)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)

## Sobre o Projeto

Este projeto é uma API e Aplicação Web desenvolvida em **Java com Spring Boot** para o gerenciamento de Pessoas e Tarefas. O sistema permite o cadastro completo de usuários (CRUD) e a atribuição de tarefas específicas para cada pessoa cadastrada. 

A aplicação possui uma interface web amigável construída com **Thymeleaf, HTML e CSS**, além de expor endpoints documentados para integrações futuras.

## Tecnologias e Ferramentas Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java 17**
* **Spring Boot 3.3.4**
    * **Spring Web:** Para criação da API REST e controle das rotas web (MVC).
    * **Spring Data JPA:** Para persistência de dados e mapeamento objeto-relacional (ORM).
    * **Thymeleaf:** Motor de templates para renderização das páginas HTML/CSS no lado do servidor.
* **H2 Database:** Banco de dados em memória, ideal para desenvolvimento e testes rápidos.
* **Flyway:** Ferramenta para versionamento e migração do banco de dados.
* **Lombok:** Biblioteca para redução de código boilerplate (Getters, Setters, Construtores, etc).
* **Springdoc OpenAPI (Swagger):** Para geração automática da documentação da API.
* **Maven:** Gerenciador de dependências e build.

## Arquitetura

O projeto foi estruturado utilizando o padrão de **Arquitetura em Camadas (Layered Architecture)** para garantir um código limpo, testável e de fácil manutenção:

* **`Model` (Entidades):** Representação das tabelas do banco de dados (Pessoa e Tarefa).
* **`Repository`:** Interfaces de comunicação com o banco de dados via Spring Data JPA.
* **`Service`:** Camada responsável pelas regras de negócio da aplicação.
* **`Controller`:** Responsável por receber as requisições HTTP (API e Web) e orquestrar as respostas.
* **`DTO` (Data Transfer Object):** Objetos utilizados para transferir dados entre as camadas, garantindo segurança e isolamento das entidades do banco.
* **`Mapper`:** Responsável por converter Entidades em DTOs e vice-versa.

## Como Executar o Projeto

### Pré-requisitos
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) instalado.
* [Maven](https://maven.apache.org/download.cgi) instalado (ou utilizar o Wrapper do Spring).

### Passos para rodar localmente

1. Clone o repositório:
git clone [https://github.com/seu-usuario/CadastroPessoas.git](https://github.com/SergioCezar/CadastroPessoas.git)

2. Acesse a pasta do projeto:
cd CadastroPessoas

3. Baixe as dependências e compile o projeto.

## Acesso à Aplicação

Após iniciar o projeto, os seguintes serviços estarão disponíveis (considerando a porta padrão 8080):

Interface Web (Thymeleaf): http://localhost:8080/ (ajuste o caminho dependendo de como configurou as rotas no Controller)

Documentação da API (Swagger UI): http://localhost:8080/swagger-ui.html

Console do H2 Database: http://localhost:8080/h2-console

