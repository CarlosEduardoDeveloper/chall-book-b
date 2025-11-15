package challbooks.br.com.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        int status,
        String message,
        LocalDateTime timestamp,
        List<FieldErrorDetail> errors
) {}