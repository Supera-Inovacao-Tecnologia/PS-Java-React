package br.com.banco.repository;

import br.com.banco.domain.entity.Conta;
import br.com.banco.domain.entity.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

    List<Transferencia> findByContaAndDataTransferenciaBetweenAndNomeOperadorTransacaoContains(
            Conta conta, LocalDateTime dataInicio, LocalDateTime dataFim, String nomeOperador);


    @Query(nativeQuery = true, value = "SELECT SUM(valor) FROM transferencia WHERE conta_id = ?1")
    Double findSaldoTotal();


}
