INSERT INTO users (username, password, enabled)
VALUES ('Lionel', 'Messi', true);

INSERT INTO users (username, password, enabled)
VALUES ('Dhileepan', 'Rajendran', true);

INSERT INTO authorities (username,authority) 
VALUES ('Lionel','ROLE_ADMIN');

INSERT INTO authorities (username,authority) 
VALUES ('Lionel','ROLE_USER');

INSERT INTO authorities (username,authority) 
VALUES ('Dhileepan','ROLE_USER');


