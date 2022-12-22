package br.com.banco.model;

import br.com.banco.model.enums.Operacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Entity representing Transactions
 */
@Entity
@Table(name = "transferencia")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transferencia")
    private LocalDateTime dataTransferencia;

    @Column(name = "valor", nullable = false, columnDefinition = "DECIMAL(20,2)")
    @NotNull
    private Double valor;

    @Enumerated(value = EnumType.STRING)
    @NotNull
    private Operacao tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne
    private Conta conta;

}
