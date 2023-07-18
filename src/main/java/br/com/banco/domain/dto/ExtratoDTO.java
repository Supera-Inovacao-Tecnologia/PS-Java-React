package br.com.banco.domain.dto;

import br.com.banco.domain.entity.Transferencia;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ExtratoDTO {
    @JsonProperty("saldo_total")
    private Double saldoTotal;
    @JsonProperty("saldo_periodo")
    private Double saldoPeriodo;
    private List<TransferenciaDTO> transferencias;
}
