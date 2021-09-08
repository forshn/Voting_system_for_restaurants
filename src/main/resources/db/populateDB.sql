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
VALUES ('Тако', 1000, 100002, CURRENT_DATE),
       ('Буррито', 300, 100002, CURRENT_DATE),
       ('Гуакамоле', 400, 100003, CURRENT_DATE),
       ('Стейк из акулы', 180, 100003, CURRENT_DATE),
       ('Мраморная говядина', 150, 100003, CURRENT_DATE),
       ('Мисо суп', 800, 100004, CURRENT_DATE),
       ('Гренки', 550, 100004, CURRENT_DATE),
       ('Пицца', 700, 100004, CURRENT_DATE),
       ('Паста Карбонара', 450, 100004, CURRENT_DATE),
       ('Вчерашний буррито', 500, 100002, CURRENT_DATE - 1 DAY),
       ('Вчерашний тако', 150, 100002, CURRENT_DATE - 1 DAY);

INSERT INTO vote (restaurant_id, user_id)
VALUES (100002, 100000);
