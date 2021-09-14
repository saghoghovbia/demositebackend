package com.tsluxurycars.tsluxurycars.security;

import com.tsluxurycars.tsluxurycars.model.AuthUser;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.tsluxurycars.tsluxurycars.repository.AuthUserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;

import static com.tsluxurycars.tsluxurycars.constants.SecurityConstants.EXPIRATION_TIME;
import static com.tsluxurycars.tsluxurycars.constants.SecurityConstants.KEY;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final AuthUserRepository authUserRepository;

    public AuthenticationFilter(AuthenticationManager authenticationManager, AuthUserRepository authUserRepository) {
        this.authenticationManager = authenticationManager;
        this.authUserRepository = authUserRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            AuthUser authUser = new ObjectMapper().readValue(req.getInputStream(), AuthUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authUser.getUsername(),
                            authUser.getPassword(), new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) {

        Date exp = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Key key = Keys.hmacShaKeyFor(KEY.getBytes());
        Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
        String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, key).setExpiration(exp).compact();
        res.addHeader("token", token);

        AuthUser authUser = authUserRepository.findByUsername(((User) auth.getPrincipal()).getUsername());
        res.addHeader("role", authUser.getRole().toString());
        res.addHeader("userID", authUser.getId().toString());
    }
}
