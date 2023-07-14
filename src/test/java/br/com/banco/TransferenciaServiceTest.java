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
        /*Conta conta = new Conta(1, "Sicrano");

        List<Transferencia> transferenciasEsperadas = Arrays.asList(
                new Transferencia(
                    1, 
                    Timestamp.valueOf(LocalDateTime.parse("2023-01-01 12:00:00+03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))), 
                    100.40, 
                    "DEPÓSITO", 
                    null,
                    conta
                ),
                new Transferencia(
                    2, 
                    Timestamp.valueOf(LocalDateTime.parse("2023-01-01 12:00:00+03", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))), 
                    200.50, 
                    "Transferência",
                    "Beltrano",
                    conta
                )
        );

        when(repository.buscarTransferenciasPorConta(1)).thenReturn(transferenciasEsperadas);

        // Chamada do método para buscar transferências por conta
        List<Transferencia> transferencias = service.buscarTransferenciasPorConta(1, null, null, null, );

        // Verificação dos resultados esperados
        assertEquals(2, transferencias.size());
        assertEquals(transferencia1, transferencias.get(0));
        assertEquals(transferencia2, transferencias.get(1));

        // Verificação se as transferências retornadas correspondem à conta esperada
        verify(repository, times(1)).findByContaId(1);
        assertEquals(2, transferencias.size());

        Transferencia transferencia1 = transferencias.get(0);
        assertEquals(1, transferencia1.getId());
        assertEquals(100.0, transferencia1.getValor());
        assertEquals("Transferência", transferencia1.getTipo());
        assertEquals(1, transferencia1.getContaId());

        Transferencia transferencia2 = transferencias.get(1);
        assertEquals(2, transferencia2.getId());
        assertEquals(200.0, transferencia2.getValor());
        assertEquals("Transferência", transferencia2.getTipo());
        assertEquals(1, transferencia2.getContaId());*/
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
