package br.com.banco.service;

import br.com.banco.exception.AccountNotFoundException;
import br.com.banco.model.Account;
import br.com.banco.repository.AccountRepository;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId) {
        return accountRepository
                .findById(accountId)
                .orElseThrow(AccountNotFoundException::new);
    }
}
