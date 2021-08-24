DELETE FROM user_role;
DELETE FROM dish;
DELETE FROM menu;
DELETE FROM vote;
DELETE FROM restaurant;
DELETE FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@gmail.com', 'password'),
       ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100001),
       ('USER', 100000);

INSERT INTO restaurant (name)
VALUES ('Кафе'),
       ('Бар'),
       ('Ресторан');

INSERT INTO menu (restaurant_id)
VALUES (100002),
       (100003),

INSERT INTO dish (name, price, restaurant_id)
VALUES ('Тако', 500, 100002),
       ('Буррито', 760, 100002),
       ('Гуакамоле', 450, 100003),
       ('Стейк из акулы', 850, 100003),
       ('Мраморная говядина', 730, 100003),
       ('Мисо суп', 300, 100004),
       ('Паста Карбонара', 600, 100004),
       ('Гренки', 150, 100004),
       ('Салат Цезарь', 400, 100004);

