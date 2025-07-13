import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.wecp.library_management_system_jwt.controller.UserController;
import com.wecp.library_management_system_jwt.dto.AuthRequest;
import com.wecp.library_management_system_jwt.dto.AuthResponse;
import com.wecp.library_management_system_jwt.entity.User;
import com.wecp.library_management_system_jwt.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRole("USER");

        when(userService.registerUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    public void testLoginUser() throws Exception {
        AuthRequest authRequest = new AuthRequest();
        authRequest.setUsername("testUser");
        authRequest.setPassword("testPassword");

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken("jwtToken");

        when(userService.loginUser(any(AuthRequest.class))).thenReturn(authResponse);

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(authRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").value("jwtToken"));
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testGetUserDetails() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("testUser");
        user.setRole("USER");

        when(userService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("testUser"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateUserDetails() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("updatedUser");
        user.setPassword("newPassword");

        when(userService.updateUser(anyLong(), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("updatedUser"));
    }
}