package com.notificationapi.enums;

public enum SmsType {

    RECOVER_ACCOUNT_TOKEN("Olá, segue seu código de verificação para alteração de senha: "),
    VALIDATE_PHONE_NUMBER_TOKEN("Olá, para validação deste número de telefone, informe o seguinte código: "),
    CREATE_ACCOUNT("Sua conta foi criada com sucesso em nossa plataforma. Favor validar, se este for seu número!");

    SmsType(String messageDescription) {
    }

    public String getSmsType() {
        return this.name();
    }
}
