package com.restapi.restApi.config.security;

public enum RoleType {
    ROLE_USER("USER"), ROLE_ADMIN("ADMIN");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }
}
