# 📦 Client API — Spring Boot + ViaCEP

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/SpringBoot-API-success)
![JPA](https://img.shields.io/badge/SpringData-JPA-orange)
![Database](https://img.shields.io/badge/MySQL-Hibernate-informational)
![Status](https://img.shields.io/badge/Status-Learning%20Project-brightgreen)

---

## 📌 Sobre o projeto

API REST desenvolvida com **Spring Boot** para gerenciamento de clientes com resolução automática de endereço via **consulta de CEP**.

Ao cadastrar ou atualizar um cliente, o sistema:

- Consulta o CEP em um serviço externo
- Persiste o endereço no banco caso ainda não exista
- Associa o cliente ao endereço salvo

O objetivo foi praticar **organização em camadas, integração externa e persistência com JPA**, aplicando padrões comuns utilizados no desenvolvimento backend.

---

## 🧠 Conceitos aplicados na prática

- Arquitetura em camadas
- Injeção de dependência (Spring)
- Repository Pattern
- Service Layer
- Integração com API externa (ViaCEP)
- Relacionamento entre entidades JPA
- Reaproveitamento de dados persistidos
- Tratamento básico de exceções
- Separação de responsabilidades

---

## 🛠️ Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- ViaCEP

---

## 🏗️ Estrutura do projeto

```
controller
├─ Endpoints REST

service
├─ Regras de negócio
├─ Consulta de CEP
├─ Associação Cliente ↔ Endereço

repository
├─ Persistência de dados

model
├─ Entidades JPA
```

---

## ⚙️ Funcionalidades implementadas

- ✅ Cadastro de cliente
- ✅ Atualização de cliente
- ✅ Remoção de cliente
- ✅ Listagem de clientes
- ✅ Consulta automática de endereço por CEP
- ✅ Persistência de endereço
- ✅ Reutilização de endereço existente
- ✅ Associação entre entidades

---

## 🔁 Fluxo da aplicação

1️⃣ Cliente envia requisição com CEP  
2️⃣ API verifica se o endereço já existe  
3️⃣ Caso não exista → consulta ViaCEP  
4️⃣ Endereço é persistido  
5️⃣ Cliente é salvo com referência ao endereço  

---

## 📡 Exemplo de requisição

```json
{
  "nome": "Enzo",
  "endereco": {
    "cep": "06160180"
  }
}
```

---

## ▶️ Como executar o projeto

### 1️⃣ Clonar repositório

```bash
git clone (https://github.com/EnzoAnthony006/API-REST-Clientes-e-Enderecos.git)
cd projeto
```

### 2️⃣ Criar banco

```sql
CREATE DATABASE aula_jpa;
```

### 3️⃣ Configurar application.properties

```properties
spring.datasource.username=SEU_USER
spring.datasource.password=SUA_SENHA
```

### 4️⃣ Executar

```bash
./mvnw spring-boot:run
```

API disponível em:

```
http://localhost:8080
```

---

## 🎯 Objetivo

Projeto desenvolvido como parte do processo de evolução em **Backend Java**, explorando integração externa, persistência relacional e boas práticas arquiteturais utilizando Spring Boot.
