package br.com.banco.service;

import br.com.banco.exception.SaldoInsuficienteException;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.dto.ContaDTO;
import br.com.banco.model.enums.Operacao;
import br.com.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaService transferenciaService;


    public void inserir(Conta conta) {
        contaRepository.save(conta);
    }

    public void valida(Conta conta, double valor) {
        if (conta.getSaldo() - valor < 0) {
            throw new SaldoInsuficienteException("Saldo deve ser maior que valor de transação!");
        }
    }

    @Transactional
    public void transferir(Conta origem, Conta destino, Double valor) {

        valida(origem, valor);

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        contaRepository.save(origem);
        contaRepository.save(destino);

        Transferencia transSaida = Transferencia.builder()
                .dataTransferencia(LocalDate.now())
                .tipo(Operacao.TRANSFERENCIA)
                .nomeOperadorTransacao(destino.getNomeResponsavel())
                .conta(origem)
                .valor(valor)
                .build();

        Transferencia transEntrada = Transferencia.builder()
                .dataTransferencia(LocalDate.now())
                .tipo(Operacao.TRANSFERENCIA)
                .nomeOperadorTransacao(origem.getNomeResponsavel())
                .conta(destino)
                .valor(valor)
                .build();

        transferenciaService.inserir(transEntrada);
        transferenciaService.inserir(transSaida);

    }

    @Transactional
    public void sacar(Conta conta, Double valor) {
        valida(conta, valor);

        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);

        Transferencia transferencia = Transferencia.builder()
                .dataTransferencia(LocalDate.now())
                .valor(valor)
                .tipo(Operacao.SAQUE)
                .conta(conta)
                .build();
        transferenciaService.inserir(transferencia);

    }

    @Transactional
    public void depositar(Conta conta, Double valor) {
        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        Transferencia transferencia = Transferencia.builder()
                .dataTransferencia(LocalDate.now())
                .valor(valor)
                .tipo(Operacao.DEPOSITO)
                .conta(conta)
                .build();
        transferenciaService.inserir(transferencia);

    }

    public Conta fromDto(ContaDTO dto) {
        return Conta.builder()
                .email(dto.getEmail())
                .agencia(dto.getAgencia())
                .numero(dto.getNumero())
                .nomeResponsavel(dto.getNome())
                .dataDeCriacao(LocalDate.now())
                .saldo(0.00)
                .build();
    }

    public Conta findByContaNumero(Integer numero) {
        Conta conta = contaRepository.findByNumero(numero);
        return conta;
    }

    public Conta findById(Long id) {
        Optional<Conta> op = contaRepository.findById(id);
        if (op.isEmpty()) {
            throw new RuntimeException("Conta de id: " + id + " não encontrada");
        }

        return op.get();
    }
}
