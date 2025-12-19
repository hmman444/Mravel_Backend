package com.mravel.partner.security;

public record JwtUserPrincipal(Long id, String email, String role, String fullname, String avatar) { }