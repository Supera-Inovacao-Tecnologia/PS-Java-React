package br.com.banco.service;

import br.com.banco.exception.AccountNotFoundException;
import br.com.banco.model.Account;
import br.com.banco.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;


    @Test
    void getAccountById_whenAccountExists_shouldReturnAccount() {
        Account account = Account.builder().id(1L).build();
        when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(account));

        Account actualAccount = accountService.getAccountById(account.getId());

        assertEquals(actualAccount, account);
    }

    @Test
    void getAccountById_whenAccountDoesNotExist_shouldThrowException() {
        when(accountRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.getAccountById(1L));
    }
}