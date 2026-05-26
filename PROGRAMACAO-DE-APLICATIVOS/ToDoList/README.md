# 📋 TodoList — Sistema de Gerenciamento de Tarefas

Aplicação console em Java para cadastro, autenticação de usuários e gerenciamento completo de tarefas, com persistência em banco de dados MySQL.

> Atividade prática do curso Técnico em Desenvolvimento de Sistemas — SENAI CTTI.

---

## 👥 Autores

| Aluno | GitHub |
|---|---|
| João Victor Paschoal | [@JPaschoalDev](https://github.com/JPaschoalDev) |
| Kerllom | — |

---

## 🛠️ Tecnologias utilizadas

- **Java 17+** — linguagem principal
- **Maven** — gerenciamento de dependências
- **MySQL 8** — banco de dados relacional
- **JDBC** — conexão entre Java e MySQL
- **MySQL Connector/J 8.4.0** — driver JDBC

---

## 📐 Arquitetura

O projeto segue o padrão **MVC adaptado** com camada de persistência DAO:

```
src/Tarefas/
├── Main.java                 → Ponto de entrada e menu interativo
├── model/                    → Representação das entidades
│   ├── Usuario.java
│   └── Tarefa.java
├── dao/                      → Camada de acesso a dados (JDBC)
│   ├── ConexaoDB.java
│   ├── UsuarioDAO.java
│   └── TarefaDAO.java
└── controller/               → Regras de negócio
    ├── UsuarioController.java
    └── TarefaController.java
```

**Princípio:** separação de responsabilidades. O `Main` cuida da interação com o usuário, os `Controllers` aplicam regras de negócio e tratam erros, e os `DAOs` são os únicos que falam com o banco.

---

## 🗄️ Modelo de dados

```
┌─────────────────────┐         ┌─────────────────────────────┐
│ usuarios            │         │ tarefas                     │
├─────────────────────┤         ├─────────────────────────────┤
│ id (PK)             │◄────┐   │ id (PK)                     │
│ nome                │     │   │ titulo                      │
│ email (UNIQUE)      │     │   │ descricao                   │
│ senha               │     │   │ status (ENUM)               │
│ data_cadastro       │     │   │ data_criacao                │
└─────────────────────┘     └───┤ usuario_id (FK, CASCADE)    │
                                └─────────────────────────────┘
```

A coluna `status` é um `ENUM('PENDENTE', 'ATRASADA', 'CONCLUIDA')` no MySQL, garantindo integridade dos valores aceitos no banco. A FK `usuario_id` possui `ON DELETE CASCADE`: ao deletar um usuário, suas tarefas são removidas automaticamente.

---

## ✨ Funcionalidades

### Autenticação
- Cadastro de novo usuário com validação de e-mail único
- Login com e-mail e senha
- Logout

### Gerenciamento de tarefas (CRUD completo)
- Adicionar tarefa com título e descrição
- Listar todas as tarefas do usuário logado
- Marcar tarefa como **CONCLUÍDA**, **ATRASADA** ou **PENDENTE**
- Editar título e descrição
- Remover tarefa (com confirmação)
- **Filtrar tarefas por status** (extra)
- **Data de criação automática** (extra, preenchida pelo banco)
- **Persistência total** (extra, via MySQL)

---

## 🚀 Como executar

### Pré-requisitos
- Java JDK 17 ou superior
- Maven instalado e configurado
- MySQL Server 8+ rodando localmente
- IntelliJ IDEA (ou outra IDE Java)

### Passo 1 — Configurar o banco de dados

Abra o MySQL Workbench (ou seu cliente MySQL preferido) e execute o conteúdo do arquivo `database/schema.sql`. Isso cria o banco `todolist_db` com as tabelas `usuarios` e `tarefas`.

### Passo 2 — Configurar a conexão

Abra o arquivo `src/Tarefas/dao/ConexaoDB.java` e ajuste as credenciais do seu MySQL:

```java
private static final String URL = "jdbc:mysql://localhost:3306/todolist_db";
private static final String USUARIO = "root";
private static final String SENHA = "sua_senha_aqui";
```

### Passo 3 — Importar o projeto

1. Abra o IntelliJ IDEA
2. **File → Open** e selecione a pasta `ToDoList/`
3. Aguarde o Maven baixar as dependências (driver MySQL)

### Passo 4 — Executar

Abra `src/Tarefas/Main.java` e clique no botão verde de play ao lado de `public static void main`.

---

## 🎮 Fluxo de uso

```
1. BEM-VINDO À TODOLIST
   → Cadastrar / Login

2. Após login:
   → Adicionar / Listar / Editar / Remover tarefas
   → Marcar status (PENDENTE / ATRASADA / CONCLUIDA)
   → Filtrar por status
   → Logout

3. Sair
```

---

## 🧪 Exemplo de sessão

```
╔════════════════════════════════════╗
║       BEM-VINDO À TODOLIST         ║
╚════════════════════════════════════╝

┌─────────── MENU ───────────┐
│ 1. Cadastrar novo usuário  │
│ 2. Fazer login             │
│ 0. Sair                    │
└────────────────────────────┘
Escolha: 2

--- LOGIN ---
E-mail: kerllom@email.com
Senha: ******
✅ Bem-vindo, Kerllom!

┌─────── TODOLIST ───────┐
│ 1. Adicionar tarefa    │
│ 2. Listar todas        │
│ 3. Filtrar por status  │
│ 4. Marcar CONCLUÍDA    │
│ 5. Marcar ATRASADA     │
│ 6. Marcar PENDENTE     │
│ 7. Editar tarefa       │
│ 8. Remover tarefa      │
│ 0. Sair (logout)       │
└────────────────────────┘
Usuário: Kerllom
Escolha: _
```

---

## 🧱 Decisões de design

- **MVC com DAO:** isola a lógica de banco da interface, facilitando manutenção e testes futuros
- **`PreparedStatement` em todas as queries:** proteção contra SQL Injection
- **`try-with-resources` em todo JDBC:** garante fechamento automático de conexões e recursos
- **ENUM no banco + Enum no Java:** integridade referencial de valores de status garantida em ambos os lados
- **Validações nos setters do model:** previnem dados inválidos antes mesmo de chegarem ao banco
- **Mensagens de feedback no Controller:** padronizadas para boa experiência de uso

---

## 📂 Estrutura completa do projeto

```
ToDoList/
├── database/
│   └── schema.sql            → Script SQL de criação do banco
├── src/
│   └── Tarefas/
│       ├── Main.java
│       ├── controller/
│       │   ├── TarefaController.java
│       │   └── UsuarioController.java
│       ├── dao/
│       │   ├── ConexaoDB.java
│       │   ├── TarefaDAO.java
│       │   └── UsuarioDAO.java
│       └── model/
│           ├── Tarefa.java
│           └── Usuario.java
├── pom.xml                   → Configuração Maven
├── .gitignore
└── README.md
```

---

## ⚠️ Observações

- A senha do usuário é armazenada em texto puro neste projeto por simplicidade didática. Em produção, deve-se usar hashing (BCrypt, Argon2, etc).
- O sistema permite múltiplos usuários no mesmo banco, mas cada um vê apenas as próprias tarefas (filtro por `usuario_id` em todas as queries).
