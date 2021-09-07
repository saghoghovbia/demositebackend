package com.tsluxurycars.tsluxurycars.repository;

import com.tsluxurycars.tsluxurycars.model.AuthUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    AuthUser findByUsername(String username);
}
