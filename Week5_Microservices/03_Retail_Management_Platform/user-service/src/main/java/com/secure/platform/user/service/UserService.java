package com.secure.platform.user.service;
import com.secure.platform.user.dto.*;
import java.util.List;
import java.util.UUID;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(UUID id);
    UserResponse updateUser(UUID id, UserUpdateRequest request);
    void deleteUser(UUID id);
}
