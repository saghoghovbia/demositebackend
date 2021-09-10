package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.AuthUserNotFoundException;
import com.tsluxurycars.tsluxurycars.model.AuthUser;
import com.tsluxurycars.tsluxurycars.repository.AuthUserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthUserServiceImplTest {
    @Mock
    private AuthUserRepository authUserRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private AuthUserServiceImpl authUserService;

    @Test
    void createAuthUser() {
        Mockito.when(authUserRepository.save(Mockito.any(AuthUser.class))).thenReturn(new AuthUser());

        AuthUser authUser = new AuthUser();
        authUser.setPassword("root");

        assertThat(authUserService.createAuthUser(authUser), is(notNullValue()));
    }

    @Test
    void findById() throws AuthUserNotFoundException {
        Mockito.when(authUserRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.of(new AuthUser()));

        assertThat(authUserService.findById(1L), is(notNullValue()));
    }

    @Test
    void findById_AuthUserNotFound() {
        Mockito.when(authUserRepository.findById(Mockito.any(Long.class))).thenReturn(Optional.empty());

        assertThrows(AuthUserNotFoundException.class, () -> {
            authUserService.findById(1L);
        });
    }
}