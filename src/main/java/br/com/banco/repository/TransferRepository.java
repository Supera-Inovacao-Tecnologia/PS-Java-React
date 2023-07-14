package br.com.banco.repository;

import br.com.banco.model.Account;
import br.com.banco.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TransferRepository extends JpaRepository<Transfer, Long> {
    List<Transfer> findByAccountOrOperatorName(Account account, String operatorName);

}
