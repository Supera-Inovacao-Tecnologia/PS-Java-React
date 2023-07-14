package br.com.banco.dto;

import br.com.banco.model.Transfer;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class StatementDTO {
    private String accountOwner;
    private List<Transfer> transfers;
    private BigDecimal totalBalance;
    private BigDecimal balanceInPeriod;
}
