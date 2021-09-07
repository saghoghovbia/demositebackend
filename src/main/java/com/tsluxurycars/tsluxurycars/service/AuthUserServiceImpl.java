package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.model.AuthUser;
import com.tsluxurycars.tsluxurycars.repository.AuthUserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserService {
    private final AuthUserRepository authUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthUserServiceImpl(AuthUserRepository authUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.authUserRepository = authUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public AuthUser createAuthUser(AuthUser newAuthUser) {
        newAuthUser.setPassword(bCryptPasswordEncoder.encode(newAuthUser.getPassword()));
        return authUserRepository.save(newAuthUser);
    }
}
