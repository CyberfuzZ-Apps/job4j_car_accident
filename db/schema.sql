CREATE TABLE types
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000) NOT NULL
);

CREATE TABLE rules
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(2000) NOT NULL
);

CREATE TABLE accident
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(2000) NOT NULL,
    text    VARCHAR(2000) NOT NULL,
    address VARCHAR(2000) NOT NULL,
    type_id INTEGER NOT NULL REFERENCES types (id)
);

CREATE TABLE accident_rules
(
    accident_id INTEGER NOT NULL REFERENCES accident (id) ON DELETE CASCADE,
    rules_id    INTEGER NOT NULL REFERENCES rules (id)
);

INSERT INTO types (name) VALUES ('Две машины');
INSERT INTO types (name) VALUES ('Машина и пешеход');
INSERT INTO types (name) VALUES ('Машина и велосипед');

INSERT INTO rules (name) VALUES ('Статья 1');
INSERT INTO rules (name) VALUES ('Статья 2');
INSERT INTO rules (name) VALUES ('Статья 3');

/* Security */

CREATE TABLE authorities
(
    id        SERIAL PRIMARY KEY,
    authority VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE users
(
    id           SERIAL PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(100) NOT NULL,
    enabled      BOOLEAN DEFAULT TRUE,
    authority_id INT          NOT NULL REFERENCES authorities (id)
);

INSERT INTO authorities (authority)
VALUES ('ROLE_USER');
INSERT INTO authorities (authority)
VALUES ('ROLE_ADMIN');

INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', TRUE, '$2a$10$wY1twJhMQjGVxv4y5dBC5ucCBlzkzT4FIGa4FNB/pS9GaXC2wm9/W',
        (SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));