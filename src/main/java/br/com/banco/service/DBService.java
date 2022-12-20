package br.com.banco.service;

import br.com.banco.model.Conta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    private ContaService contaService;

    @Bean
    public void populateDatabase() {

        // usado apenas para popular o banco de dados

        Conta c1 = new Conta("Alice Evelyn Rezende", 1926, 1111299, "alicevelyn@gmail.com");
        Conta c2 = new Conta("Victor Carlos Eduardo da Conceição", 2697,11909463, "carlosvitor12@outlook.com");

        contaService.depositar(c1,52500.00);
        contaService.depositar(c2, 12355.00);

        contaService.inserir(c1);
        contaService.inserir(c2);

        contaService.transferir(c1, c2, 2523.53);
        contaService.transferir(c2, c1, 5250.00);
        contaService.sacar(c2, 1500.00);
        contaService.sacar(c1, 1200.00);
        contaService.depositar(c1, 5000.00);
        contaService.depositar(c2, 5213.00);

    }
}
