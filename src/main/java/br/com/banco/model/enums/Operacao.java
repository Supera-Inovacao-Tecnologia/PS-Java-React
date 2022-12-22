package br.com.banco.model.enums;

/**
 * Define the tipe of transaction
 */
public enum Operacao {

    DEPOSITO(1, "Deposito"),
    SAQUE(2, "Saque"),
    TRANSFERENCIA(3, "Transferencia");

    private final int cod;

    private final String descricao;

    Operacao(int id, String description) {
        this.cod = id;
        this.descricao = description;
    }

}
