package br.com.banco.repository;

import br.com.banco.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
