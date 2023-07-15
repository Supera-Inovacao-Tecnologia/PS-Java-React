package br.com.banco.services;

import br.com.banco.models.Transferencia;
import br.com.banco.repositorys.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;

    public List<Transferencia> findAllTransferencia() {
        return transferenciaRepository.findAll();
    }

    @Transactional
    public Transferencia saveTransferencia(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }

    public List<Transferencia> getAllTransferencias() {
        return transferenciaRepository.findAll();
    }

    public List<Transferencia> getTransferenciasBetweenDates(LocalDateTime dataInicio, LocalDateTime dataFim) {
        return transferenciaRepository.findByDataTransferenciaBetween(dataInicio, dataFim);
    }

    public List<Transferencia> getTransferenciasOperador(String operadorName) {
        return transferenciaRepository.findByNomeOperadorTransacao(operadorName);
    }

    public List<Transferencia> getTransferenciaWhitAllFilters
            (LocalDateTime dataInicio, LocalDateTime dataFim, String operadorNome) {
        return transferenciaRepository
                .findByDataTransferenciaBetweenAndNomeOperadorTransacao(dataInicio, dataFim, operadorNome);
    }

    public List<Transferencia> getTransferenciasByNumeroConta(String numeroConta) {
        return transferenciaRepository.findByContaNumeroConta(numeroConta);
    }
}
