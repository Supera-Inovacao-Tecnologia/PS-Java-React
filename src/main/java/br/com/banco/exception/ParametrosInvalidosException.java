package br.com.banco.exception;

public class ParametrosInvalidosException extends RuntimeException {
    public ParametrosInvalidosException(String mensagem) {
        super(mensagem);
    }   
}
