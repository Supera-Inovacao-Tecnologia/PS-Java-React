package br.com.banco.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {
	
	Optional<Transferencia> findBydataTransferencia(Instant dataTransferencia);
	
	Optional<Transferencia>	 findBynomeOperadorTransacao(String nomeOperadorTransacao);

}



