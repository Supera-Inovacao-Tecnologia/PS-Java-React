package br.com.banco.service;

import br.com.banco.exception.SaldoInsuficienteException;
import br.com.banco.model.Conta;
import br.com.banco.model.Transferencia;
import br.com.banco.model.dto.ContaDTO;
import br.com.banco.model.enums.Operacao;
import br.com.banco.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransferenciaService transferenciaService;


    /**
     * Insert an account at database
     * @param conta account object
     */
    public void inserir(Conta conta) {
        contaRepository.save(conta);
    }

    /**
     * validates value before making transcations. (AccountBalance - Value) shouldn't be less than 0
     * @param conta Account object
     * @param valor value
     */
    public void valida(Conta conta, double valor) {
        if (conta.getSaldo() - valor < 0) {
            throw new SaldoInsuficienteException("(AccountBalance - Value) shouldn't be less than 0");
        }
    }

    /**
     * transfer value from a account to another. Instantiates Transferencia object and inserts at database.
     * @param origem account sender
     * @param destino account receiver
     * @param valor value
     */
    @Transactional
    public void transferir(Conta origem, Conta destino, Double valor) {

        valida(origem, valor);

        origem.setSaldo(origem.getSaldo() - valor);
        destino.setSaldo(destino.getSaldo() + valor);

        contaRepository.save(origem);
        contaRepository.save(destino);

        Transferencia transSaida = Transferencia.builder()
                .dataTransferencia(LocalDateTime.now())
                .tipo(Operacao.TRANSFERENCIA)
                .nomeOperadorTransacao(destino.getNomeResponsavel())
                .conta(origem)
                .valor(valor)
                .build();

        Transferencia transEntrada = Transferencia.builder()
                .dataTransferencia(LocalDateTime.now())
                .tipo(Operacao.TRANSFERENCIA)
                .nomeOperadorTransacao(origem.getNomeResponsavel())
                .conta(destino)
                .valor(valor)
                .build();

        transferenciaService.inserir(transEntrada);
        transferenciaService.inserir(transSaida);

    }

    /**
     * Withdraws value from account. Instantiates Transferencia object and inserts at database.
     * @param conta account object
     * @param valor value
     */
    @Transactional
    public void sacar(Conta conta, Double valor) {
        valida(conta, valor);

        conta.setSaldo(conta.getSaldo() - valor);
        contaRepository.save(conta);

        Transferencia transferencia = Transferencia.builder()
                .dataTransferencia(LocalDateTime.now())
                .valor(valor)
                .tipo(Operacao.SAQUE)
                .conta(conta)
                .build();
        transferenciaService.inserir(transferencia);

    }

    /**
     * Deposit value to account. Instantiates Transferencia object and inserts at database.
     * @param conta account object
     * @param valor value
     */
    @Transactional
    public void depositar(Conta conta, Double valor) {
        conta.setSaldo(conta.getSaldo() + valor);
        contaRepository.save(conta);

        Transferencia transferencia = Transferencia.builder()
                .dataTransferencia(LocalDateTime.now())
                .valor(valor)
                .tipo(Operacao.DEPOSITO)
                .conta(conta)
                .build();
        transferenciaService.inserir(transferencia);

    }

    /**
     * Converts dto from account into Account object
     * @param dto ContaDTO
     * @return Account Object
     */
    public Conta fromDto(ContaDTO dto) {
        return Conta.builder()
                .id(null)
                .email(dto.getEmail())
                .agencia(dto.getAgencia())
                .numero(dto.getNumero())
                .nomeResponsavel(dto.getNome())
                .dataDeCriacao(LocalDateTime.now())
                .saldo(0.00)
                .build();
    }

    /**
     * Retrieves account object from database by id.
     * @param id account's id
     * @return Account Object
     */
    public Conta findById(Long id) {
        Optional<Conta> op = contaRepository.findById(id);
        if (op.isEmpty()) {
            throw new EntityNotFoundException("Account with id: " + id + " not found");
        }

        return op.get();
    }

    /**
     * Retrieves data from Database using account id as reference filtering by period and operator's name.
     * @param id Account id
     * @param inicio begin period
     * @param fim end period
     * @param name operator's name
     * @return Page of Transactions
     */
    public Page<Transferencia> findByOperadorAndPeriod(Long id, LocalDateTime inicio, LocalDateTime fim, String name, Pageable pageable) {
        return transferenciaService.findByNomeAndPeriodo(id, inicio, fim, name, pageable);
    }

    /**
     * Retrieves data from Database using account id as reference filtering by period and operator's name.
     * @param id Account id
     * @param inicio begin period
     * @param fim ending period
     * @return Page of Transactions
     */
    public Page<Transferencia> findByPeriodo(Long id, LocalDateTime inicio, LocalDateTime fim, Pageable pageable) {
        return transferenciaService.findByPeriodo(id, inicio, fim, pageable);
    }
}
