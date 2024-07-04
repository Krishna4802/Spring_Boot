package com.example.Bank_App;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account, @RequestHeader(value = "Authorization", required = true) String token) {

        return ResponseEntity.ok(accountService.createAccount(account));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id , @RequestHeader(value = "Authorization", required = true) String token) {
        Account account = accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request, @RequestHeader(value = "Authorization", required = true) String token) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.deposit(id, amount));
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request, @RequestHeader(value = "Authorization", required = true) String token) {
        Double amount = request.get("amount");
        return ResponseEntity.ok(accountService.withdraw(id, amount));
    }

    @GetMapping("/roi")
    public ResponseEntity<Mono<Double>> rate(@RequestHeader(value = "Authorization", required = true) String token){
        return ResponseEntity.ok(accountService.getInterestRate());
    }

    @GetMapping("/{id}/InterestAmount")
    public double Interest(@PathVariable Long id, @RequestBody Map<String, Double> request, @RequestHeader(value = "Authorization", required = true) String token) {
        Account account = accountService.getAccount(id).orElseThrow(() -> new RuntimeException("Account not found"));
        double interestRate = accountService.getInterestRate().block();
        double totalInterest = (account.getBalance() * interestRate) / 100.0;
        double totalAmount = account.getBalance() + totalInterest;
        return totalAmount;
    }

}
