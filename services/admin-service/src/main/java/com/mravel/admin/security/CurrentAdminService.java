package com.mravel.admin.security;

import com.mravel.common.security.JwtUserPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentAdminService {

    public Long getId() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof JwtUserPrincipal p))
            return null;
        return p.getId();
    }

    public String getRole() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof JwtUserPrincipal p))
            return null;
        return p.getRole();
    }
}
