package br.com.banco.entities;

public class RespostaDeErro {
    private int status;
    private String mensagem;

    public RespostaDeErro(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
    }

    public int getStatus () {
        return status;
    }
    public String getMensagem () {
        return mensagem;
    }
}
