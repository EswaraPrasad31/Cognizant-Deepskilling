package com.secure.platform.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegisterRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;

    @Pattern(regexp = "^(ADMIN|CUSTOMER|MANAGER)$", message = "Role must be ADMIN, CUSTOMER, or MANAGER")
    private String role;

    public UserRegisterRequest() {}

    public UserRegisterRequest(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public static UserRegisterRequestBuilder builder() {
        return new UserRegisterRequestBuilder();
    }

    public static class UserRegisterRequestBuilder {
        private String name;
        private String email;
        private String password;
        private String role;

        public UserRegisterRequestBuilder name(String name) { this.name = name; return this; }
        public UserRegisterRequestBuilder email(String email) { this.email = email; return this; }
        public UserRegisterRequestBuilder password(String password) { this.password = password; return this; }
        public UserRegisterRequestBuilder role(String role) { this.role = role; return this; }

        public UserRegisterRequest build() {
            return new UserRegisterRequest(name, email, password, role);
        }
    }
}
