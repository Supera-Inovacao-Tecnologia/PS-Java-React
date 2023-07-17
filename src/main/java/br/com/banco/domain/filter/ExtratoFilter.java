package br.com.banco.domain.filter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ExtratoFilter {
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private String operadorTransacionado;
}
