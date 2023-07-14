package br.com.banco.controller;

import br.com.banco.services.TransferenciaService;
import br.com.banco.entities.Transferencia;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    @Autowired
    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Transferencia> buscarTransferencias(
        @RequestParam(required = false) Integer contaId, 
        @RequestParam(required = false) String nomeOperador,
        @RequestParam(required = false) @DateTimeFormat(pattern = "MM") Integer mes,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy") Integer ano
    ) {
        return service.buscarTransferencias(contaId, nomeOperador, mes, ano);
    }
}
