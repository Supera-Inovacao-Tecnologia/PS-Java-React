package br.com.banco.repositorys;

import br.com.banco.models.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Long> {
}
