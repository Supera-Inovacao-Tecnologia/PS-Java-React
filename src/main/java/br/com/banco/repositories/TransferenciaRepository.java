package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta")
    List<Transferencia> buscarTodasTransferencias();

    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE c.idConta = :contaId")
    List<Transferencia> buscarTransferenciasPorConta(@Param("contaId") Integer contaId);

    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE t.nomeOperadorTransacao = :nomeOperador")
    List<Transferencia> buscarTransferenciasPorNomeOperador(@Param("nomeOperador") String nomeOperador);

    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta c WHERE YEAR(t.dataTransferencia) = :ano AND MONTH(t.dataTransferencia) = :mes")
    List<Transferencia> buscarTransferenciasPorMesAno(@Param("mes") int mes, @Param("ano") int ano);
}

