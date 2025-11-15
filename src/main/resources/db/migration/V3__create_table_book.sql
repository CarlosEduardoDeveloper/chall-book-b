CREATE TABLE livro (
                       cod_l SERIAL PRIMARY KEY,
                       titulo VARCHAR(40) NOT NULL,
                       editora VARCHAR(40) NOT NULL,
                       edicao INTEGER,
                       ano_publicacao VARCHAR(4),
                       preco DECIMAL(10,2)
);

CREATE INDEX idx_livro_titulo ON livro(titulo);
CREATE INDEX idx_livro_editora ON livro(editora);