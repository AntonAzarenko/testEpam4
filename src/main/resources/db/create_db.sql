DROP DATABASE IF EXISTS `mydb`;

CREATE DATABASE `mydb` DEFAULT CHARACTER SET utf8;

GRANT SELECT,INSERT,UPDATE,DELETE
ON `mydb`.*
TO app@'%'
IDENTIFIED BY 'app_pw';



