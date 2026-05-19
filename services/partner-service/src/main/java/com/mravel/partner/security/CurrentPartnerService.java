package com.mravel.partner.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentPartnerService {

    public Long getCurrentPartnerIdOrThrow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new SecurityException("Unauthenticated");
        }
        Object p = auth.getPrincipal();
        if (p instanceof JwtUserPrincipal jp && jp.id() != null) {
            return jp.id();
        }
        throw new SecurityException("Unauthenticated");
    }

    public JwtUserPrincipal getPrincipalOrThrow() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof JwtUserPrincipal jp)) {
            throw new SecurityException("Unauthenticated");
        }
        return jp;
    }
}