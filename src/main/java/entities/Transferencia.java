package entities;

import entities.Enum.Tipos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "transferencia")
public class Transferencia {


    @Id
    private Long id;

    //    @NotNull
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private LocalDate dataTransferencia;

    //    @NotNull
    @Column(name = "valor", precision = 20, scale = 2)
    private BigDecimal valor;

    //    @NotNull
    @Column(name = "tipo")
    private Tipos tipo;

    //    @NotNull
    @Column(name = "nome_operador_transacao", length = 50)
    private String nomeOperadorTransacao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}