USE mydb;
CREATE TABLE catalog_periodicals (
  id          INTEGER        NOT NULL AUTO_INCREMENT,
  title       VARCHAR(50)    NOT NULL,
  discription VARCHAR(100)   NOT NULL,
  price       DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE users (
  id           INTEGER                 NOT NULL AUTO_INCREMENT,
  name         VARCHAR(30)             NOT NULL,
  email        VARCHAR(45)             NOT NULL,
  password     VARCHAR(20)             NOT NULL,
  enabled      TINYINT(0)              NOT NULL,
  role         VARCHAR(5)              NOT NULL,
  registration TIMESTAMP DEFAULT now() NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE payment (
  id      INTEGER              NOT NULL AUTO_INCREMENT,
  date    DATE               NOT NULL,
  user_id INTEGER              NOT NULL,
  price   DECIMAL(10, 2)       NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE subscriptions (
  id               INTEGER     NOT NULL AUTO_INCREMENT,
  id_periodicals   INTEGER(11) NOT NULL,
  name_periodicals VARCHAR(100) NOT NULL,
  date_start       DATE NOT NULL,
  date_end         DATE  NOT NULL,
  user_id          INTEGER(11)  NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id),
  FOREIGN KEY (user_id) REFERENCES  users (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE subscriptions (
  id               INTEGER      NOT NULL AUTO_INCREMENT,
  id_periodicals   INTEGER(11)  NOT NULL,
  name_periodicals VARCHAR(100) NOT NULL,
  date_start       DATE         NOT NULL,
  date_end         DATE         NOT NULL,
  price          DECIMAL(10,2)  NOT NULL,

  PRIMARY KEY (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id)

);

