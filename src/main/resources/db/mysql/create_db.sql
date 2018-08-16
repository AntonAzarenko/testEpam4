DROP DATABASE IF EXISTS `mydb`;

CREATE DATABASE `mydb`
  DEFAULT CHARACTER SET utf8;

GRANT SELECT, INSERT, UPDATE, DELETE
ON `mydb`.*
TO app@'%'
IDENTIFIED BY 'app_pw';

USE mydb;
CREATE TABLE catalog_periodicals (
  id               INTEGER        NOT NULL AUTO_INCREMENT,
  title            VARCHAR(50)    NOT NULL,
  discription      VARCHAR(100)   NOT NULL,
  publisher        VARCHAR(50)    NOT NULL,
  output_frequency INTEGER(10)    NOT NULL,
  per_index        INTEGER(5)     NOT NULL,
  age_limit        INTEGER(2)     NOT NULL,
  archive          BOOLEAN        NOT NULL,
  price            DECIMAL(10, 2) NOT NULL,

  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8;

USE mydb;
CREATE TABLE users (
  id           INTEGER                          NOT NULL AUTO_INCREMENT,
  name         VARCHAR(30)                      NOT NULL,
  email        VARCHAR(45)                      NOT NULL,
  password     VARCHAR(20)                      NOT NULL,
  enabled      TINYINT DEFAULT TRUE             NOT NULL,
  registration TIMESTAMP DEFAULT now()          NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE payment (
  id      INTEGER        NOT NULL AUTO_INCREMENT,
  date    TIMESTAMP      NOT NULL,
  user_id INTEGER        NOT NULL,
  price   DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE subscriptions (
  id             INTEGER     NOT NULL AUTO_INCREMENT,
  id_periodicals INTEGER(11) NOT NULL,
  date_start     TIMESTAMP   NOT NULL,
  date_end       TIMESTAMP   NOT NULL,
  user_id        INTEGER(11) NOT NULL,
  payment_id     INTEGER     NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (payment_id) references payment (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE history_price (
  id               INTEGER        NOT NULL AUTO_INCREMENT,
  id_periodicals   INTEGER(11)    NOT NULL,
  name_periodicals VARCHAR(100)   NOT NULL,
  date_start       TIMESTAMP      NOT NULL,
  date_end         TIMESTAMP,
  price            DECIMAL(10, 2) NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id)


)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;


USE mydb;
CREATE TABLE shopping_cart (
  id             INTEGER        NOT NULL AUTO_INCREMENT,
  userId         INTEGER        NOT NULL,
  id_periodicals INTEGER,
  date_start     TIMESTAMP      NOT NULL,
  date_end       TIMESTAMP      NOT NULL,
  countPer       INTEGER        NOT NULL,
  time           INTEGER        not null,
  price          DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id),
  FOREIGN KEY (userId) REFERENCES users (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE roles (
  user_id INTEGER     NOT NULL,
  role    VARCHAR(50) NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON DELETE CASCADE
)

  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
INSERT INTO catalog_periodicals (title, discription, publisher, output_frequency, per_index, age_limit, archive, price)
VALUES
  ('Вяселка', 'Развлекательный журнал', 'unknown', '6', '65234', '6', '0', '1.35'),
  ('СпидИнфо', 'Еженедельная газета', 'unknown', '12', '22354', '18', '0', '3.25'),
  ('Вечерний Витебск', 'Еженедельный обозреватель', 'unknown', '48', '32658', '12', '0', '1.47'),
  ('Секретные исследования', 'фантастическая газета', 'unknown', '24', '58986', '10', '0', '1.29'),
  ('Камсомолка', 'Еженедельная газета', 'unknown', '12', '45896', '15', '0', '2.85'),
  ('Аргументы и Факты', 'Еженедельная газета', 'unknown', '24', '58745', '15', '0', '3.85'),
  ('Витебский курьер', 'Ежедневный обозреватель', 'unknown', '12', '12548', '16', '0', '7.47'),
  ('Работа', 'вакансии и спрос', 'unknown', '48', '26589', '18', '0', '1.29');

USE mydb;
INSERT INTO users (name, email, password) VALUES
  ('User', 'User@ya.ru', 'user'),
  ('Admin', 'Admin@gmail.com', 'admin');


USE mydb;
INSERT INTO roles (role, user_id) VALUES
  ('ROLE_USER', '1'),
  ('ROLE_ADMIN', '2')

