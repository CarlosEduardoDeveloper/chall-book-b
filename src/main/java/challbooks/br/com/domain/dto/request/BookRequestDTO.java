package challbooks.br.com.domain.dto.request;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;

public record BookRequestDTO(

        @NotBlank(message = "Title is required.")
        String title,

        @NotBlank(message = "Publisher is required.")
        String publisher,

        Integer edition,

        @Size(max = 4, message = "Publication year must have up to 4 characters.")
        String publicationYear,

        @NotNull(message = "Price is required.")
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal price,

        @NotEmpty(message = "At least one author is required.")
        Set<Long> authorIds,

        @NotEmpty(message = "At least one subject is required.")
        Set<Long> subjectIds
) {}