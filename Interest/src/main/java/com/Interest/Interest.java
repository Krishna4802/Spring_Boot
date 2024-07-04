package com.Interest;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Interest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Double interestrate;
//    private String accountHolderName;
//    private double balance;
}