# 1. Setup e Pré-requisitos
## 1.1 Visão Geral
### 1.1.1 Objetivo Geral
Conheça o Spring Boot, projeto que facilita a implementação de repósitorios baseados em banco de dados relacionais. Nesse contexto, explore a linguagem de programação  Kotlin e entenda como o Projeto Spring Data JPA facilita a criação de aplicativos baseados em Spring que usam tecnologias de acesso a dados

### 1.1.2 Pré-requisitos
- IDE para desenvolvimento Kotlin(IntelliJ Community)
- JDK 17+
- Kotlin 1.7.22
- Postman
- Sintaxe Kotlin e POO
- Entendimento sobre Arquitetura REST
- Utilização do Spring Boot 3.0.3
- Noções sobre BDR (Banco de Daods Relacionais)

## 1.2 Contextualizando JDBC, JPA, ORM e Hibernate
### 1.2.1 JDBC
- JDBC significa Java EE Database Conecctivity
- JDBC é uma API de nível de chamada, o que significa que as instruções de SQL são transmitidas como sequências para a API que, então, se encarrega de executá-las no RDMS
- SQL é uma linguagem declarativa de sintaxe relativamente simples, voltada ao banco  de dados relacionais.
- Um banco de dados é uma coleção organizada normalmente armaazenadas eletronicamenente em um sistema de computador
- Conexão com Banco de Dados MYSQL com JDBC

### 1.2.2 JPA
- O JPA(Java Persistence API) define uma maneira para mapear Plain Old Java Objects, POJOs, para um banco de dados. Estes POJOs são chamados  de entidades (Entities)
- JPA, portanto, é um framework utilizado na camada de persitência para o desenvolvedor ter uma maior produtividade no contexto Java

### 1.2.3 ORM e Hibernate
- ORM (Mapeamento Objeto Relacional), é uma técnica para aproximar o paradigma de desenvolvimento de aplicações orientadas a objeto ao paradigma do banco relacional.
- O Hibernate é uma ferramenta de consulta e persistência objeto/relacional de alta performance.
- Na versão 3.x o Hibernate implementa a especificação JPA através do conceito de anotações, o que facilita ainda mais o mapeamento objeto-relacional, que pode agora ser feito diretamente na classe.

## 1.3 Spring DataJPA
- O Spring Data JPA, é a maior parte da família Spring Data, facilita a implementação de repositórios baseados em JPA.
- Este módulo lida com suporte aprimorado para camadas de acesso a dado baseadas em JPA.
- Facilita a criação de aplicativos baseados em Spring que usam tecnologias de acesso a dados

## 1.4 Conhecendo o domínio da Aplicação.
### API para sistema de Avaliação de Créditos
Uma empresa de emprestimo precisa criar um sistema de análise de solicitação de crédito. Sua tarefa será criar uma API REST SPRING BOOTE KOTLIN para a empresa aos seus clientes as seguintes funcionalidades.

### Customer

<style>
.v {
    writing-mode: vertical-lr;
    text-align: center;
}
</style>

| Customer | | id | <div class="v">firstName</div> | <div class="v">lastName</div> | <div class="v">cpf</div> | <div class="v">income</div> | <div class="v">email</div> | <div class="v">password</div> | <div class="v">zipcode</div> | <div class="v">street</div> |
|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|:---:|
| Cadastrar | req | | x | x | x | x | x | x | x | x |
| | res | | | | | | | | | |
| Editar | req | x | x | x | | x | | | x | x |
| | res | | | | x | x | x | | x | x |
| Visualizar | req | x | | | | | | | | |
| | res | | x | x | x | x | x | | x | x |
| Deletar | req | x | | | | | | | | |
| | res | | | | | | | | | | 

### Solicitação de Empréstimo (Credit)

| Credito 	|  	| <div class="v">creditCode (PK)</div> 	| <div class="v">creditValue</div> 	| <div class="v">dayFirstOfInstallment</div> 	| <div class="v">numberOfInstallments</div> 	| <div class="v">customerId(FK)<br/>customer.id</div> 	| <div class="v">customer.email</div> 	| <div class="v">customer.income</div> 	| <div class="v">status</div> 	|
|:---:	|:---:	|:---:	|:---:	|:---:	|:---:	|:---:	|---	|---	|---	|
| Cadastrar 	| req 	|  	| x 	| x 	| x 	| x 	|  	|  	|  	|
|  	| res 	|  	|  	|  	|  	|  	|  	|  	|  	|
| ListAll* 	| req 	|  	|  	|  	|  	| x 	|  	|  	|  	|
|  	| res 	| x 	| x 	|  	| x 	|  	|  	|  	|  	|
| Visualizar** 	| req 	| x 	|  	|  	|  	| x 	|  	|  	|  	|
|  	| res 	|  	| x 	| x 	| x 	| x 	| x 	| x 	| x 	|

ListAll* = Listar todos as solicitações de emprestimo
Visualizar** = Visualizr um 

### Desafio
Implemente as regras de negócio a seguir para a solicitação de emprestimo:
- o máximo de parcelas permitido deverá ser  no máximo 3 meses após o dia atual
- data da primeira parcela deverá ser no máximo 3 meses apos o dia atual

## 1.5 Iniciando o Projeto no Spring Initialzr 
### 1.5.1 Link
https://start.spring.io

### 1.5.2 Project
#### Project
- [x] Graddle - Groovy


#### Language
- [x] Kotlin

#### Spring Boot
- [x] 3.0.4
- [ ] Groovy

#### Project Metada
- Group: br.com.ruben
- Artifact: credit-application-system
- Name: credit-application-system
- Description: Client Application System with Spring Boot and Kotlin
- Package name: br.com.ruben.credit-application-system
- Packaging: Jar
- Java: 17

#### Dependecies
- Spring WEB : vai permitir criar uma API Rest
- Validation : Validações de Campo de Entrada
- Spring Data JPA : Persistir dados em SQL com JAPA Persistence API
- Flyway Migration : "Git de banco de dados"
- H2 Database: Banco de Dados em memória

## 1.6 Importando o Projeto parao o IntelliJ

## 1.7 Criando o repósitorio do projeot e "first commit"

```shell
git init
....
```

# 2. Criando as classes da Camada de de Persitência (Model)
# 2.1 Criando classes de Modelo
```shell
git checkout -b "01/classes_camada_de_persitencia"
```

Criado eos arquivos
```shell
entity.
   Address.kt
   CreditCode.kt
   Customer.kt

enumeration.
   Status.kt
```

## 2.2 Arquivo de Configuração "application.yml"

````
spring:
  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        trace: false
        web-allow-others: false
````

## 2.3 Anotando as Classes de Modelo com as Anotações JPA (jakarta.persistence)

