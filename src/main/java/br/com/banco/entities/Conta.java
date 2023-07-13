package br.com.banco.entities;

import javax.persistence.*;

@Entity
@Table(name = "conta")
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private int idConta;

    @Column(name = "nome_responsavel", nullable = false)
    private String nomeResponsavel;

    public int getIdConta() {
        return idConta;
    }
    public String getNomeResponsavel() {
        return nomeResponsavel;
    }
}
