package com.tsluxurycars.tsluxurycars.service;

import com.tsluxurycars.tsluxurycars.exception.AuthUserNotFoundException;
import com.tsluxurycars.tsluxurycars.model.AuthUser;

public interface AuthUserService {
    AuthUser createAuthUser(AuthUser newAuthUser);
    AuthUser findById(Long id) throws AuthUserNotFoundException;
}
