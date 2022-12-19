package entities.Enum;

public enum Tipos {

    DEPOSITO("Depósito"),
    TRANSFERENCIA_ENTRADA("Transferência Entrada"),
    TRANSFERENCIA_SAIDA("Transferência Saída"),

    SAQUE("Saque");

    private String descricao;

    Tipos(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao(){ return descricao;}
}
