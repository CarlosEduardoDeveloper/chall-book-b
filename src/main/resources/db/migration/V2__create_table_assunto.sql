CREATE TABLE assunto (
                           cod_as SERIAL PRIMARY KEY,
                           descricao VARCHAR(20) NOT NULL
);

CREATE INDEX idx_assunto_descricao ON assunto(descricao);