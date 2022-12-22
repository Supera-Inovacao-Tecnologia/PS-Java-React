package br.com.banco.controller;


import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.dto.ContaDTO;
import br.com.banco.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public ResponseEntity<Void> depositar(@PathVariable Long id, @PathVariable Double valor) {
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Page<Transferencia>> getter(@PathVariable Long id,
                                                      @RequestParam(required = false, defaultValue = "1900-01-01 00:00") String dataInicio,
                                                      @RequestParam(required = false, defaultValue = "2999-12-29 00:00") String dataFim,
                                                      @RequestParam(required = false) String nome,
                                                      Pageable pageable) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime inicio = LocalDateTime.parse(dataInicio, formatter);
        LocalDateTime fim = LocalDateTime.parse(dataFim, formatter);

        if (nome == null) {
            Page<Transferencia> page = contaService.findByPeriodo(id, inicio, fim, pageable);
            return ResponseEntity.ok(page);
        }

        Page<Transferencia> page = contaService.findByOperadorAndPeriod(id, inicio, fim, nome, pageable);

        return ResponseEntity.ok(page);
    }

}
