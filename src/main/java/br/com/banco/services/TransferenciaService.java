package br.com.banco.services;

import br.com.banco.controllers.TransferenciaController;
import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.TrasnferenciaRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
        return trasnferenciaRepository.save(transferencia);
    }

    public List<Transferencia> getAllTransferencias() {
        return trasnferenciaRepository.findAll();
    }

    public List<Transferencia> getTransferenciasBetweenDates(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return trasnferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
    }

    public List<Transferencia> getTransferenciasOperador(String operadorName) {
        return trasnferenciaRepository.findByNomeOperadorTransacao(operadorName);
    }

    public List<Transferencia> getTransferenciaWhitAllFilters
            (LocalDateTime dataInicio, LocalDateTime dataFim, String operadorNome){
        return trasnferenciaRepository
                .findByDataTransferenciaBetweenAndNomeOperadorTransacao(dataInicio, dataFim, operadorNome);
    }
}
