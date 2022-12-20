package br.com.banco.model;

import br.com.banco.model.enums.Operacao;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "transferencia")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transferencia")
    private LocalDate dataTransferencia;

    @Column(name = "valor", nullable = false, columnDefinition = "DECIMAL(20,2)")
    @NotNull
    private Double valor;

    @Enumerated
    @NotNull
    private Operacao tipo;

    @Column(name = "nome_operador_transacao")
    private String nomeOperadorTransacao;

    @ManyToOne
    private Conta conta;

}
