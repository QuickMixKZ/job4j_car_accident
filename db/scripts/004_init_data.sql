insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$DQTw.XiEB0JDJ79wUE9khuDyhudPfxgEPgKIYnW3yDmKc1cztPVba',
        (select id from authorities where authority = 'ROLE_ADMIN'));