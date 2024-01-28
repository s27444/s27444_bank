package com.example.s27444_bank.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class UserAccount {
    private Integer id;
    private String peselNumber;
    private Double initialBalance;
    private UserAccountCurrency userAccountCurrency;
    private String firstName;
    private String surName;
}
