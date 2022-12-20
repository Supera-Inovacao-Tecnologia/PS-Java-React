package br.com.banco.controller;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transferencia")
public class ContaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<List<Transferencia>> findByIdConta(@PathVariable Long id) {
        List<Transferencia> lista =  transferenciaService.findAllByIdConta(id);
        return ResponseEntity.ok(lista);
    }

}
