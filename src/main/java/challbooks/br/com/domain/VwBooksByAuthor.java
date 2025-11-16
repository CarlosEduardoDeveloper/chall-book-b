package challbooks.br.com.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "vw_books_by_author")
@Getter
@Setter
public class VwBooksByAuthor {

    @Id
    @Column(name = "book_id")
    private Long bookId;

    private Long authorId;
    private String authorName;
    private String bookTitle;
    private String publisher;
    private Integer edition;
    private String publicationYear;
    private BigDecimal price;
    private Long subjectId;
    private String subjectDescription;
}
