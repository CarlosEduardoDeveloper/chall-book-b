package challbooks.br.com.domain.dto.response;

import java.math.BigDecimal;
import java.util.Set;

public record BookResponseDTO(
        Long id,
        String title,
        String publisher,
        Integer edition,
        String publicationYear,
        BigDecimal price,
        Set<AuthorResponseDTO> authors,
        Set<SubjectResponseDTO> subjects
) {}