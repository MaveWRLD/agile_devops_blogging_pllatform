package org.example.agile_devops_blogging_platform.services;

import org.example.agile_devops_blogging_platform.entities.User;
import org.example.agile_devops_blogging_platform.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUserWhenUsernameAndEmailAreUnique() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");

        when(userRepository.existsByUsername("john")).thenReturn(false);
        when(userRepository.existsByEmail("john@example.com")).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("john", createdUser.getUsername());
        assertEquals("john@example.com", createdUser.getEmail());

        verify(userRepository).existsByUsername("john");
        verify(userRepository).existsByEmail("john@example.com");
        verify(userRepository).save(user);
    }

    @Test
    void shouldThrowExceptionWhenUsernameExists() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");

        when(userRepository.existsByUsername("john")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });

        assertEquals("Username already exists", exception.getMessage());
        verify(userRepository).existsByUsername("john");
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@example.com");

        when(userRepository.existsByUsername("john")).thenReturn(false);
        when(userRepository.existsByEmail("john@example.com")).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.createUser(user);
        });

        assertEquals("Email already exists", exception.getMessage());
        verify(userRepository).existsByUsername("john");
        verify(userRepository).existsByEmail("john@example.com");
        verify(userRepository, never()).save(any());
    }
}
