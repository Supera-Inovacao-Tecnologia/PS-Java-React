package br.com.banco.repositories;

import br.com.banco.entities.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
    @Query("SELECT t FROM Transferencia t JOIN FETCH t.conta")
    List<Transferencia> buscarTodasTransferencias();
}

