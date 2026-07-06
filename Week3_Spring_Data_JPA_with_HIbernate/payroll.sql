CREATE DATABASE IF NOT EXISTS ormlearn;
USE ormlearn;

DROP TABLE IF EXISTS employee_skill;
DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS skill;
DROP TABLE IF EXISTS department;

CREATE TABLE department(
 dp_id INT AUTO_INCREMENT PRIMARY KEY,
 dp_name VARCHAR(50)
);

CREATE TABLE employee(
 em_id INT AUTO_INCREMENT PRIMARY KEY,
 em_name VARCHAR(50),
 em_salary DOUBLE,
 em_permanent BOOLEAN,
 em_date_of_birth DATE,
 em_dp_id INT,
 FOREIGN KEY (em_dp_id) REFERENCES department(dp_id)
);

CREATE TABLE skill(
 sk_id INT AUTO_INCREMENT PRIMARY KEY,
 sk_name VARCHAR(50)
);

CREATE TABLE employee_skill(
 es_em_id INT,
 es_sk_id INT,
 PRIMARY KEY(es_em_id,es_sk_id),
 FOREIGN KEY(es_em_id) REFERENCES employee(em_id),
 FOREIGN KEY(es_sk_id) REFERENCES skill(sk_id)
);

INSERT INTO department(dp_name) VALUES
('IT'),('HR'),('Finance');

INSERT INTO employee(em_name,em_salary,em_permanent,em_date_of_birth,em_dp_id) VALUES
('John',55000,1,'1998-04-12',1),
('Alice',62000,1,'1997-09-21',1),
('Bob',45000,0,'1999-01-15',2),
('David',70000,1,'1995-11-03',3);

INSERT INTO skill(sk_name) VALUES
('Java'),('Spring Boot'),('SQL'),('React');

INSERT INTO employee_skill VALUES
(1,1),(1,2),(1,3),
(2,1),(2,4),
(3,3),
(4,2),(4,3);
