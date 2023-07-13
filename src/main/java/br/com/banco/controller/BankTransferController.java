package br.com.banco.controller;

import org.springframework.web.bind.annotation.RestController;
import br.com.banco.entities.Account;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping(value = "/account")
public class BankTransferController {

    @GetMapping
    public Account getObjects() {
        Account a1 = new Account();
        a1.setAccountId(1);
        a1.setClientName("Maria");
        return a1;
    }
}
