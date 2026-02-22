# 🚀 API de Gerenciamento - Auto Escola S042

Este projeto é uma API REST desenvolvida para o gerenciamento de instrutores e alunos de uma autoescola, permitindo o cadastro, atualização, listagem e remoção lógica de registros.

## 🎯 Desafio - Auto Escola

Este projeto foi desenvolvido como parte de um desafio técnico para criar uma solução robusta de gerenciamento para autoescolas. O objetivo é fornecer uma API escalável e bem estruturada para lidar com o fluxo de instrutores e alunos.

[Saiba mais sobre o desafio aqui](https://github.com/WeslleyRocha/Auto-Escola-S042/blob/main/src/main/resources/file/Desafio%20-%20Auto-Escola.pdf)

---

## 🛠️ Tecnologias Utilizadas

* **Java 25**
* **Spring Boot 3+**
* **Maven**
* **MySQL**
* **Flyway** (para migração de banco de dados)
* **Lombok**
* **Spring Data JPA**
* **Jakarta Validation**

---

## 📡 Endpoints da API

Abaixo estão os exemplos de requisições para cada método disponível na API.

### Verificar Status da API (Health Check)
Verifica se o servidor está rodando corretamente.

* **URL:** `GET http://localhost:8080/health-check`
* **Resposta esperada:** `200 OK`


### 1. Instrutores

#### Listar Todos (Paginação)
* **URL:** `GET http://localhost:8080/instrutores`
* **Parâmetros Opcionais:** `page`, `size`, `sort`

#### Cadastrar Novo Instrutor
* **URL:** `POST http://localhost:8080/instrutores`
* **Body (JSON):**
```json
{
  "nome": "Michael Silva",
  "email": "michael.silva@provedor.com",
  "cnh": "32165498900",
  "telefone": "(11)12345-2222",
  "especialidade": "MOTOS",
  "endereco": {
    "logradouro": "Rua Bahia",
    "numero": "88",
    "complemento": "Bloco B",
    "bairro": "Savassi",
    "cidade": "Belo Horizonte",
    "uf": "MG",
    "cep": "30150331"
  }
}
```

#### Atualizar Instrutor
* **URL:** `PUT http://localhost:8080/instrutores`
* **Body (JSON):**
```json
{
  "id": 1,
  "nome": "Michael Silva Editado",
  "telefone": "(11)12345-2222",
  "endereco": {
    "logradouro": "Rua Bahia Atualizada",
    "numero": "90",
    "complemento": "Bloco C",
    "bairro": "Savassi",
    "cidade": "Belo Horizonte",
    "uf": "MG",
    "cep": "30150331"
  }
}
```

#### Excluir Instrutor (Lógico)
* **URL:** `DELETE http://localhost:8080/instrutores/{id}`

---

### 2. Alunos

#### Listar Todos (Paginação)
* **URL:** `GET http://localhost:8080/alunos`

#### Cadastrar Novo Aluno
* **URL:** `POST http://localhost:8080/alunos`
* **Body (JSON):**
```json
{
  "nome": "João Silva",
  "email": "joao.silva@email.com",
  "cpf": "11122233344",
  "telefone": "(11) 98765-4321",
  "especialidade": "CARRO",
  "endereco": {
    "logradouro": "Rua das Flores",
    "numero": "123",
    "bairro": "Centro",
    "cidade": "São Paulo",
    "uf": "SP",
    "cep": "01001000"
  }
}
```

#### Atualizar Aluno
* **URL:** `PUT http://localhost:8080/alunos`
* **Body (JSON):**
```json
{
    "id": 1,
    "nome": "Wes Silva",
    "telefone": "",
    "endereco": {
      "logradouro": "Rua das Flores",
      "numero": "123",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "uf": "SP",
      "cep": "01001000"
    }
}
```
#### Excluir Aluno (Lógico)
* **URL:** `DELETE http://localhost:8080/alunos/{id}`

---

## 🗄️ Banco de Dados

O projeto utiliza **MySQL** e **Flyway** para gerenciar o esquema do banco de dados.
As migrações incluem:
1. Criação da tabela `instrutores`.
2. Criação da tabela `alunos`.
3. Populando as tabelas `Instrutores` e `Alunos` com dados iniciais de exemplo.
