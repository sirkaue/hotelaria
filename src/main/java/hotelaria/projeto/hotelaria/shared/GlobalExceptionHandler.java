package hotelaria.projeto.hotelaria.shared;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Trata erros de entidade não encontrada (ex: paciente, consulta etc).
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleEntityNotFound(EntityNotFoundException ex,
                                                                     HttpServletRequest request) {
        var response = new ErrorMessageResponse(
                request,
                HttpStatus.NOT_FOUND,
                ex.getMessage() != null ? ex.getMessage() : "Recurso não encontrado."
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Trata erros de validação de campos (Bean Validation).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex,
                                                                          HttpServletRequest request) {

        List<FieldErrorResponse> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldErrorResponse(err.getField(), err.getDefaultMessage()))
                .collect(Collectors.toList());

        var response = new ValidationErrorResponse(
                request,
                HttpStatus.BAD_REQUEST,
                fieldErrors
        );

        return ResponseEntity.badRequest().body(response);
    }

    /**
     * Trata violação de integridade de dados (ex: CPF duplicado, e-mail único etc).
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessageResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex,
                                                                             HttpServletRequest request) {
        var response = new ErrorMessageResponse(
                request,
                HttpStatus.CONFLICT,
                ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    /**
     * Trata erros genéricos não mapeados.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessageResponse> handleGenericErrors(Exception ex,
                                                                    HttpServletRequest request) {
        var response = new ErrorMessageResponse(
                request,
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Erro interno no servidor. " + (ex.getMessage() != null ? ex.getMessage() : "")
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
