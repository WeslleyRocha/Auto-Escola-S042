# 🚀 API de Gerenciamento de Instrutores - TechConsult

Este projeto é uma API REST desenvolvida para o gerenciamento de instrutores e consultores técnicos, permitindo o cadastro, atualização, listagem e remoção de registros, além de possuir um endpoint de verificação de integridade.

## 🛠️ Tecnologias Utilizadas

* **Java 25** (ou sua versão atual)
* **Spring Boot 3+**
* **Maven**
* **Lombok**
* **Jakarta Validation** (para máscaras de telefone e campos obrigatórios)

---

## 📡 Endpoints da API (Postman/Insomnia)

Abaixo estão os exemplos de requisições para cada método disponível na API.

### 1. Verificar Status da API (Health Check)
Verifica se o servidor está rodando corretamente.

* **URL:** `GET http://localhost:8080/health-check`
* **Resposta esperada:** `200 OK`

### 2. Listar Todos os Instrutores
Retorna uma lista de todos os instrutores cadastrados.

* **URL:** `GET http://localhost:8080/instrutores`

### 3. Cadastrar Novo Instrutor
Cria um novo registro no sistema.

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

### 4. Atualizar Instrutor Existente

Atualiza parcialmente ou totalmente os dados de um instrutor através do ID.

* **URL:** `PUT http://localhost:8080/instrutores`

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

### 5. Remover Instrutor

Exclui um instrutor permanentemente do banco de dados.

* **URL:** `DELETE http://localhost:8080/instrutores/{}`

* **Parâmetro:** id (Ex: 3)