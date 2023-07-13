package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TransferenciaService {
    private TransferenciaRepository repository;

    @Autowired
    public TransferenciaService(TransferenciaRepository repository) {
        this.repository = repository;
    }

    public List<Transferencia> buscarTodasTransferencias() {
        return repository.buscarTodasTransferencias();
    }
}
