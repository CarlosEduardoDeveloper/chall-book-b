package challbooks.br.com.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "livro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_l")
    private Long id;

    @Column(name = "titulo", length = 40, nullable = false)
    private String title;

    @Column(name = "editora", length = 40, nullable = false)
    private String publisher;

    @Column(name = "edicao")
    private Integer edition;

    @Column(name = "ano_publicacao", length = 4)
    private String publicationYear;

    @Column(name = "preco", precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_cod_l"),
            inverseJoinColumns = @JoinColumn(name = "autor_cod_au")
    )
    private Set<Author> authors = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "livro_assunto",
            joinColumns = @JoinColumn(name = "livro_cod_l"),
            inverseJoinColumns = @JoinColumn(name = "assunto_cod_as")
    )
    private Set<Subject> subjects = new HashSet<>();
}