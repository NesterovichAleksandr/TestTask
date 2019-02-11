package net.cabinssolitude.cabins.services.impl;

import net.cabinssolitude.cabins.services.AuthenticationFacadeService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationFacadeServiceImpl implements AuthenticationFacadeService {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}