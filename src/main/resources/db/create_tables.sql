USE mydb;
CREATE TABLE catalog_periodicals (
  id               INTEGER        NOT NULL AUTO_INCREMENT,
  title            VARCHAR(50)    NOT NULL,
  type             VARCHAR(50)    NOT NULL,
  output_frequency INTEGER(10)    NOT NULL,
  discription      VARCHAR(100)   NOT NULL,
  price            DECIMAL(10, 2) NOT NULL,

  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET utf8;

USE mydb;
CREATE TABLE users (
  id           INTEGER                 NOT NULL AUTO_INCREMENT,
  name         VARCHAR(30)             NOT NULL,
  email        VARCHAR(45)             NOT NULL,
  password     VARCHAR(20)             NOT NULL,
  enabled      TINYINT(0)              NOT NULL,
  application         VARCHAR(5)              NOT NULL,
  registration TIMESTAMP DEFAULT now() NOT NULL,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

USE mydb;
CREATE TABLE payment (
  id      INTEGER        NOT NULL AUTO_INCREMENT,
  date    DATE           NOT NULL,
  user_id INTEGER        NOT NULL,
  price   DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
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
  user_id          INTEGER(11)  NOT NULL,

  PRIMARY KEY (id),
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
  date_start       DATE           NOT NULL,
  date_end         DATE           NOT NULL,
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
  date_start     DATE           NOT NULL,
  date_end       DATE           NOT NULL,
  countPer       INTEGER        NOT NULL,
  price          DECIMAL(10, 2) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id),
  FOREIGN KEY (userId) REFERENCES users (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET utf8;

use mydb;
INSERT INTO catalog_periodicals (title,  discription,  output_frequency, price) VALUES
  ('Вяселка', 'Развлекательный журнал','1','1.35'),
  ('СпидИнфо','Еженедельная газета','2','3.25'),
  ('Вечерний Витебск','Еженедельный обозреватель','8','1.47'),
  ('Секретные исследования','фантастическая газета','4','1.29'),
  ('Камсомолка','Еженедельная газета','2','2.85'),
  ('Аргументы и Факты','Еженедельная газета','1','3.85'),
  ('Вечерний Витебск','ежедневный обозреватель','2','7.47'),
  ('Работа','вакансии и спрос','8','1.29');

