DROP VIEW IF EXISTS vw_books_by_author;

CREATE OR REPLACE VIEW vw_books_by_author AS
SELECT
    l.cod_l AS book_id,
    l.titulo AS book_title,
    l.editora AS publisher,
    l.edicao AS edition,
    l.ano_publicacao AS publication_year,
    l.preco AS price,

    a.cod_au AS author_id,
    a.nome AS author_name,

    s.cod_as AS subject_id,
    s.descricao AS subject_description
FROM livro l
         INNER JOIN livro_autor la ON la.livro_cod_l = l.cod_l
         INNER JOIN autor a ON a.cod_au = la.autor_cod_au
         LEFT JOIN livro_assunto ls ON ls.livro_cod_l = l.cod_l
         LEFT JOIN assunto s ON s.cod_as = ls.assunto_cod_as
ORDER BY l.cod_l, a.nome, s.descricao;