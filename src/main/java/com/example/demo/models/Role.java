package com.example.demo.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    CLEANER, ADMIN, PROJECT, STOCK, RESEARCHER, USER;


    @Override
    public String getAuthority() {
        return name();
    }
}
