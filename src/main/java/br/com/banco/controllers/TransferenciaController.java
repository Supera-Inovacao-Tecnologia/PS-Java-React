package br.com.banco.controllers;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.services.ContaService;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/banco/")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;
    @Autowired
    private ContaService contaService;

    @GetMapping("/buscarTransferencias")
    public ResponseEntity<List<Transferencia>> getAllTransferencia(
            @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOne,
            @RequestParam(value = "dataFim", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTwo,
            @RequestParam(value = "nomeOperadorTransacao", required = false) String nome,
            @RequestParam(value = "numeroConta", required = false) Long numeroConta) {

        List<Transferencia> transferencias;

        if (numeroConta != null) {
            transferencias = transferenciaService.getTransferenciasByNumeroConta(numeroConta);
        } else if (dateOne == null && dateTwo == null && nome == null) {
            transferencias = transferenciaService.findAllTransferencia();
        } else if (dateOne != null && dateTwo != null) {
            LocalDateTime dateTimeOne = dateOne.atStartOfDay();
            LocalDateTime dateTimeTwo = dateTwo.atStartOfDay();
            transferencias = transferenciaService.getTransferenciasBetweenDates(dateTimeOne, dateTimeTwo);
        } else if (nome != null) {
            transferencias = transferenciaService.getTransferenciasOperador(nome);
        } else {
            LocalDateTime dateTimeOne = dateOne != null ? dateOne.atStartOfDay() : LocalDateTime.MIN;
            LocalDateTime dateTimeTwo = dateTwo != null ? dateTwo.atStartOfDay() : LocalDateTime.MAX;
            transferencias = transferenciaService.getTransferenciaWhitAllFilters(dateTimeOne, dateTimeTwo, nome);
        }


        return ResponseEntity.ok(transferencias);
    }
}




