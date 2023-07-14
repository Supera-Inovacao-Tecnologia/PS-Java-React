package br.com.banco.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Table(name = "transferencia")
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "data_transferencia", nullable = false)
    private Timestamp dataTransferencia;

    @Column(name = "valor", nullable = false)
    private double valor;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @Column(name = "conta_id")
    private int contaId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public Transferencia(
        int id, 
        Timestamp dataTransferencia,
        double valor, 
        String tipo, 
        String nomeOperadorTransacao, 
        int contaId, 
        Conta conta
    ) {
        this.id = id;
        this.dataTransferencia = dataTransferencia;
        this.valor = valor;
        this.tipo = tipo;
        this.nomeOperadorTransacao = nomeOperadorTransacao;
        this.contaId = contaId;
        this.conta = conta;
    }

    public int getId() {
        return id;
    }
    public Timestamp getDataTransferencia() {
        return dataTransferencia;
    }
    public double getValor() {
        return valor;
    }
    public String getTipo() {
        return tipo;
    }
    public String getNomeOperadorTransacao() {
        return nomeOperadorTransacao;
    }
    public Conta getConta() {
        return conta;
    }
}
