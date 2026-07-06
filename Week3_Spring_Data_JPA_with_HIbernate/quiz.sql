CREATE DATABASE IF NOT EXISTS ormlearn;
USE ormlearn;

DROP TABLE IF EXISTS attempt_option;
DROP TABLE IF EXISTS attempt_question;
DROP TABLE IF EXISTS attempt;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS question;
DROP TABLE IF EXISTS user;

CREATE TABLE user(
    us_id INT AUTO_INCREMENT PRIMARY KEY,
    us_name VARCHAR(100) NOT NULL
);

CREATE TABLE question(
    qu_id INT AUTO_INCREMENT PRIMARY KEY,
    qu_text VARCHAR(500) NOT NULL,
    qu_score DECIMAL(3,1) NOT NULL
);

CREATE TABLE options(
    op_id INT AUTO_INCREMENT PRIMARY KEY,
    op_qu_id INT,
    op_text VARCHAR(255),
    op_score DECIMAL(3,1),
    FOREIGN KEY(op_qu_id) REFERENCES question(qu_id)
);

CREATE TABLE attempt(
    at_id INT AUTO_INCREMENT PRIMARY KEY,
    at_us_id INT,
    at_date DATETIME,
    FOREIGN KEY(at_us_id) REFERENCES user(us_id)
);

CREATE TABLE attempt_question(
    aq_id INT AUTO_INCREMENT PRIMARY KEY,
    aq_at_id INT,
    aq_qu_id INT,
    FOREIGN KEY(aq_at_id) REFERENCES attempt(at_id),
    FOREIGN KEY(aq_qu_id) REFERENCES question(qu_id)
);

CREATE TABLE attempt_option(
    ao_id INT AUTO_INCREMENT PRIMARY KEY,
    ao_aq_id INT,
    ao_op_id INT,
    selected BOOLEAN,
    FOREIGN KEY(ao_aq_id) REFERENCES attempt_question(aq_id),
    FOREIGN KEY(ao_op_id) REFERENCES options(op_id)
);

INSERT INTO user(us_name) VALUES ('John'),('Alice');

INSERT INTO question(qu_text,qu_score) VALUES
('What is the extension of the HyperText Markup Language file?',1.0),
('What is the maximum heading tag in HTML?',1.0),
('HTML document begins with <html> and ends with </html>.',1.0),
('Choose the right option to store text value in a variable.',1.0);

INSERT INTO options(op_qu_id,op_text,op_score) VALUES
(1,'.xhtm',0),(1,'.ht',0),(1,'.html',1),(1,'.htmx',0),
(2,'5',0),(2,'3',0),(2,'4',0),(2,'6',1),
(3,'false',0),(3,'true',1),
(4,"'John'",0.5),(4,'John',0), (4,'"John"',0.5),(4,'/John/',0);

INSERT INTO attempt(at_us_id,at_date)
VALUES (1,NOW());

INSERT INTO attempt_question(aq_at_id,aq_qu_id)
VALUES (1,1),(1,2),(1,3),(1,4);

INSERT INTO attempt_option(ao_aq_id,ao_op_id,selected)
VALUES
(1,3,TRUE),
(2,8,TRUE),
(3,10,TRUE),
(4,11,TRUE);
