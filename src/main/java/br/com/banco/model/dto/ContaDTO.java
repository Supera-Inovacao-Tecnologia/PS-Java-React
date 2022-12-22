package br.com.banco.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class ContaDTO {

    private String nome;

    private String email;

    @NotNull
    @Size(min = 1, max = 9999)
    private Integer agencia;

    @NotNull
    @Size(min = 1111, max = 99999999)
    private Integer numero;


    private Double valor;
}
