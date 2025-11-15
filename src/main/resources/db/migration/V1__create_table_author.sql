CREATE TABLE autor (
                         cod_au SERIAL PRIMARY KEY,
                         nome VARCHAR(40) NOT NULL
);

CREATE INDEX idx_autor_nome ON autor(nome);

