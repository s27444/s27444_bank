package com.example.s27444_bank.controller;

import com.example.s27444_bank.model.UserAccount;
import com.example.s27444_bank.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userAccount")
@RequiredArgsConstructor

public class UserController {
    private final UserAccountService userAccountService;


    @GetMapping("/{id}")
    public ResponseEntity<UserAccount> getUserAccountByPathVariable(@PathVariable Integer id) {
        UserAccount userAccount = userAccountService.getById(id);

        return ResponseEntity.ok(userAccount);
    }

    @PostMapping("/create")
    public ResponseEntity<UserAccount> createUserAccount(@RequestBody UserAccount userAccount) {
        UserAccount createdUserAccount = userAccountService.createUserAccount(userAccount);

        return ResponseEntity
                .status(HttpStatusCode.valueOf(201))
                .body(createdUserAccount);
    }

}
