package com.secure.platform.user.mapper;

import com.secure.platform.user.dto.UserRegisterRequest;
import com.secure.platform.user.dto.UserResponse;
import com.secure.platform.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserRegisterRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .role(request.getRole() != null ? request.getRole() : "CUSTOMER")
                .build();
    }

    public UserResponse toDto(User user) {
        if (user == null) {
            return null;
        }
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
