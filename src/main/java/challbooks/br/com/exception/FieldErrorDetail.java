package challbooks.br.com.exception;

public record FieldErrorDetail(
        String field,
        String message
) {}
