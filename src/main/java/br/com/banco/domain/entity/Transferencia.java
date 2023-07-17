package br.com.banco.domain.entity;

import br.com.banco.domain.entity.Conta;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "transferencia")
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "data_transferencia")
    private LocalDateTime dataTransferencia;
    private Double valor;
    private String tipo;
    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;
    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
