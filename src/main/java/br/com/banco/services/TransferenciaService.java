package br.com.banco.services;

import br.com.banco.entities.Transferencia;
import br.com.banco.exception.FiltroNaoEncontradoException;
import br.com.banco.exception.ParametroDeTempoException;
import br.com.banco.exception.ParametrosInvalidosException;
import br.com.banco.repositories.TransferenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TransferenciaService {
  private TransferenciaRepository repository;

  @Autowired
  public TransferenciaService(TransferenciaRepository repository) {
      this.repository = repository;
  }

  public List<Transferencia> buscarTransferencias(
    Integer contaId, String nomeOperador, Integer mes, Integer ano
  ) {
    
    if ((mes == null && ano != null) || mes != null && ano == null) {
        throw new ParametroDeTempoException("Você não pode passar como parâmetro apenas o mês ou o ano.");
    }

    if (contaId != null && nomeOperador != null && mes == null && ano == null) {
        throw new ParametrosInvalidosException("Você não pode passar o id da conta e o nome do operador como parâmetros. Os dois só podem ser passados em conjunto se o mês e o ano forem informados também.");
    }

    if (contaId != null && mes != null && ano != null && nomeOperador == null) {
        throw new ParametrosInvalidosException("Você não pode passar o id da conta e o mês e o ano como parâmetros. Eles só podem ser passados em conjunto se o nome do operador for informado também.");
    }

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
