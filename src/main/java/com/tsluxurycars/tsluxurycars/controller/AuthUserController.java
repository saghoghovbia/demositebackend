package com.tsluxurycars.tsluxurycars.controller;

import com.tsluxurycars.tsluxurycars.model.AuthUser;
import com.tsluxurycars.tsluxurycars.service.AuthUserService;
import com.tsluxurycars.tsluxurycars.service.AuthUserServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class AuthUserController {
    private final AuthUserService authUserService;

    public AuthUserController(AuthUserServiceImpl authUserServiceImpl) {
        this.authUserService = authUserServiceImpl;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthUser> signup(@RequestBody AuthUser newAuthUser) {
        return new ResponseEntity<>(authUserService.createAuthUser(newAuthUser), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthUser authUser) {
//        AuthUser user = AuthUserService

        return null;
    }
}
