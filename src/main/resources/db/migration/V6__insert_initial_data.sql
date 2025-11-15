-- =============================
-- AUTORES
-- =============================
INSERT INTO autor (nome) VALUES
                             ('Robert Martin'),
                             ('Martin Fowler'),
                             ('Joshua Bloch'),
                             ('Kent Beck'),
                             ('Eric Evans');

-- =============================
-- ASSUNTOS
-- =============================
INSERT INTO assunto (descricao) VALUES
                                    ('Software'),
                                    ('Arquitetura'),
                                    ('Padrões'),
                                    ('Java'),
                                    ('Testes');

-- =============================
-- LIVROS
-- =============================
INSERT INTO livro (titulo, editora, edicao, ano_publicacao, preco) VALUES
                                                                       ('Clean Code', 'Prentice Hall', 1, '2008', 150.00),
                                                                       ('Refactoring', 'Addison-Wesley', 2, '2019', 200.00),
                                                                       ('Effective Java', 'Addison-Wesley', 3, '2018', 180.00);

-- =============================
-- RELAÇÃO LIVRO x AUTOR
-- =============================
INSERT INTO livro_autor (livro_cod_l, autor_cod_au) VALUES (1, 1);
INSERT INTO livro_autor (livro_cod_l, autor_cod_au) VALUES (2, 2);
INSERT INTO livro_autor (livro_cod_l, autor_cod_au) VALUES (3, 3);

-- =============================
-- RELAÇÃO LIVRO x ASSUNTO
-- =============================
INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES (1, 1);
INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES (1, 5);

INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES (2, 1);
INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES (2, 2);

INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES (3, 4);
INSERT INTO livro_assunto (livro_cod_l, assunto_cod_as) VALUES (3, 3);
