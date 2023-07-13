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

  public List<Transferencia> buscarTransferencias(Integer contaId, String nomeOperador, Integer mes, Integer ano) {
    if (nomeOperador != null && mes != null & ano != null) {
      return repository.buscarTransferenciasPorMesAnoEoperador(nomeOperador, mes, ano);
    } else if (contaId != null) {
      return repository.buscarTransferenciasPorConta(contaId);
    } else if (nomeOperador != null) {
      return repository.buscarTransferenciasPorNomeOperador(nomeOperador);
    } else if (mes != null && ano != null) {
      return repository.buscarTransferenciasPorMesAno(mes, ano);
    } else {
      return repository.buscarTodasTransferencias();
    }
  }
}
