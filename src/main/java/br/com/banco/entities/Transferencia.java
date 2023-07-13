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
    private BigDecimal valor;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "conta_id", nullable = false)
    private Conta conta;

    public int getId() {
        return id;
    }
    public Timestamp getDataTransferencia() {
        return dataTransferencia;
    }
    public BigDecimal getValor() {
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
