DROP table IF EXISTS mydb;

CREATE TABLE catalog_periodicals (
  id               bigserial      NOT NULL primary key,
  title            VARCHAR(50)    NOT NULL,
  discription      VARCHAR(100)   NOT NULL,
  publisher        VARCHAR(50)    NOT NULL,
  output_frequency INTEGER        NOT NULL,
  per_index        INTEGER        NOT NULL,
  age_limit        INTEGER        NOT NULL,
  archive          BOOLEAN        NOT NULL,
  price            DECIMAL(10, 2) NOT NULL


);

CREATE TABLE users (
  id           bigserial                        NOT NULL primary key,
  name         VARCHAR(30)                      NOT NULL,
  email        VARCHAR(45)                      NOT NULL,
  password     VARCHAR(20)                      NOT NULL,
  enabled      boolean DEFAULT TRUE             NOT NULL,
  registration TIMESTAMP DEFAULT now()          NOT NULL

);


CREATE TABLE payment (
  id      bigserial      NOT NULL primary key,
  date    TIMESTAMP      NOT NULL,
  user_id INTEGER        NOT NULL,
  price   DECIMAL(10, 2) NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE subscriptions (
  id             bigserial NOT NULL primary key,
  id_periodicals INTEGER   NOT NULL,
  date_start     TIMESTAMP      NOT NULL,
  date_end       TIMESTAMP      NOT NULL,
  user_id        INTEGER   NOT NULL,
  payment_id     INTEGER     NOT NULL,

  FOREIGN KEY (payment_id) references payment (id),
  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id),
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE history_price (
  id               bigserial      NOT NULL primary key,
  id_periodicals   INTEGER        NOT NULL,
  name_periodicals VARCHAR(100)   NOT NULL,
  date_start       TIMESTAMP           NOT NULL,
  date_end         TIMESTAMP,
  price            DECIMAL(10, 2) NOT NULL,

  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id)


);


CREATE TABLE shopping_cart (
  id             BIGSERIAL      NOT NULL,
  userId         INTEGER        NOT NULL,
  id_periodicals INTEGER,
  date_start     TIMESTAMP           NOT NULL,
  date_end       TIMESTAMP           NOT NULL,
  countPer       INTEGER        NOT NULL,
  time           INTEGER        not null,
  price          DECIMAL(10, 2) NOT NULL,

  FOREIGN KEY (id_periodicals) REFERENCES catalog_periodicals (id),
  FOREIGN KEY (userId) REFERENCES users (id)
);

CREATE TABLE roles (
  user_id INTEGER     NOT NULL,
  role    VARCHAR(50) NOT NULL,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


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


INSERT INTO users (name, email, password) VALUES
  ('User', 'User@ya.ru', 'user'),
  ('Admin', 'Admin@gmail.com', 'admin');


INSERT INTO roles (role, user_id) VALUES
  ('ROLE_USER', '1'),
  ('ROLE_ADMIN', '2')

