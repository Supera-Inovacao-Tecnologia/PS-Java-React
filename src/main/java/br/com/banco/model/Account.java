package br.com.banco.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "conta")
@Data
@Builder
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long id;

    @Column(name = "nome_responsavel")
    private String owner;
}
