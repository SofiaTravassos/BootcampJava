# 👥 Gestão de Usuários - Bootcamp Java

Projeto desenvolvido durante o **Bootcamp Java da Deloitte**. Este sistema consiste num CRUD completo que evoluiu de uma simples aplicação de consola (Java Puro) para uma aplicação Web moderna utilizando a arquitetura Spring.

## 🚀 Tecnologias Utilizadas
* **Java 21**
* **Spring Boot**
* **Spring Data JPA**
* **H2 Database**
* **Thymeleaf & Bootstrap**

## ⚙️ Funcionalidades Principais
* **CRUD Completo:** Criar, listar, atualizar e remover utilizadores.
* **Herança (Single Table):** O sistema gere dois tipos de entidades na mesma tabela: **Usuários** (Nome, Email) e **Funcionários** (Nome, Email, Cargo).
* **Interface Web:** Telas renderizadas no servidor com design responsivo.

## 💻 Como Executar o Projeto

1. Certifique-se de que tem o **Java 21** e o **Maven** instalados na sua máquina.
2. Abra o terminal na pasta raiz do projeto (onde se encontra o ficheiro `pom.xml`).
3. Inicie o servidor executando o seguinte comando:
   ```bash
   mvn spring-boot:run
