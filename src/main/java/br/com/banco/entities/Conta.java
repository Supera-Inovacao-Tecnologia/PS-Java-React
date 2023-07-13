package br.com.banco.entities;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    private int id_conta;
    private String nome_responsavel;

    public int getAccountId() {
        return id_conta;
    }
    public String getClientName() {
        return nome_responsavel;
    }
}
