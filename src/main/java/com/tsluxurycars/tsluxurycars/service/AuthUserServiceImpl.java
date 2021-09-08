package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.AuthUserNotFoundException;
import com.tsluxurycars.tsluxurycars.model.AuthUser;
import com.tsluxurycars.tsluxurycars.repository.AuthUserRepository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public AuthUser findById(Long id) throws AuthUserNotFoundException {
        Optional<AuthUser> optionalAuthUser = authUserRepository.findById(id);

        if (optionalAuthUser.isPresent()) {
            return optionalAuthUser.get();
        }

        throw new AuthUserNotFoundException(id);
    }
}
