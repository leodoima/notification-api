CREATE TABLE sms(
    id bigint not null auto_increment,
    phone_number varchar(100),
    token_id bigint,
    sms_type varchar(255),
    owner_request varchar(255),
    sms_status_send varchar(255),

    primary key(id)
);