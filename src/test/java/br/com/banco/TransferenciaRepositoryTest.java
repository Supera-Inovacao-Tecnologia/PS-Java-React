package br.com.banco;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
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
        assertTrue(resposta.stream().anyMatch(res -> res.getTipo().contains("DEPOSITO")));
        assertTrue(resposta.stream().anyMatch(res -> res.getTipo().contains("SAQUE")));
        assertTrue(resposta.stream().anyMatch(res -> res.getTipo().contains("TRANSFERENCIA")));
    }
}
