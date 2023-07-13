package br.com.banco.handler;

import br.com.banco.exception.FiltroNaoEncontradoException;
import br.com.banco.entities.RespostaDeErro;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FiltroNaoEncontradoException.class)
    public ResponseEntity<RespostaDeErro> handleFiltroNaoEncontradoException(FiltroNaoEncontradoException ex) {
        RespostaDeErro respostaDeErro = new RespostaDeErro(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(respostaDeErro, HttpStatus.BAD_REQUEST);
    }
}
