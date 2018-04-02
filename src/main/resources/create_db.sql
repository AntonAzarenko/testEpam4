use mydb;

DROP TABLE IF EXISTS users_subscription;

CREATE TABLE users_subscription
(
  id               INTEGER PRIMARY KEY AUTO_INCREMENT,
  title             VARCHAR(45) NOT NULL ,
  price             DECIMAL(10,2)
);



