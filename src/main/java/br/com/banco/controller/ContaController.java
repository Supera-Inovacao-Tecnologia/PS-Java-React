package br.com.banco.controller;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.banco.entities.Conta;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.services.ContaService;



@RestController
@RequestMapping(value="/contas")
public class ContaController {
	
	@Autowired
	private ContaService service; 
	
	
	private ContaRepository cr;
  
	@GetMapping
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<List<Conta>> findAll(){
		List<Conta> list=service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value="/{id}")
	@CrossOrigin("http://localhost:3000")
	public  ResponseEntity<Conta> findById(@PathVariable Long id){		
		Conta order=service.findbyId(id);
		return ResponseEntity.ok().body(order);
	}
	
	@GetMapping(value="/dono/{nomeResponsavel}")
	@CrossOrigin("http://localhost:3000")
	public  ResponseEntity <Conta> findbyNome(@PathVariable String nomeResponsavel){		
		Conta order=service.findbyNomeResp(nomeResponsavel);
		return ResponseEntity.ok().body(order);
	}
	
	
	@PostMapping
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Conta> insert(@RequestBody Conta obj){
		obj  = service.insert(obj);
		URI uri =ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj); 
	}
	
	
	
	@DeleteMapping(value="/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> Delete(@PathVariable long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}

}
