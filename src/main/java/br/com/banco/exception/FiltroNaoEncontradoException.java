package br.com.banco.exception;

public class FiltroNaoEncontradoException extends RuntimeException {
    public FiltroNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
