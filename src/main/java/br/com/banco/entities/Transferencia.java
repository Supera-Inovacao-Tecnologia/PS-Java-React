package br.com.banco.entities;

import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.*;

@Entity
@Table(name = "transferencia")
public class Transferencia {
    @Id
    private int id;
    private Timestamp dataTransferencia;
    private BigDecimal valor;
    private String tipo;
    private String nomeOperadorTransacao;
    private int contaId;

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
    public int getContaId() {
        return contaId;
    }
}
