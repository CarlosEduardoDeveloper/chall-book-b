package challbooks.br.com.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VwBooksByAuthorId implements Serializable {
    private Long bookId;
    private Long authorId;
    private Long subjectId;
}