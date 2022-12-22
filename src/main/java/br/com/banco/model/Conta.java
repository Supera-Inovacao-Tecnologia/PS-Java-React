package br.com.banco.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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

    @Column(name = "agencia", unique = true)
    @NotNull
    @Size(min = 1, max = 9999)
    private Integer agencia;

    @Column(name = "numero", unique = true)
    @NotNull
    @Size(min = 1111, max = 99999999)
    private Integer numero;

    @Column(name = "nome_responsavel")
    private String nomeResponsavel;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "saldo", columnDefinition = "DECIMAL(20,2) DEFAULT 0.00")
    private Double saldo;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao;

    public Conta(String nomeResponsavel, int agencia, int conta, String email) {
        this.nomeResponsavel = nomeResponsavel;
        this.agencia = agencia;
        this.numero = conta;
        this.saldo = 0.00;
        this.email = email;
        this.dataDeCriacao = LocalDateTime.now();
    }

}
