package br.com.banco.controller;

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

import br.com.banco.entities.Conta;
import br.com.banco.entities.Transferencia;
import br.com.banco.repositories.ContaRepository;
import br.com.banco.services.TransferenciaService;

@RestController
@RequestMapping(value="/transferencias")
public class TransferenciaController {

	@Autowired
	private TransferenciaService service;
	
	
	@Autowired
	private ContaRepository cr;
  
	@GetMapping
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<List<Transferencia>> findAll(){
		List<Transferencia> list=service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value="/{id}")
	@CrossOrigin("http://localhost:3000")
	public  ResponseEntity<Transferencia> findById(@PathVariable Long id){		
		Transferencia order=service.findbyId(id);
		return ResponseEntity.ok().body(order);
	}
	
	
	
	
	@PostMapping("/{conta_id}")	
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Transferencia> insert(@RequestBody Transferencia obj, @PathVariable("conta_id") Long conta_id) {
		System.out.println("id conta"+conta_id);
	    Conta p = cr.findById(conta_id).orElseThrow(() -> new IllegalArgumentException("conta não encontrada")); 
	    obj.setNome_operador(p.getnomeResponsavel());
	    obj = service.insert(obj);
	    return ResponseEntity.ok().body(obj);
	}
	
	 	@GetMapping(value="/datatransferencia")
	    public Transferencia buscarPorIntervaloDeDatas(@RequestParam("dataTransferencia") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant dataTransferencia)
	                                                     {
	        return service.findData(dataTransferencia);
	    }
	 	
	 	
	 	@GetMapping(value="/dono/{nomeOperadorTransacao}")
		@CrossOrigin("http://localhost:3000")
		public  ResponseEntity <Transferencia> findbynome(@PathVariable String nomeOperadorTransacao){		
			Transferencia order=service.findbyNomeOpera(nomeOperadorTransacao);
			return ResponseEntity.ok().body(order);
		}
	
	
	
	
	@DeleteMapping(value="/{id}")
	@CrossOrigin("http://localhost:3000")
	public ResponseEntity<Void> Delete(@PathVariable long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
}
