package br.com.banco.repositorys;

import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia>findByNomeOperadorTransacao(String nomeOperadorTransacao);

    List<Transferencia> findByContaIdConta(int idConta);

    List<Transferencia> findByDataTransferenciaBetweenAndNomeOperadorTransacao
            (LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperadorTransacao);

    List<Transferencia> findByDataTransferenciaBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

//    @Query("SELECT t FROM Transferencia t WHERE t.dataTransferencia >= :startDate AND t.dataTransferencia <= :endDate")
//    List<Transferencia> findByPeriodo(LocalDateTime startDate, LocalDateTime endDate);


    List<Transferencia> findByContaIdConta(Long idConta);
}
