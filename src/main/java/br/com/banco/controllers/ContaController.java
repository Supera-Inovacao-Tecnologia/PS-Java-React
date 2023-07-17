package br.com.banco.controllers;

import br.com.banco.domain.entity.Conta;
import br.com.banco.domain.filter.ExtratoFilter;
import br.com.banco.repository.TransferenciaRepository;
import br.com.banco.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conta")
public class ContaController {

    private final ContaService contaService;

    @GetMapping("/{idConta}/extrato")
    public ResponseEntity buscarTransferenciasConta(@PathVariable Long idConta,
                                                    @RequestParam(value = "dataInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataInicio,
                                                    @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataFinal,
                                                    @RequestParam(value = "operadorTransacionado", required = false) String operadorTransacionado){

        ExtratoFilter filter = new ExtratoFilter(dataInicio, dataFinal, operadorTransacionado);
        return ResponseEntity.ok().body(contaService.emitirExtratoBancario(idConta, filter));

    }
}
