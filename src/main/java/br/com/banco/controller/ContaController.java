package br.com.banco.controller;


import br.com.banco.model.Conta;
import br.com.banco.model.dto.ContaDTO;
import br.com.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private ContaService contaService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> cadastrar(@RequestBody ContaDTO dto) {
        Conta conta = contaService.fromDto(dto);

        contaService.inserir(conta);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/depositar/{id}/{valor}")
    public ResponseEntity<Void> depositar(@PathVariable Long id,  @PathVariable Double valor) {
        Conta conta = contaService.findById(id);

        contaService.depositar(conta, valor);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/sacar/{id}/{valor}")
    public ResponseEntity<Void> sacar(@PathVariable Long id, @PathVariable Double valor) {
        Conta conta = contaService.findById(id);

        contaService.sacar(conta, valor);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/{idOrigem}/{valor}/{idDestino}")
    public ResponseEntity<Void> transferir(@PathVariable Long idOrigem,
                                           @PathVariable Double valor, @PathVariable Long idDestino) {

        Conta origem = contaService.findById(idOrigem);
        Conta destino = contaService.findById(idDestino);

        contaService.transferir(origem, destino, valor);

        return ResponseEntity.ok().build();

    }

}
