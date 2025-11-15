package challbooks.br.com.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthorRequestDTO(
        @NotBlank(message = "Name is required.")
        String name
) {}