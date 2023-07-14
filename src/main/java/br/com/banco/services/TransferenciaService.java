package br.com.banco.services;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.TransferenciaRepository;

@Service
public class TransferenciaService {
	
	@Autowired
	TransferenciaRepository repository;
	
	public List<Transferencia> findAll(){
		return  repository.findAll();
	}
	
	
	public Transferencia insert(Transferencia obj){
		return repository.save(obj);
	}
	
	public Transferencia findbyId(Long id) {
		 Optional <Transferencia> obj = repository.findById(id);
		 return obj.get();
	}
	
	public Transferencia findData(Instant dataTransferencia) {
		Optional <Transferencia>obj=repository.findBydataTransferencia(dataTransferencia);
		return obj.get();
				
    }
	
	public Transferencia findbyNomeOpera(String nomeOperadorTransacao) {		 
		Optional<Transferencia> obj = repository.findBynomeOperadorTransacao(nomeOperadorTransacao);
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
