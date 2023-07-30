alter table sms
add foreign key (token_id) references token(id);