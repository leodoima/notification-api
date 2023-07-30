package com.notificationapi.enums;

public enum SmsTypeEnum {
    RECOVER_ACCOUNT_TOKEN("Olá, segue seu código de verificação para alteração de senha: "),
    CONFIRM_PHONE_NUMBER_TOKEN("Olá, para validação deste número de telefone, informe o seguinte código: "),
    CREATE_ACCOUNT("Sua conta foi criada com sucesso em nossa plataforma. Favor validar, se este for seu número!"),
    WARNING("Sua conta foi acessada");

    public final String messageDescription;

    SmsTypeEnum(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getMessageDescription() {
        return this.messageDescription;
    }
}
