package br.com.banco.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.banco.model.Transferencia;
import br.com.banco.service.TransferenciaService;

@RestController
@RequestMapping("/api/transferencias")
public class TransferenciaController {
    
    @Autowired
    private TransferenciaService transferenciaService;
    
    @GetMapping
    public Page<Transferencia> getAllCars(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "4") int size
    ) {

        // Se o valor de 'page' for menor que 1, define automaticamente como 1.
        if (page < 1) {
            page = 1;
        }
        
        return transferenciaService.findAllPageable(PageRequest.of(page - 1, size));
    }

    // @GetMapping("/search")
    // public Page<Transferencia> searchTransferencias(
    //         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataInicio,
    //         @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataFim,
    //         @RequestParam(required = false) String nomeOperador,
    //         @RequestParam(defaultValue = "1") int page,
    //         @RequestParam(defaultValue = "10") int size
    // ) {
    //     // Se o valor de 'page' for menor que 1, define automaticamente como 1.
    //     if (page < 1) {
    //         page = 1;
    //     }
        
    //     Pageable pageable = PageRequest.of(page - 1, size);

    //     // Verifica quais parâmetros de pesquisa foram fornecidos e chama o método apropriado do serviço.
    //     if (dataInicio != null && dataFim != null && nomeOperador != null) {
    //         return transferenciaService.searchTransferenciasPorDataEOperador(dataInicio, dataFim, nomeOperador, pageable);
    //     } else if (dataInicio != null && dataFim != null) {
    //         return transferenciaService.searchTransferenciasPorData(dataInicio, dataFim, pageable);
    //     } else if (nomeOperador != null) {
    //         return transferenciaService.searchTransferenciasPorOperador(nomeOperador, pageable);
    //     } else {
    //         return transferenciaService.getAllTransferencias(pageable);
    //     }
    // }
}
