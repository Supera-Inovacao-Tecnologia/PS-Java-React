package br.com.banco.exception;

public class ContaNaoEncontradaException extends RuntimeException {

    public ContaNaoEncontradaException(String message) {
        super(message);
    }

    public ContaNaoEncontradaException(String message, Throwable cause) {
        super(message, cause);
    }

}
