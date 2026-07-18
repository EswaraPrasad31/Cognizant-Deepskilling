package com.secure.platform.user.service;

import com.secure.platform.user.dto.ChangePasswordRequest;
import com.secure.platform.user.dto.UserRegisterRequest;
import com.secure.platform.user.dto.UserResponse;
import com.secure.platform.user.dto.UserUpdateRequest;

import java.util.UUID;

public interface UserService {
    UserResponse registerUser(UserRegisterRequest request);
    UserResponse getProfile(String email);
    UserResponse updateProfile(String email, UserUpdateRequest request);
    void changePassword(String email, ChangePasswordRequest request);
    UserResponse getUserById(UUID id);
}
