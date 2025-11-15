package challbooks.br.com.domain.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SubjectRequestDTO(
        @NotBlank(message = "Description is required.")
        String description
) {}