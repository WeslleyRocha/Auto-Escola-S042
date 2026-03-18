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
* **Spring Security**
* **JWT (JSON Web Token)**

---

## 📡 Endpoints da API

Abaixo estão os exemplos de requisições para cada método disponível na API.

### Verificar Status da API (Health Check)
Verifica se o servidor está rodando corretamente.

* **URL:** `GET http://localhost:8080/health-check`
* **Resposta esperada:** `200 OK`

---

### 🔐 Autenticação

Para acessar os endpoints protegidos, é necessário obter um token JWT.

#### Login
* **URL:** `POST http://localhost:8080/login`
* **cURL:**
```bash
curl -X POST http://localhost:8080/login \
-H "Content-Type: application/json" \
-d '{
  "login": "admin",
  "senha": "123"
}'
```

---

### 1. Usuários

#### Listar Todos (Paginação)
* **URL:** `GET http://localhost:8080/usuarios`
* **cURL:**
```bash
curl -X GET "http://localhost:8080/usuarios?page=0&size=10&sort=login,asc" \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### Detalhar Usuário
* **URL:** `GET http://localhost:8080/usuarios/{id}`
* **cURL:**
```bash
curl -X GET http://localhost:8080/usuarios/1 \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### Cadastrar Novo Usuário
* **URL:** `POST http://localhost:8080/usuarios`
* **cURL:**
```bash
curl -X POST http://localhost:8080/usuarios \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
  "login": "novo.usuario",
  "senha": "senhaSegura123"
}'
```

#### Atualizar Usuário
* **URL:** `PUT http://localhost:8080/usuarios`
* **cURL:**
```bash
curl -X PUT http://localhost:8080/usuarios \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
  "id": 1,
  "login": "usuario.atualizado",
  "senha": "novaSenha123"
}'
```

#### Excluir Usuário (Lógico)
* **URL:** `DELETE http://localhost:8080/usuarios/{id}`
* **cURL:**
```bash
curl -X DELETE http://localhost:8080/usuarios/1 \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

### 2. Instrutores

#### Listar Todos (Paginação)
* **URL:** `GET http://localhost:8080/instrutores`
* **cURL:**
```bash
curl -X GET "http://localhost:8080/instrutores?page=0&size=10&sort=nome,asc" \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### Detalhar Instrutor
* **URL:** `GET http://localhost:8080/instrutores/{id}`
* **cURL:**
```bash
curl -X GET http://localhost:8080/instrutores/1 \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### Cadastrar Novo Instrutor
* **URL:** `POST http://localhost:8080/instrutores`
* **cURL:**
```bash
curl -X POST http://localhost:8080/instrutores \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
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
}'
```

#### Atualizar Instrutor
* **URL:** `PUT http://localhost:8080/instrutores`
* **cURL:**
```bash
curl -X PUT http://localhost:8080/instrutores \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
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
}'
```

#### Excluir Instrutor (Lógico)
* **URL:** `DELETE http://localhost:8080/instrutores/{id}`
* **cURL:**
```bash
curl -X DELETE http://localhost:8080/instrutores/1 \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

### 3. Alunos

#### Listar Todos (Paginação)
* **URL:** `GET http://localhost:8080/alunos`
* **cURL:**
```bash
curl -X GET "http://localhost:8080/alunos?page=0&size=10&sort=nome,asc" \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### Detalhar Aluno
* **URL:** `GET http://localhost:8080/alunos/{id}`
* **cURL:**
```bash
curl -X GET http://localhost:8080/alunos/1 \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

#### Cadastrar Novo Aluno
* **URL:** `POST http://localhost:8080/alunos`
* **cURL:**
```bash
curl -X POST http://localhost:8080/alunos \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
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
}'
```

#### Atualizar Aluno
* **URL:** `PUT http://localhost:8080/alunos`
* **cURL:**
```bash
curl -X PUT http://localhost:8080/alunos \
-H "Content-Type: application/json" \
-H "Authorization: Bearer SEU_TOKEN_AQUI" \
-d '{
    "id": 1,
    "nome": "Wes Silva",
    "telefone": "(11) 99999-8888",
    "endereco": {
      "logradouro": "Rua das Flores",
      "numero": "123",
      "bairro": "Centro",
      "cidade": "São Paulo",
      "uf": "SP",
      "cep": "01001000"
    }
}'
```

#### Excluir Aluno (Lógico)
* **URL:** `DELETE http://localhost:8080/alunos/{id}`
* **cURL:**
```bash
curl -X DELETE http://localhost:8080/alunos/1 \
-H "Authorization: Bearer SEU_TOKEN_AQUI"
```

---

### 4. Instruções (Agendamento)

#### Listar Toos os Agendamentos
* **URL:** `GET http://localhost:8080/instrucao`
* **cURL:**
```bash
curl -X GET 'http://localhost:8080/instrucao' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBcGkgQXV0byBFc2NvbGEgUzA0MiIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzczODYyMDg0fQ.s9WvJyotsClQ9JgGNEBdtdi-nMawWO433NBz78lusI4'
```

#### Agendar Nova Instrução
* **URL:** `POST http://localhost:8080/instrucao`
* **cURL:**
```bash
curl -X POST 'http://localhost:8080/instrucao' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBcGkgQXV0byBFc2NvbGEgUzA0MiIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzczODU4NTU1fQ.Z9OftwErJNZPxccrqNz52Q6IATBCqH9ULoT5nkxLxWQ' \
-d '{
  "idAluno": 9,
  "idInstrutor": 6,
  "data": "20/03/2026 - 13:00"
}'
```

#### Cancelar Instrução
* **URL:** `DELETE http://localhost:8080/instrucao`
* **cURL:**
```bash
curl -X DELETE 'http://localhost:8080/instrucao' \
-H 'Content-Type: application/json' \
-H 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJBcGkgQXV0byBFc2NvbGEgUzA0MiIsInN1YiI6ImFkbWluIiwiZXhwIjoxNzczODU4NTU1fQ.Z9OftwErJNZPxccrqNz52Q6IATBCqH9ULoT5nkxLxWQ' \
-d '{
  "idInstrucao": 2,
  "motivo": "ALUNO_DESISTIU"
}'
```

---

## 🗄️ Banco de Dados

O projeto utiliza **MySQL** e **Flyway** para gerenciar o esquema do banco de dados.
As migrações incluem:
1. Criação da tabela `instrutores`.
2. Criação da tabela `alunos`.
3. Criação da tabela `usuarios`.
4. Criação da tabela `instrucoes` (agendamento).
5. Populando as tabelas `Instrutores`, `Alunos`, `Usuarios` e `Agendamento` com dados iniciais de exemplo.
