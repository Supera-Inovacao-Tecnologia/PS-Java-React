package br.com.banco.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;

@Service
public class ContaService {
	
	@Autowired
	ContaRepository repository;
	
	public List<Conta> findAll(){
		return  repository.findAll();
	}
	
	
	public Conta insert(Conta obj){
		return repository.save(obj);
	}
	
	public Conta findbyId(Long id) {
	    Optional<Conta> obj = repository.findById(id);
	    return obj.orElseThrow(() -> new RuntimeException("Conta não encontrada")); 
	}
	
	public Conta findbyNomeResp(String NomeResponsavel) {		 
		Optional<Conta> obj = repository.findByNomeResponsavel(NomeResponsavel);
		return obj.orElseThrow(() -> new RuntimeException("Usuario não encontrado")); 
	}
	
	 
	
	public void  delete(Long obj) {
		try {
			repository.deleteById(obj);
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
	}

}
