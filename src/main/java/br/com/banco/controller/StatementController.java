package br.com.banco.controller;

import br.com.banco.dto.StatementDTO;
import br.com.banco.service.StatementService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class StatementController {
    private final StatementService statementService;

    public StatementController(StatementService statementService) {
        this.statementService = statementService;
    }

    @GetMapping("/statement/{accountId}")
    public StatementDTO getStatement(@PathVariable("accountId") Long accountId,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                     @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                     @RequestParam(required = false) String operatorName) {
        return statementService.getStatement(accountId, startDate, endDate, operatorName);
    }
}
