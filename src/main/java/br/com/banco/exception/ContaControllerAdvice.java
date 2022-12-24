package br.com.banco.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Exception handler
 */
@RestControllerAdvice
public class ContaControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<?> tratarErroSaldo(SaldoInsuficienteException ex) {
        var erros = ex.getMessage();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> tratarErroDuplicataDb() {
        var erros = "Entity already exists";
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarArgumentoInvalido(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros);
    }
}
