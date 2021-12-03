CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled  BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (username)
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users (username)
);

INSERT INTO users (username, password)
VALUES ('user', '$2a$10$b9wO6VNMiMLefzbCs8cAnOIuG4z9m2TbpRIAf7hCHG8i/kdBUXSRa');

INSERT INTO authorities (username, authority) VALUES ('user', 'ROLE_USER');