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
VALUES('dirga', '$2a$10$TGH1JvcjgszNEbtkzu.EteFsBB21dIJ00mqFoUVdiVlgkAgp3dvBq', 'Dirga Meligo', '', 1678675200);

CREATE TABLE pokemon_users
(
    id                  INT AUTO_INCREMENT NOT NULL,
    user_id             VARCHAR(100) NOT NULL,
    pokemon_id          INT,
    pokemon_nickname    VARCHAR(100),
    current_fibo        INT,
    pokemon_name    VARCHAR(100),
    PRIMARY KEY (id)
) ENGINE InnoDB;

