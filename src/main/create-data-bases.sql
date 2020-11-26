CREATE TABLE IF NOT EXISTS users
(
    id       INTEGER PRIMARY KEY AUTOINCREMENT,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(30)  NOT NULL
);

insert into users (email, password, role)
VALUES ('admin@gmail.com', 'admin', 'ADMIN'),
       ('user@gmail.com', 'user', 'USER');

select *
from users;
