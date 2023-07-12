package br.com.banco.resources;

import br.com.banco.model.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaResource {

    @Autowired
    private TransferenciaService transferenciaService;

    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ResponseEntity<Page<Transferencia>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "filterDataInicio", defaultValue = "") String filterDataInicio,
                                                        @RequestParam(value = "filterDataFim", defaultValue = "") String filterDataFim,
                                                        @RequestParam(value = "filterNomeOperadorTransacao", defaultValue = "") String filterNomeOperadorTransacao,
                                                        @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
                                                        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy,
                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Transferencia> categorias = transferenciaService.findPage(page, linesPerPage, orderBy, direction,filterDataInicio,filterDataFim,filterNomeOperadorTransacao);
        return ResponseEntity.ok().body(categorias);
    }

}
