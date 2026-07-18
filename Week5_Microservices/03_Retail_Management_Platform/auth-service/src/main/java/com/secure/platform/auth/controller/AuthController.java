package com.secure.platform.auth.controller;
import com.secure.platform.auth.dto.*;
import com.secure.platform.auth.entity.User;
import com.secure.platform.auth.repository.UserRepository;
import com.secure.platform.auth.util.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest request) {
        log.info("Registering user: {}", request.getEmail());
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("Login request for email: {}", request.getEmail());
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        // Access Token: 15 mins (900,000ms), Refresh Token: 7 days
        String access = jwtUtil.generateToken(user.getEmail(), user.getRole(), 900000);
        String refresh = jwtUtil.generateToken(user.getEmail(), user.getRole(), 604800000);
        return ResponseEntity.ok(AuthResponse.builder()
                .accessToken(access)
                .refreshToken(refresh)
                .email(user.getEmail())
                .role(user.getRole())
                .build());
    }
}
