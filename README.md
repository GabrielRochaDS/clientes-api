# Clientes API

API REST para gerenciamento de clientes, desenvolvida com **Spring Boot** e **Spring Data JPA**.

O projeto implementa operações CRUD completas, com validações de dados, paginação, filtros dinâmicos e tratamento de exceções customizadas.  

## Funcionalidades

- **Criar cliente** (`POST /api/clientes`)
- **Atualizar cliente** (`PUT /api/clientes/{id}`)
- **Excluir cliente** (`DELETE /api/clientes/{id}`)
- **Buscar cliente por ID** (`GET /api/clientes/{id}`)
- **Listar clientes com filtros, paginação e ordenação** (`GET /api/clientes`)
- **Validações de dados**:
  - Nome obrigatório
  - CPF obrigatório e único
  - Data de nascimento obrigatória
  - Telefone opcional
- **Swagger UI** para documentação interativa:
  - [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html#/)

---

## Estrutura do projeto

- `model`: Entidades JPA
- `dto`: Objetos de transferência de dados
- `repository`: Repositórios Spring Data
- `service`: Regras de negócio e tratamento de exceções
- `controller`: Endpoints REST
- `exception`: Exceções customizadas (`NotFoundException`, `DomainConflictException`)
- `utils`: Especificações (Specifications) para filtros dinâmicos

---

## Processo criativo

O desenvolvimento foi guiado pelos princípios de **simplicidade, escalabilidade e manutenção**:

1. **Modelagem de dados** com DTOs para separar input, output e entidades.
2. **Arquitetura Controller-Service-Repository**, garantindo responsabilidades bem definidas.
3. **Filtros dinâmicos** com Specifications, permitindo consultas flexíveis.
4. **Tratamento de erros e transações**, garantindo consistência e respostas claras.
5. **Preparação para evolução futura**, facilitando integração com Docker, Swagger e novos endpoints.
