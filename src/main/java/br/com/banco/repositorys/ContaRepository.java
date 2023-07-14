package br.com.banco.repositorys;

import br.com.banco.models.Conta;
import br.com.banco.models.Transferencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository <Conta, Long> {

}
