package br.com.banco;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;
import br.com.banco.services.TransferenciaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransferenciaServiceTest {

    @Mock
    private TransferenciaRepository repository;

    @InjectMocks
    private TransferenciaService service;

    public TransferenciaServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void buscarTransferencias_Todas_DeveRetornarTransferenciasCorretas() {
        
    }

    @Test
    public void buscarTransferencias_PorConta_DeveRetornarTransferenciasCorretas() {
        
    }

    @Test
    public void buscarTransferencias_PorMesAno_DeveRetornarTransferenciasCorretas() {
        
    }

    @Test
    public void buscarTransferencias_PorNomeOperador_DeveRetornarTransferenciasCorretas() {
        
    }

    @Test
    public void buscarTransferencias_PorNomeOperadorEMesAno_DeveRetornarTransferenciasCorretas() {
        
    }
}
