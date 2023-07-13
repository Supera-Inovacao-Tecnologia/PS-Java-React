package br.com.banco;

import br.com.banco.services.TransferenciaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BancoApplicationTests {

    @Autowired
    private TransferenciaService service;

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
