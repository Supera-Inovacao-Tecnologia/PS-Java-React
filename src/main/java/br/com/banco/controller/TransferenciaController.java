package br.com.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {
    
    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAll(){
        List<Transferencia> transferencias = transferenciaService.findAll();
        return ResponseEntity.ok().body(transferencias);
    }
}
