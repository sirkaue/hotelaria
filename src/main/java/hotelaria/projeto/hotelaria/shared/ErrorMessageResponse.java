package hotelaria.projeto.hotelaria.shared;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessageResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        String method) {

    public ErrorMessageResponse(HttpServletRequest request, HttpStatus status, String message) {
        this(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI(),
                request.getMethod());
    }
}

