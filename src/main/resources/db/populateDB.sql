DELETE
FROM user_role;
DELETE
FROM meal;
DELETE
FROM vote;
DELETE
FROM restaurant;
DELETE
FROM users;

ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@mail.ru', 'password'),
       ('Admin', 'adm@mail.ru', 'password2');

INSERT INTO user_role (role, user_id)
VALUES ('ADMIN', 100001),
       ('USER', 100000);

INSERT INTO restaurant (name)
VALUES ('Ресторан'),
       ('Кафе'),
       ('Закусочная');

INSERT INTO meal (name, price, restaurant_id, added)
VALUES ('Тако', 500, 100002, current_date),
       ('Буррито', 760, 100002, current_date),
       ('Гуакамоле', 450, 100003, current_date),
       ('Стейк из акулы', 850, 100003, current_date),
       ('Мраморная говядина', 730, 100003, current_date),
       ('Мисо суп', 300, 100004, current_date),
       ('Паста Карбонара', 600, 100004, current_date),
       ('Гренки', 150, 100004, current_date),
       ('Салат Цезарь', 400, 100004, current_date),
       ('Вчерашний суп', 500, 100002, current_date),
       ('Вчерашние гренки', 150, 100002, current_date - 1),
       ('Вчерашний гуакамоле', 450, 100003, current_date - 1),
       ('Вчерашний буррито', 760, 100003, current_date - 1),
       ('Вчерашний тако', 500, 100003, current_date - 1);

INSERT INTO vote (restaurant_id, user_id)
VALUES (100002, 100000);

