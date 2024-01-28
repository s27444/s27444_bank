package com.example.s27444_bank.controllerUnit;

import com.example.s27444_bank.controller.UserController;
import com.example.s27444_bank.model.UserAccount;
import com.example.s27444_bank.model.UserAccountCurrency;
import com.example.s27444_bank.service.UserAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserAcountControllerUnitTest {

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        // Inicjalizacja mocków
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserAccountByPathVariableTest() {
        // Arrange
        int userId = 1;
        UserAccount expectedUserAccount =  new UserAccount(null, "string", (double) 1, UserAccountCurrency.EUR, "Adrian", "Adrian");
        when(userAccountService.getById(userId)).thenReturn(expectedUserAccount);

        // Act
        ResponseEntity<UserAccount> responseEntity = userController.getUserAccountByPathVariable(userId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedUserAccount, responseEntity.getBody());
        verify(userAccountService, times(1)).getById(userId);
    }

    @Test
    void createUserAccountTest() {
        // Arrange
        UserAccount userAccountToCreate =  new UserAccount(null, "string", (double) 1, UserAccountCurrency.PLN, "Adrian", "Adrian");
        UserAccount createdUserAccount =  new UserAccount(null, "string", (double) 1, UserAccountCurrency.EUR, "Adrian", "Adrian");
        when(userAccountService.createUserAccount(userAccountToCreate)).thenReturn(createdUserAccount);

        // Act
        ResponseEntity<UserAccount> responseEntity = userController.createUserAccount(userAccountToCreate);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdUserAccount, responseEntity.getBody());
        verify(userAccountService, times(1)).createUserAccount(userAccountToCreate);
    }
}
