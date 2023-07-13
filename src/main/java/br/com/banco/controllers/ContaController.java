package br.com.banco.controllers;

import br.com.banco.models.Conta;
import br.com.banco.services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/banco/conta")
@RestController
public class ContaController {

    @Autowired
    private ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> addConta(@RequestBody Conta conta){
        return ResponseEntity.status(HttpStatus.CREATED).body(contaService.saveConta(conta));
    }

    @GetMapping
    public ResponseEntity<List<Conta>> getAllConta(){
        return ResponseEntity.status(HttpStatus.OK).body(contaService.findAllConta());
    }

}
