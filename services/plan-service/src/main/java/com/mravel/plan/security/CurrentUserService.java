package com.mravel.plan.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserService {
    public JwtUserPrincipal getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getPrincipal() instanceof JwtUserPrincipal principal) {
            return principal;
        }
        return null;
    }

    public Long getId() {
        var p = getPrincipal();
        return p != null ? p.getId() : null;
    }

    public String getEmail() {
        var p = getPrincipal();
        return p != null ? p.getEmail() : null;
    }

    public String getRole() {
        var p = getPrincipal();
        return p != null ? p.getRole() : null;
    }
}
