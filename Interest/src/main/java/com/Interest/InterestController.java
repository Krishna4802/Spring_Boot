package com.Interest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class InterestController {

    @Autowired
    private BankInterest bankInterestService;

    @GetMapping("/rateOfInterest")
    public ResponseEntity<Double> roi() {
        double interestRate = bankInterestService.rateofinterest();
        return ResponseEntity.ok(interestRate);
    }
}
