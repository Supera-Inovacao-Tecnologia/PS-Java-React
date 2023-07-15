package br.com.banco.services;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.TransferenciaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransferenciaServiceTeste {

    @Mock
    private TransferenciaRepository transferenciaRepository;

    @InjectMocks
    private TransferenciaService transferenciaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTransferencias() {
        Transferencia transferencia1 = new Transferencia();
        transferencia1.setId(1L);
        transferencia1.setDataTransferencia(LocalDateTime.now());
        transferencia1.setValor(BigDecimal.valueOf(100));

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setId(2L);
        transferencia2.setDataTransferencia(LocalDateTime.now());
        transferencia2.setValor(BigDecimal.valueOf(200));

        List<Transferencia> transferenciasEsperadas = Arrays.asList(transferencia1, transferencia2);

        when(transferenciaRepository.findAll()).thenReturn(transferenciasEsperadas);

        List<Transferencia> transferencias = transferenciaService.getAllTransferencias();

        verify(transferenciaRepository).findAll();
        assertEquals(transferenciasEsperadas, transferencias);
    }

    @Test
    public void getTransferenciasByNumeroContaSucesso() {
        Long id = 1L;

        Transferencia transferencia1 = new Transferencia();
        transferencia1.setId(1L);
        transferencia1.setDataTransferencia(LocalDateTime.now());
        transferencia1.setValor(BigDecimal.valueOf(100));

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setId(2L);
        transferencia2.setDataTransferencia(LocalDateTime.now());
        transferencia2.setValor(BigDecimal.valueOf(200));

        List<Transferencia> transferenciasIdEsperadas = Arrays.asList(transferencia1, transferencia2);

        when(transferenciaRepository.findByContaIdConta(id)).thenReturn(transferenciasIdEsperadas);

        List<Transferencia> transf = transferenciaService.getTransferenciasByNumeroConta(id);

        verify(transferenciaRepository).findByContaIdConta(id);
        assertEquals(transferenciasIdEsperadas, transf);
    }

}
