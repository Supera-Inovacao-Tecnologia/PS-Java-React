package br.com.banco;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class TransferenciaRepositoryTest {
    
    @Autowired
    private TransferenciaRepository repository;

    @Test
    public void testarBuscarTodasAsTransferencias() {
        List<Transferencia> resposta = repository.buscarTodasTransferencias();

        assertNotNull(resposta);
        assertEquals(resposta.get(0).getId(), 1);
    }

    @Test
    public void testarBuscarTodasTransferenciasPorIdConta() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorConta(1);
        List<Transferencia> resposta2 = repository.buscarTransferenciasPorConta(2);
  
        assertNotNull(resposta);
        assertEquals(resposta.get(0).getId(), 1);
        assertNotNull(resposta2);
        assertNotEquals(resposta2.get(0).getId(), 1);
    }

    @Test
    public void testarBuscarTodasTransferenciasPorNomeOperador() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorNomeOperador("Beltrano");

        assertNotNull(resposta);
        assertEquals(resposta.get(0).getTipo(), "TRANSFERENCIA");
        assertEquals(resposta.get(0).getNomeOperadorTransacao(), "Beltrano");
    }

    @Test
    public void testarBuscarTransferenciasPorMesAno() {
        List<Transferencia> resposta = repository.buscarTransferenciasPorMesAno(2, 2020);
        assertEquals(0, resposta.size());

        List<Transferencia> resposta2 = repository.buscarTransferenciasPorMesAno(3, 2023);
        assertNotNull(resposta2);
    }
}
