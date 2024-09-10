package com.example.pract2.model;
import org.springframework.security.core.GrantedAuthority;
public enum Roles implements GrantedAuthority {
    USER, ADMIN, MANAGER;
    @Override
    public String getAuthority() {
        return name();
    }
}