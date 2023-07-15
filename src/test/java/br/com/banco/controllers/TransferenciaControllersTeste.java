package br.com.banco.controllers;

import br.com.banco.models.Transferencia;
import br.com.banco.services.TransferenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = TransferenciaController.class)
public class TransferenciaControllersTeste {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransferenciaService transferenciaService;

    private List<Transferencia> transferenciasEsperadas;

    @BeforeEach
    public void setUp() {
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setId(1L);
        transferencia1.setDataTransferencia(LocalDateTime.now());
        transferencia1.setValor(BigDecimal.valueOf(100));

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setId(2L);
        transferencia2.setDataTransferencia(LocalDateTime.now());
        transferencia2.setValor(BigDecimal.valueOf(200));

        transferenciasEsperadas = Arrays.asList(transferencia1, transferencia2);
    }

    @Test
    public void deveRetornarSucesso_QuandoBuscarTransferencias() throws Exception {
        when(transferenciaService.getAllTransferencias()).thenReturn(transferenciasEsperadas);

        mockMvc.perform(get("/api/banco/buscarTransferencias"))
                .andExpect(status().isOk());
    }
}
