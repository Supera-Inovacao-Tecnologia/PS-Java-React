package br.com.banco.repositories;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.banco.entities.Conta;



public interface ContaRepository extends JpaRepository<Conta,Long> {
	
	//@Query(value="select C from Conta  where C.NomeResponsavel")
	Optional<Conta>	 findByNomeResponsavel(String nomeResponsavel);
	
	
	
	
}

