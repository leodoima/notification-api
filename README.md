# Notification-api
Aplicação que disponibiliza recursos para envio de notificações via celular e/ou e-mail.


* O objetivo é fornecer uma api para envio de notificações específicas, tais como: *token para validações de telefone/e-mail, autenticação de dois fatores no acesso à conta, etc*.

```mermaid
App ->> Notification-Api: accountRecover / phoneValidate
Notification-Api->>Zenvia: sendSms()
Zenvia-->> Client: Enviar SMS
Zenvia-->> Notification-Api: Resposta do envio
Notification-Api -->> App: Status do envio
```

</br>

### Recursos e tecnologias
Descrição dos principais itens contidos no desenvolvimento da aplicação:

* Docker
* MySQL
* Flyway
* Java
* Gradle
* Spring Boot
* [Zenvia](https://www.zenvia.com/) (api externa de comunicação)

### Executar
Sequência de passos para executar a aplicação:

```bash
# Clonar repositório
$ git clone https://github.com/leodoima/sms-app.git

# Ir para pasta raiz do projeto
$ cd sms-app

# Configurar variáveis de ambiente 
Renomear arquivo .env.example para .env
Alterar o conteúdo das chaves para aqueles desejados

# Rodar a aplicação
$ npm start

# Testar a aplicação
$ http://localhost:3333/

```

### Melhorias futuras
Ideias de como evoluir esta aplicação:

- [ ] Aplicar ennvio de e-mail
- [ ] Implementar recurso para documentação da api
- [ ] Aplicar testes unitários
- [ ] Implementar uso de filas para controle de requisições
