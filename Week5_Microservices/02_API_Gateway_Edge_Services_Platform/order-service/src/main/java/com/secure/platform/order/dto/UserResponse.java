package com.secure.platform.order.dto;

import java.util.UUID;

public class UserResponse {
    private UUID id;
    private String name;
    private String email;
    private String role;

    public UserResponse() {}

    public UserResponse(UUID id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public static UserResponseBuilder builder() {
        return new UserResponseBuilder();
    }

    public static class UserResponseBuilder {
        private UUID id;
        private String name;
        private String email;
        private String role;

        public UserResponseBuilder id(UUID id) { this.id = id; return this; }
        public UserResponseBuilder name(String name) { this.name = name; return this; }
        public UserResponseBuilder email(String email) { this.email = email; return this; }
        public UserResponseBuilder role(String role) { this.role = role; return this; }

        public UserResponse build() {
            return new UserResponse(id, name, email, role);
        }
    }
}
