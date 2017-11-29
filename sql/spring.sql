DROP DATABASE IF EXISTS spring;
CREATE DATABASE spring DEFAULT CHARACTER SET utf8;
USE spring;

CREATE TABLE user (
	user_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	user_name VARCHAR(30),
	credits INT,
	password VARCHAR(32),
	last_login_datetime DATETIME,
	last_ip VARCHAR(23)
)ENGINE=InnoDB;

CREATE TABLE login_log (
	log_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	user_id INT,
	login_ip VARCHAR(23),
	login_datetime DATETIME
)ENGINE=InnoDB;