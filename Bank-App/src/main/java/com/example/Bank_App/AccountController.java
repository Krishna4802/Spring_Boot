package com.example.Bank_App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private BankInterest bankinterest;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account, @RequestHeader(value = "Authorization", required = true) String token) {
        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{id}")
    public Account getAccount(@PathVariable Long id) {
        return accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    @PostMapping("/{id}/deposit")
    public Account deposit(@PathVariable Long id, @RequestBody Map<String, Double> request, @RequestHeader(value = "Authorization", required = false) String token) {
        Double amount = request.get("amount");
        return accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public Account withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request, @RequestHeader(value = "Authorization", required = true) String token) {
        Double amount = request.get("amount");
        return accountService.withdraw(id, amount);
    }

    @GetMapping("/{id}/rateofinterest")
    public double roi(@PathVariable Long id){
        Account account = accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        double interest = bankinterest.rateodinterest();
        double total = (account.getBalance() * interest) /100;
        return account.getBalance() + total;
    }
}

