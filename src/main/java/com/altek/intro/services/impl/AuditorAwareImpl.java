package com.altek.intro.services.impl;

import com.altek.intro.entities.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {

//    @Override
//    public Optional<String> getCurrentAuditor() {
//        jwtUtils.getUserNameFromJwtToken()
//        return Optional.of("Default auditor");
//    }

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            return Optional.empty();
        }

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Optional.ofNullable(userPrincipal.getUsername());
    }
}
