CREATE OR REPLACE VIEW vw_books_by_author AS
SELECT
    a.cod_au AS author_id,
    a.nome AS author_name,

    l.cod_l AS book_id,
    l.titulo AS book_title,
    l.editora AS publisher,
    l.edicao AS edition,
    l.ano_publicacao AS publication_year,
    l.preco AS price,

    s.cod_as AS subject_id,
    s.descricao AS subject_description
FROM autor a
         JOIN livro_autor la ON la.autor_cod_au = a.cod_au
         JOIN livro l ON l.cod_l = la.livro_cod_l
         JOIN livro_assunto ls ON ls.livro_cod_l = l.cod_l
         JOIN assunto s ON s.cod_as = ls.assunto_cod_as
ORDER BY a.nome, l.titulo, s.descricao;