package br.com.banco.exception;

import br.com.banco.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> handlePassengerNotFoundException() {
        ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO("Conta não encontrada", 404);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponse);
    }
}
