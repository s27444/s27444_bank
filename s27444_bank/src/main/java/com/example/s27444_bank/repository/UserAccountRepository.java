package com.example.s27444_bank.repository;

import com.example.s27444_bank.model.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class UserAccountRepository {
    List<UserAccount> userAccountList = new ArrayList<>();

    public UserAccount create(UserAccount userAccount) {
        userAccount.setId(userAccountList.size());
        userAccountList.add(userAccount);

        return userAccount;
    }

    public Optional<UserAccount> getById(Integer id){
        return userAccountList.stream()
                .filter(userAccount -> userAccount.getId().equals(id))
                .findFirst();
    }

}
