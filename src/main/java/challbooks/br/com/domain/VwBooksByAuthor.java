package challbooks.br.com.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "vw_books_by_author")
@IdClass(VwBooksByAuthorId.class)
@Getter
@Setter
public class VwBooksByAuthor {

    @Id
    @Column(name = "book_id")
    private Long bookId;

    @Id
    @Column(name = "author_id")
    private Long authorId;

    @Id
    @Column(name = "subject_id")
    private Long subjectId;

    private String authorName;
    private String bookTitle;
    private String publisher;
    private Integer edition;
    private String publicationYear;
    private BigDecimal price;
    private String subjectDescription;
}