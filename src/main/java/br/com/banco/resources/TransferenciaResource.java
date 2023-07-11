package br.com.banco.resources;

import br.com.banco.services.TransferenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transferencias")
public class TransferenciaResource {

    @Autowired
    private TransferenciaService transferenciaService;


}
