package com.tsluxurycars.tsluxurycars.model;

import com.tsluxurycars.tsluxurycars.constants.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AuthUser {
    public AuthUser(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    private Role role;
}
