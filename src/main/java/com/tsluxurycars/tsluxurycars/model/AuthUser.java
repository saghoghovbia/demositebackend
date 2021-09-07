package com.tsluxurycars.tsluxurycars.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class AuthUser {
    public AuthUser(String username, String password, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String password;

    @ElementCollection
    private List<String> authorities;
}
