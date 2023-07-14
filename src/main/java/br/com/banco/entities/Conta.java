package br.com.banco.entities;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConta;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;

    public Conta (int idConta, String nomeResponsavel) {
        this.idConta = idConta;
        this.nomeResponsavel = nomeResponsavel;
    }

    public int getIdConta() {
        return idConta;
    }
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
}
