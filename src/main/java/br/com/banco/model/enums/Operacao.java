package br.com.banco.model.enums;

public enum Operacao {

    DEPOSITO(1, "Deposito"),
    SAQUE(2, "Saque"),
    TRANSFERENCIA(3, "Transferencia");

    private int cod;

    private String descricao;

    Operacao(int id, String description) {
        this.cod = id;
        this.descricao = description;
    }

    public int getCod() {
        return cod;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Operacao toEnum(Integer cod) {
        if (cod == null) {
            return null;
        }
        for (Operacao x : Operacao.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Invalid ID" + cod);
    }
}
