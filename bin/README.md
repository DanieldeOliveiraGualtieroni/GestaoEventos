# 💻 Gestão de Eventos e Participantes

Sistema de gerenciamento de eventos desenvolvido com Java e Spring Boot. Este projeto tem como objetivo facilitar o cadastro, controle e acompanhamento de eventos em geral, como palestras, workshops, conferências, entre outros.


## ⚙️ O que faz o projeto?

- Cadastro de novos eventos
- Edição e remoção de eventos existentes
- Listagem de eventos cadastrados
- Integração com banco de dados relacional
- Exposição de endpoints para consumo por front-ends ou sistemas externos

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **Maven**
- **JPA / Hibernate**
- **MySQL** (ou banco H2 para testes locais)
- **Git**

---

## 📁 Estrutura do Projeto
```
src/
└── main/
    ├── java
    │       └── atividade.gestao
    │          └── GestaoApplication.java
    │               │
    │               ├──  atividade.gestao.controllers   
    │               │   └── EventoController.java
    │               │   └── ParticipanteController.java
    │               │
    │               ├── atividade.gestao.dto           
    │               │   └── EventosDTO.java
    │               │   └── ParticipantesDTO.java
    │               │
    │               ├── atividade.gestao.entities          
    │               │   └── Eventos.java
    │               │   └── Participantes.java
    │               │
    │               ├── atividade.gestao.repositories        
    │               │   └── EventoRepository.java
    │               │   └── ParticipanteRepository.java
    │               │
    │               ├── atividade.gestao.services          
    │               │   └── EventoService.java
    │               │   └── ParticipanteService.java
    │               │
    │
    └── resources/
        ├── application.properties 
```
