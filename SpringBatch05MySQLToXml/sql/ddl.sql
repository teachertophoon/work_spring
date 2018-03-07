DROP TABLE IF EXISTS user;

CREATE TABLE user (
	id INT,
	username VARCHAR(20),
	password VARCHAR(20),
	age INT,
	regdate DATE,
	income DECIMAL(9,2)
);

INSERT INTO user (id, username, password, age, regdate, income)
	VALUES (1, 'USER-1', 'PASSWORD-1', 30, CURDATE(), 1000.50);
INSERT INTO user (id, username, password, age, regdate, income)
	VALUES (2, 'USER-2', 'PASSWORD-2', 25, CURDATE(), 1000.50);
INSERT INTO user (id, username, password, age, regdate, income)
	VALUES (3, 'USER-3', 'PASSWORD-3', 10, CURDATE(), 1000.50);
INSERT INTO user (id, username, password, age, regdate, income)
	VALUES (4, 'USER-4', 'PASSWORD-4', 25, CURDATE(), 1000.50);
INSERT INTO user (id, username, password, age, regdate, income)
	VALUES (5, 'USER-5', 'PASSWORD-5', 40, CURDATE(), 1000.50);
	
SELECT * FROM user;