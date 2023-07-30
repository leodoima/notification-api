create table token(
    id bigint not null auto_increment,
    hash_token varchar(255) not null,
    created_at date not null,
    expiration_at date not null,
    token_status varchar(255),

    primary key(id)
);