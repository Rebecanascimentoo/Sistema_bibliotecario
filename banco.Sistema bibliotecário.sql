CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE livros (
    id INT PRIMARY KEY,
    titulo VARCHAR(255),
    autor VARCHAR(255),
    genero VARCHAR(100)
);

INSERT INTO livros VALUES (1, 'crepúsculo', 'Stephenie Meyer', ' Fantasy romance');