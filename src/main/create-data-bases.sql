CREATE TABLE IF NOT EXISTS users
(
    user_id  INTEGER PRIMARY KEY AUTO_INCREMENT,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS roles
(
    role_id INTEGER PRIMARY KEY AUTO_INCREMENT,
    role    VARCHAR(255) NOT NULL UNIQUE
);

CREATE table if not exists users_roles
(
    user_id int not null references users (user_id),
    role_id int not null references roles (role_id),
    constraint users_roles_pk primary key (user_id, role_id)
);

insert into users (email, password)
VALUES ('admin@gmail.com', 'admin');

insert into roles (role)
values ('Admin'),
       ('User'),
       ('Guest');

insert into users_roles (user_id, role_id)
VALUES (1, 1),
       (1, 2);
