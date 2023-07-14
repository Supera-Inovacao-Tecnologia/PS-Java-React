package br.com.banco.controllers;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/banco/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @GetMapping
    public ResponseEntity<List<Transferencia>> getAllTransferencia() {


        return ResponseEntity.status(HttpStatus.OK).body(transferenciaService.findAllTransferencia());
    }

    @PostMapping
    public ResponseEntity<Transferencia> addTransferencia(@RequestBody Transferencia transferencia) {
        if (transferencia.getNomeOperadorTransacao() == null){


            transferencia.setNomeOperadorTransacao(transferencia.getConta().getNomeResponsavel());
        }else if (transferencia.getNomeOperadorTransacao() != transferencia.getConta().getNomeResponsavel()){
            transferencia.setNomeOperadorTransacao(transferencia.getConta().getNomeResponsavel());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(transferenciaService.saveTransferencia(transferencia));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Transferencia> getByIdTransferencia(@PathVariable Long id) {
        Optional<Transferencia> transferenciaOptional = transferenciaService.findByIdTransferencia(id);

        if (transferenciaOptional.isPresent()) {
            Transferencia transferencia = transferenciaOptional.get();
            return ResponseEntity.status(HttpStatus.OK).body(transferencia);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}