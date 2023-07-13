package br.com.banco.services;

import br.com.banco.models.Conta;
import br.com.banco.repositorys.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;


    @Transactional
    public Conta saveConta(Conta conta){
        return contaRepository.save(conta);
    }

    public List<Conta> findAllConta(){
        return contaRepository.findAll();
    }
}
