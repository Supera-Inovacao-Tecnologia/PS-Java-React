package br.com.banco.repository;

import br.com.banco.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {

    Conta findByNumero(Integer numero);
}