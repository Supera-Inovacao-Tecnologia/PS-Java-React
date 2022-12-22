package br.com.banco.repository;

import br.com.banco.model.Transferencia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {


    /**
     * Retrieves data from Database using account id as reference filtering by period and operator's name.
     * @param id Account id
     * @param inicio begin period
     * @param fim end period
     * @param name operator's name
     * @return Page of Transactions
     */
    @Query("SELECT t FROM Transferencia t WHERE t.conta.id = :id AND t.dataTransferencia BETWEEN :inicio AND :fim AND t.nomeOperadorTransacao LIKE %:nome%")
    Page<Transferencia> findByPeriodAndName(@Param("id") Long id,
                                            @Param("inicio") LocalDateTime inicio,
                                            @Param("fim") LocalDateTime fim, 
                                            @Param("nome") String name,
                                            Pageable pageable);


    /**
     * Retrieves data from Database using account id as reference filtering by period and operator's name.
     * @param id Account id
     * @param inicio begin period
     * @param fim ending period
     * @return Page of Transactions
     */
    @Query("SELECT t FROM Transferencia t WHERE t.conta.id = :id AND t.dataTransferencia BETWEEN :inicio AND :fim")
    Page<Transferencia> findByPeriodo(Long id, LocalDateTime inicio, LocalDateTime fim, Pageable pageable);
}
