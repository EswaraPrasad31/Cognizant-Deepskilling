package com.secure.platform.user.service.impl;
import com.secure.platform.user.dto.*;
import com.secure.platform.user.entity.User;
import com.secure.platform.user.mapper.UserMapper;
import com.secure.platform.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inject userMapper manually to bypass Mockito JDK 26 limitations
        try {
            java.lang.reflect.Field field = UserServiceImpl.class.getDeclaredField("userMapper");
            field.setAccessible(true);
            field.set(userService, userMapper);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetUserById() {
        UUID id = UUID.randomUUID();
        User user = User.builder().id(id).email("john@example.com").name("John").role("CUSTOMER").build();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        UserResponse response = userService.getUserById(id);
        assertNotNull(response);
        assertEquals("john@example.com", response.getEmail());
    }
}
