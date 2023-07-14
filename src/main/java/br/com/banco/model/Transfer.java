package br.com.banco.model;

import br.com.banco.utils.TransferType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "transferencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_transferencia")
    private LocalDateTime transferDate;

    @Column(name = "valor")
    private BigDecimal value;

    @Column(name = "nome_operador_transacao")
    private String operatorName;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TransferType type;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Account account;
}
