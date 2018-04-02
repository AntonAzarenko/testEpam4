use mydb;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  name             VARCHAR(45)                 NOT NULL,
  email            VARCHAR(45)               NOT NULL,
  password         VARCHAR(45)                 NOT NULL,
  registered       TIMESTAMP DEFAULT now() NOT NULL,
  enabled          BOOL DEFAULT TRUE       NOT NULL,
  role             VARCHAR(7)               NOT NULL
);



