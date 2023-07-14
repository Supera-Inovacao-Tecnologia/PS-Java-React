package br.com.banco.services;

import br.com.banco.controllers.TransferenciaController;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.TrasnferenciaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TransferenciaService {

    @Autowired
    private TrasnferenciaRepository trasnferenciaRepository;

    public List<Transferencia> findAllTransferencia() {

        return trasnferenciaRepository.findAll();
    }

    @Transactional
    public Transferencia saveTransferencia(Transferencia transferencia) {
        if (transferencia.getNomeOperadorTransacao() == null){
            transferencia.getConta().getNomeResponsavel();
        }
        return trasnferenciaRepository.save(transferencia);
    }

    public Optional<Transferencia> findByIdTransferencia(Long id) {
       return trasnferenciaRepository.findById(id);
    }
}
