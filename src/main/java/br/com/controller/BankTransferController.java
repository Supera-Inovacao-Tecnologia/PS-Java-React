package br.com.controller;

import br.com.entities.Account;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/bank-transfers")

public class BankTransferController {

    @GetMapping
    public Account getObjects() {
        Account a1 = new Account();
    }
}
