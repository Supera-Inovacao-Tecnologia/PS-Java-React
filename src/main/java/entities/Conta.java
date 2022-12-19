package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity( name = "conta")
public class Conta {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_conta;

    @Column(name = "nome_responsavel", length = 50)
    private String nomeResponsavel;

    @OneToMany(mappedBy = "conta")
    @JsonIgnore
    private Set<Transferencia> transferencias;
}
