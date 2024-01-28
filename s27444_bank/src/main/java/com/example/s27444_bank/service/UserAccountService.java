package com.example.s27444_bank.service;


import com.example.s27444_bank.exception.UserAccountNotFoundException;
import com.example.s27444_bank.exception.ValidationException;
import com.example.s27444_bank.model.UserAccount;
import com.example.s27444_bank.model.UserAccountCurrency;
import com.example.s27444_bank.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserAccountService {
    private final UserAccountRepository userAccountRepository;


    private boolean isValidCurrency(UserAccountCurrency currency) {
        for (UserAccountCurrency validCurrency : UserAccountCurrency.values()) {
            if (validCurrency.equals(currency)) {
                return true;
            }
        }
        return false;
    }
    public UserAccount createUserAccount(UserAccount userAccount) {
        Map<String, String> validationErrors = new HashMap<>();
        if (userAccount.getInitialBalance() <= 0) {
            validationErrors.put("initialBalance", "Must be more than 0");
        }
        if (userAccount.getPeselNumber() == null || userAccount.getPeselNumber().isBlank()) {
            validationErrors.put("peselNumber", "Cannot be blank");
        }
        if (userAccount.getUserAccountCurrency() == null || !isValidCurrency(userAccount.getUserAccountCurrency())) {
            validationErrors.put("userAccountCurrency", "Invalid currency");
        }


        if (!validationErrors.isEmpty()){
            throw new ValidationException(validationErrors);
        }

       userAccountRepository.create(userAccount);

       return userAccount;
    }

    public UserAccount getById(Integer id){
        Optional<UserAccount> userAccount = userAccountRepository.getById(id);


        return userAccount.orElseThrow(() ->  new UserAccountNotFoundException("UserAccount with id: "+id + " does not exist"));
    }

}
