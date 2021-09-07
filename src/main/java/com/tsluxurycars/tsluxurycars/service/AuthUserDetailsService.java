package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.model.AuthUser;
import com.tsluxurycars.tsluxurycars.repository.AuthUserRepository;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthUserDetailsService implements UserDetailsService {
    private final AuthUserRepository authUserRepository;

    public AuthUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser authUser = authUserRepository.findByUsername(username);

        if (authUser == null) {
            throw new UsernameNotFoundException(username);
        }

        List<SimpleGrantedAuthority> grantedAuthorities = authUser.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        return new User(authUser.getUsername(), authUser.getPassword(), grantedAuthorities);
    }
}
