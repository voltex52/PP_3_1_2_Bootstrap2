INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN'), (2, 'ROLE_USER'), (3, 'ROLE_GUEST');
INSERT INTO users (id, firstName, lastName, email, username, password) VALUES (1, 'Nikita', 'Leonov', 'voltex7@yandex.ru', 'admin', '$2y$12$x3HPSGLtFgh3F4XmX6zgrOaaD.My2lzOjadU.JW0vCXYZVOokAW7q');
INSERT INTO users_roles VALUES (1, 1);