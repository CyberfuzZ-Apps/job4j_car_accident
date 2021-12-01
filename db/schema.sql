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