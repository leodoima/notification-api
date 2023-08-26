# Notification-api
Aplicação que disponibiliza recursos para envio de notificações via celular e/ou e-mail.


* O objetivo é fornecer uma api para envio de notificações específicas, tais como: *token para validações de telefone/e-mail, autenticação de dois fatores no acesso à conta, etc*.

</br>

```mermaid
sequenceDiagram
App ->> Notification-Api: accountRecover / phoneValidate / 2FA
Notification-Api->>Zenvia: sendSms()
Zenvia-->> Client: Enviar SMS
Zenvia-->> Notification-Api: Resposta do envio
Notification-Api -->> App: Status do envio
```

</br>

### Recursos
Descrição dos principais itens contidos no desenvolvimento da aplicação:

* Docker
* MySQL
* Flyway
* Java
* Gradle
* Swagger
* Spring Boot
* [Zenvia](https://www.zenvia.com/) (api externa de comunicação)

</br>

### Executar
Sequência de passos para executar a aplicação: </br>
- **Requisitos:** *Docker e Docker Compose*

```bash
# Clonar repositório
$ git clone https://github.com/leodoima/notification-api.git

# Ir para pasta raiz do projeto
$ cd notification-api

# Configurar variável de ambiente Total Voice (obrigatória, demais possuem valor default)
$ src/main/resources/application.properties

# Rodar Docker (fará a construção do artefato, criará recursos database, executará aplicação)
$ docker compose up --build

# Documentação de recursos
$ http://localhost:8080/swagger-ui/index.html
```

</br>

### Melhorias futuras
Ideias de como evoluir esta aplicação:

- [ ] Implementar recurso de Health Check
- [ ] Aplicar envio de e-mail
- [x] Implementar recurso para documentação da API
- [ ] Aplicar testes unitários
- [ ] Implementar uso de filas para controle de requisições
