package br.com.banco.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;

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
    public ResponseEntity<?> tratarErroSaldo() {
        return ResponseEntity.badRequest().build();
    }

}
