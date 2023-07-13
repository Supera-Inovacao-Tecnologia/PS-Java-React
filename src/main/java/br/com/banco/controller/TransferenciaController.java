package br.com.banco.controller;

import br.com.banco.services.TransferenciaService;
import br.com.banco.entities.Transferencia;
import br.com.banco.exception.FiltroNaoEncontradoException;
import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.exception.ParametrosInvalidosException;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

    private final TransferenciaService service;

    @Autowired
    public TransferenciaController(TransferenciaService service) {
        this.service = service;
    }

    private static final List<String> parametrosEsperados = Arrays.asList("contaId", "nomeOperador", "mes", "ano");

    @GetMapping
    public List<Transferencia> buscarTransferencias(
        @RequestParam(required = false) Integer contaId, 
        @RequestParam(required = false) String nomeOperador,
        @RequestParam(required = false) @DateTimeFormat(pattern = "MM") Integer mes,
        @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy") Integer ano,
        HttpServletRequest request
    ) {
        List<String> parametrosRecebidos = Collections.list(request.getParameterNames());
        parametrosRecebidos.removeAll(parametrosEsperados);

        if (!parametrosRecebidos.isEmpty()) {
            throw new FiltroNaoEncontradoException("Parâmetros inválidos fornecidos na requisição: " + parametrosRecebidos);
        }

        if ((mes == null && ano != null) || mes != null && ano == null) {
            throw new ParametroDeTempoException("Você não pode passar como parâmetro apenas o mês ou o ano.");
        }

        if (contaId != null && nomeOperador != null) {
            throw new ParametrosInvalidosException("Você não pode passar o id da conta e o nome do operador como parâmetros. Os dois só podem ser passados em conjunto se o mês e o ano forem informados também.");
        }

        if (contaId != null && mes != null && ano != null) {
            throw new ParametrosInvalidosException("Você não pode passar o id da conta e o mês e o ano como parâmetros. Eles só podem ser passados em conjunto se o nome do operador for informado também.");
        }

        return service.buscarTransferencias(contaId, nomeOperador, mes, ano);
    }
}
