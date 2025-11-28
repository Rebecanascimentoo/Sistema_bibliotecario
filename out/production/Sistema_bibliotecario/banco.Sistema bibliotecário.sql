CREATE DATABASE biblioteca;
USE biblioteca;

CREATE TABLE livros (
                        id INT PRIMARY KEY,
                        titulo VARCHAR(255),
                        autor VARCHAR(255),
                        genero VARCHAR(100)
);

CREATE TABLE revistas (
                          id INT PRIMARY KEY,
                          titulo VARCHAR(255),
                          autor VARCHAR(255),
                          edicao INT
);

CREATE TABLE midias (
                        id INT PRIMARY KEY,
                        titulo VARCHAR(255),
                        autor VARCHAR(255),
                        tipo VARCHAR(100)
);
