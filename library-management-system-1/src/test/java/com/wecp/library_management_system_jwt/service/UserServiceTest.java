import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.repository.UserRepository;
import com.wecp.library_management_system_jwt.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole("USER");
    }

    @Test
    public void testRegisterUser() {
        when(userRepository.save(any(User.class))).thenReturn(user);
        User registeredUser = userService.registerUser(user);
        assertNotNull(registeredUser);
        assertEquals("testuser", registeredUser.getUsername());
    }

    @Test
    public void testFindUserById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User foundUser = userService.findUserById(1L);
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
    }

    @Test
    public void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        user.setUsername("updateduser");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User updatedUser = userService.updateUser(1L, user);
        assertNotNull(updatedUser);
        assertEquals("updateduser", updatedUser.getUsername());
    }

    @Test
    public void testDeleteUser() {
        doNothing().when(userRepository).deleteById(1L);
        userService.deleteUser(1L);
        verify(userRepository, times(1)).deleteById(1L);
    }
}