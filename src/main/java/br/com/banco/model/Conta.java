package br.com.banco.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Table(name = "conta")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "agencia")
    @NotNull
    @Size(min = 1, max = 9999)
    private Integer agencia;

    @Column(name = "numero")
    @NotNull
    @Size(min = 1111, max = 99999999)
    private Integer numero;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "saldo", columnDefinition = "DECIMAL(20,2) DEFAULT 0.00")
    private Double saldo;

    @Column(name = "data_de_criacao")
    private LocalDate dataDeCriacao;

    public Conta(String nomeResponsavel, int agencia, int conta) {
        this.nomeResponsavel = nomeResponsavel;
        this.agencia = agencia;
        this.numero = conta;
        this.saldo = 0.00;
        this.dataDeCriacao = LocalDate.now();
    }

}
