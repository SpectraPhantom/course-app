package com.courseapp.userservice.controller;

import com.courseapp.userservice.model.Role;
import com.courseapp.userservice.model.User;
import com.courseapp.userservice.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static com.courseapp.userservice.controller.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.when;

class UserControllerTest {

    private static final String BASE_API_URL = "/users";
    private static final String TEST_USER_USERNAME = "test123";

    @Mock
    UserService userService;

    @InjectMocks
    UserController userController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getUserByUsername() throws Exception {
        User testUser = new User();
        testUser.setUsername(TEST_USER_USERNAME);

        when(userService.findUserByUsername(anyString())).thenReturn(testUser);

        mockMvc.perform(get(BASE_API_URL + "/test123")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", equalTo(testUser.getUsername())));
    }

    @Test
    void getAllUsers() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");

        List<User> userList = Arrays.asList(user1, user2);

        when(userService.getAllUsers()).thenReturn(userList);

        mockMvc.perform(get(BASE_API_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void createNewUser() throws Exception {
        User testUser = new User();
        testUser.setUsername(TEST_USER_USERNAME);
        testUser.setRole(Role.ADMIN);

        when(userService.createNewUser(any())).thenReturn(testUser);

        mockMvc.perform(post(BASE_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(testUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username", equalTo(testUser.getUsername())))
                .andExpect(jsonPath("$.role", equalTo("ADMIN")));

    }

    @Test
    void getNamesOfUsers() throws Exception {
        User testUser = new User();
        testUser.setId(1L);
        testUser.setUsername(TEST_USER_USERNAME);

        User testUser2 = new User();
        testUser2.setId(2L);
        testUser2.setUsername("test1234");

        List idList = Arrays.asList(testUser.getId().toString(), testUser2.getId().toString());

        when(userService.findUsers(anyList())).thenReturn(idList);

        mockMvc.perform(post(BASE_API_URL + "/names")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(idList)))
                .andExpect(status().isCreated());
    }
}