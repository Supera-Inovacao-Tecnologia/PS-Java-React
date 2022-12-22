package br.com.banco.service;

import br.com.banco.model.Transferencia;
import br.com.banco.repository.TransferenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class TransferenciaService {

    @Autowired
    private TransferenciaRepository transferenciaRepository;


    @Transactional
    public void inserir(Transferencia transferencia) {
        transferenciaRepository.save(transferencia);
    }


    public Page<Transferencia> findByNomeAndPeriodo(Long id, LocalDateTime inicio, LocalDateTime fim, String name, Pageable pageable) {
        return transferenciaRepository.findByPeriodAndName(id, inicio, fim, name, pageable);
    }

    public Page<Transferencia> findByPeriodo(Long id, LocalDateTime inicio, LocalDateTime fim, Pageable pageable) {
        return transferenciaRepository.findByPeriodo(id, inicio, fim, pageable);
    }
}
