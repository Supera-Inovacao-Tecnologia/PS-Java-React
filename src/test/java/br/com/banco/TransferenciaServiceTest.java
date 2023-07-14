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

    Conta conta1 = new Conta(1, "Fulano");
    Transferencia transferencia1 = new Transferencia(
        1, 
        Timestamp.valueOf(LocalDateTime.parse("2023-01-01 12:00:00+03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))), 
        100.40, 
        "DEPÓSITO", 
        null,
        conta1
    );

    Conta conta2 = new Conta(2, "Sicrano");
    Transferencia transferencia2 = new Transferencia(
        2, 
        Timestamp.valueOf(LocalDateTime.parse("2023-01-21 12:00:00+03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))), 
        200.50, 
        "Transferência",
        "Beltrano",
        conta2
    );

    List<Transferencia> transferenciasEsperadas = Arrays.asList(transferencia1, transferencia2);


    @Test
    public void buscarTransferencias_Todas_DeveRetornarTransferenciasCorretas() {
        
    }

    @Test
    public void buscarTransferencias_PorConta_DeveRetornarTransferenciasCorretas() {
        when(repository.buscarTransferenciasPorConta(1)).thenReturn(Arrays.asList(transferencia1));
        List<Transferencia> transferencias = service.buscarTransferencias(1, null, null, null);

        assertEquals(1, transferencias.size());
        assertEquals(transferencia1, transferencias.get(0));
        verify(repository, times(1)).buscarTransferenciasPorConta(1);
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
