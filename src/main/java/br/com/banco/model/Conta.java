package br.com.banco.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Conta  implements Serializable {

    @Id
    @Column(name="id_conta")
    private long id;

    @Column(name="nome_responsavel")
    private String nomeResponsavel;

    @JsonIgnore
    @OneToMany(mappedBy = "conta")
    private List<Transferencia> transferencias = new ArrayList<>();

    public Conta() {
    }

    public long getId() {
        return id;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public List<Transferencia> getTransferencia() {
        return this.transferencias;
    }
}
