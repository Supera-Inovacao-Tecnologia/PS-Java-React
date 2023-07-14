package br.com.banco.controllers;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.services.ContaService;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<Transferencia>> getAllTransferencia() {
        return ResponseEntity.status(HttpStatus.OK).body(transferenciaService.findAllTransferencia());
    }


    @PostMapping("/cadastrarTransferencias")
    public ResponseEntity<Objects> addTransferencia(@RequestBody Transferencia transferencia, @RequestBody Conta conta) {


        transferenciaService.saveTransferencia(transferencia);
        contaService.saveConta(conta);
        return ResponseEntity.ok().build();

    }



}