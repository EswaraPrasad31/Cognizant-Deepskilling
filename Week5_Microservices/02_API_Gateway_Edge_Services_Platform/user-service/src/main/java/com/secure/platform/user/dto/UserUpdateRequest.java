package com.secure.platform.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    public UserUpdateRequest() {}

    public UserUpdateRequest(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public static UserUpdateRequestBuilder builder() {
        return new UserUpdateRequestBuilder();
    }

    public static class UserUpdateRequestBuilder {
        private String name;

        public UserUpdateRequestBuilder name(String name) { this.name = name; return this; }

        public UserUpdateRequest build() {
            return new UserUpdateRequest(name);
        }
    }
}
