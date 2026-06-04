CREATE TABLE users
(
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50)  NOT NULL,
    email       VARCHAR(100) NOT NULL,
    password    VARCHAR(128),
    enabled     BOOLEAN      NOT NULL,
    authorities TEXT[]
);

ALTER TABLE users
    ADD CONSTRAINT uc_users_email UNIQUE (email);