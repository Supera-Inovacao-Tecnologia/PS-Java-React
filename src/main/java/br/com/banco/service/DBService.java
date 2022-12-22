package br.com.banco.service;

import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.enums.Operacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class DBService {

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransferenciaService transferenciaService;

    @Bean
    public void populateDatabase() {

        // usado apenas para popular o banco de dados

        Conta c1 = new Conta("Alice Evelyn Rezende", 1926, 1111299, "alicevelyn@gmail.com");
        Conta c2 = new Conta("Victor Carlos Eduardo da Conceição", 2697,11909463, "carlosvitor12@outlook.com");

        depositarDb(c1,52500.00, 22, 5, 2022, 16, 15, 55);
        depositarDb(c2, 12355.00, 16, 2, 2022, 12, 22, 30);

        contaService.inserir(c1);
        contaService.inserir(c2);

        transferirDb(c1, c2, 2523.53, 26, 2, 2021, 22, 33, 52);
        transferirDb(c2, c1, 5250.00, 14, 5, 2022, 21, 32, 51);
        sacarDb(c2, 1500.00, 26, 5, 2022, 9, 12, 52);
        sacarDb(c1, 1200.00, 23, 11, 2022, 11, 53, 11);
        depositarDb(c1, 5000.00, 23, 11, 2021, 14, 32, 0);
        depositarDb(c2, 5213.00, 28, 7, 2021, 16, 20, 0);

    }

    @Transactional
    public void sacarDb(Conta conta, Double valor, int dia, int mes, int ano, int hora, int minuto, int segundo) {
        // usado apenas para simular transações fora de data
        contaService.valida(conta, valor);

        conta.setSaldo(conta.getSaldo() - valor);
        contaService.inserir(conta);

        Transferencia transferencia = Transferencia.builder()
                .dataTransferencia(LocalDateTime.of(ano, mes, dia, hora, minuto, segundo))
                .valor(valor)
                .tipo(Operacao.SAQUE)
                .conta(conta)
                .build();
        transferenciaService.inserir(transferencia);

    }

    @Transactional
    public void depositarDb(Conta conta, Double valor, int dia, int mes, int ano, int hora, int minuto, int segundo) {
        // usado apenas para simular transações fora de data
        conta.setSaldo(conta.getSaldo() + valor);
        contaService.inserir(conta);

        Transferencia transferencia = Transferencia.builder()
                .dataTransferencia(LocalDateTime.of(ano, mes, dia, hora, minuto, segundo))
                .valor(valor)
                .tipo(Operacao.DEPOSITO)
                .conta(conta)
                .build();
        transferenciaService.inserir(transferencia);
    }

    @Transactional
    public void transferirDb(Conta origem, Conta destino, Double valor, int dia, int mes, int ano, int hora, int minuto, int segundo) {
        // usado apenas para simular transações fora de data
        contaService.valida(origem, valor);

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        contaService.inserir(origem);
        contaService.inserir(destino);

        Transferencia transSaida = Transferencia.builder()
                .dataTransferencia(LocalDateTime.of(ano, mes, dia, hora, minuto, segundo))
                .tipo(Operacao.TRANSFERENCIA)
                .nomeOperadorTransacao(destino.getNomeResponsavel())
                .conta(origem)
                .valor(valor)
                .build();

        Transferencia transEntrada = Transferencia.builder()
                .dataTransferencia(LocalDateTime.of(ano, mes, dia, hora, minuto, segundo))
                .tipo(Operacao.TRANSFERENCIA)
                .nomeOperadorTransacao(origem.getNomeResponsavel())
                .conta(destino)
                .valor(valor)
                .build();

        transferenciaService.inserir(transEntrada);
        transferenciaService.inserir(transSaida);

    }
}
