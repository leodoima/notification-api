package com.notificationapi.enums;

public enum SmsTypeEnum {
    RECOVER_ACCOUNT_TOKEN("Olá, segue seu código para recuperação de senha: "),
    CONFIRM_PHONE_NUMBER_TOKEN("Olá, para validação deste número de telefone, informe o seguinte código: ");

    public final String messageDescription;

    SmsTypeEnum(String messageDescription) {
        this.messageDescription = messageDescription;
    }

    public String getMessageDescription() {
        return this.messageDescription;
    }
}
