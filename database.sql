CREATE TABLE users
(
    username         VARCHAR(100) NOT NULL,
    password         VARCHAR(100) NOT NULL,
    name             VARCHAR(100) NOT NULL,
    token            VARCHAR(100),
    token_expired_at BIGINT,
    PRIMARY KEY (username),
    UNIQUE (token)
) ENGINE InnoDB;

INSERT INTO users (username, password, name, token, token_expired_at)
VALUES('dirga', 'rahasia', 'Dirga Meligo', '$2a$10$pfxhFJ/PsOmRy0i6oKOIN.dX059i58i8q3kc7MnZGsB8JipX2ohd2', 1678675200);

CREATE TABLE pokemon_users
(
    id                  INT AUTO_INCREMENT NOT NULL,
    user_id             VARCHAR(100) NOT NULL,
    pokemon_id          INT,
    pokemon_nickname    VARCHAR(100),
    current_fibo        INT,
    PRIMARY KEY (id)
) ENGINE InnoDB;

