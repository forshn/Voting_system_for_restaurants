DROP TABLE IF EXISTS user_role;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS vote;
DROP TABLE IF EXISTS menu;
DROP TABLE IF EXISTS restaurant;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                           NOT NULL,
    email      VARCHAR                           NOT NULL,
    password   VARCHAR                           NOT NULL,
    registered TIMESTAMP           DEFAULT now() NOT NULL,
    enabled    BOOL                DEFAULT TRUE  NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_role
(
    user_id INTEGER NOT NULL,
    role    VARCHAR,
    CONSTRAINT user_role_idx UNIQUE (user_id, role),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurant
(
    id   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR,
    CONSTRAINT restaurant_name_idx UNIQUE (name)
);

CREATE TABLE dish
(
    id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER                           NOT NULL,
    added         TIMESTAMP           DEFAULT now() NOT NULL,
    name    VARCHAR NOT NULL,
    price   INTEGER NOT NULL,
    CONSTRAINT restaurant_id_name_idx UNIQUE (restaurant_id, name),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
);

CREATE TABLE vote
(
    id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    restaurant_id INTEGER                           NOT NULL,
    user_id       INTEGER                           NOT NULL,
    voted         TIMESTAMP           DEFAULT now() NOT NULL,
    CONSTRAINT user_id_voted_idx UNIQUE (user_id, voted),
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);