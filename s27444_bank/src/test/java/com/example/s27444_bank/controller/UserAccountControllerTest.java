package com.example.s27444_bank.controller;

import com.example.s27444_bank.model.UserAccount;
import com.example.s27444_bank.model.UserAccountCurrency;
import com.example.s27444_bank.repository.UserAccountRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;



import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class UserAccountControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateNewUserAccount() throws JsonProcessingException {
        UserAccount userAccount = new UserAccount(null, "string", (double) 1, UserAccountCurrency.EUR, "Adrian", "Adrian");

        String userAccountJson = objectMapper.writeValueAsString(userAccount);

        UserAccount result = webTestClient.post().uri("/userAccount/create")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(userAccountJson)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(UserAccount.class)
                .returnResult().getResponseBody();

        assertEquals(result.getPeselNumber(), userAccount.getPeselNumber());
        assertEquals(result, userAccountRepository.getById(result.getId()).get());
    }


    @Test
    void shouldReturnUserAccountByParam(){
        UserAccount userAccount = new UserAccount(null, "string", (double) 1, UserAccountCurrency.EUR, "Adrian", "Adrian");

        UserAccount result = webTestClient.get().uri(uriBuilder ->
                        uriBuilder.path("/userAccount")
                                .queryParam("id", 0)
                                .build())
                .exchange()
                .expectBody(UserAccount.class)
                .returnResult().getResponseBody();


        assertEquals(result.getPeselNumber(), userAccount.getPeselNumber());
        assertEquals(result, userAccountRepository.getById(result.getId()).get());
    }
}
