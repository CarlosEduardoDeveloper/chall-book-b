CREATE TABLE livro_assunto (
                               livro_cod_l INTEGER NOT NULL,
                               assunto_cod_as INTEGER NOT NULL,
                               PRIMARY KEY (livro_cod_l, assunto_cod_as),
                               CONSTRAINT fk_livro_assunto_livro
                                   FOREIGN KEY (livro_cod_l)
                                       REFERENCES livro(cod_l)
                                       ON DELETE CASCADE,
                               CONSTRAINT fk_livro_assunto_assunto
                                   FOREIGN KEY (assunto_cod_as)
                                       REFERENCES assunto(cod_as)
                                       ON DELETE CASCADE
);

CREATE INDEX idx_livro_assunto_livro ON livro_assunto(livro_cod_l);
CREATE INDEX idx_livro_assunto_assunto ON livro_assunto(assunto_cod_as);