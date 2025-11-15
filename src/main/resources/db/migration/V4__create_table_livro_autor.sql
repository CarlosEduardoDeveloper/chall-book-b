CREATE TABLE livro_autor (
                             livro_cod_l INTEGER NOT NULL,
                             autor_cod_au INTEGER NOT NULL,
                             PRIMARY KEY (livro_cod_l, autor_cod_au),
                             CONSTRAINT fk_livro_autor_livro
                                 FOREIGN KEY (livro_cod_l)
                                     REFERENCES livro(cod_l)
                                     ON DELETE CASCADE,
                             CONSTRAINT fk_livro_autor_autor
                                 FOREIGN KEY (autor_cod_au)
                                     REFERENCES autor(cod_au)
                                     ON DELETE CASCADE
);

CREATE INDEX idx_livro_autor_livro ON livro_autor(livro_cod_l);
CREATE INDEX idx_livro_autor_autor ON livro_autor(autor_cod_au);