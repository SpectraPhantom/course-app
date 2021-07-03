package com.courseapp.userservice.services;


import com.courseapp.userservice.model.User;
import com.courseapp.userservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



class UserServiceImplTest {

    private static final String TEST_USER_USERNAME="test123";


    UserService userService;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        userService= new UserServiceImpl(userRepository,passwordEncoder);
    }

    @Test
    void createNewUser() {
        User testUser=new User();
        testUser.setUsername(TEST_USER_USERNAME);
        testUser.setId(1L);

        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User savedUser=userService.createNewUser(testUser);

        assertEquals(savedUser.getUsername(),testUser.getUsername());
        assertEquals(savedUser.getId(),testUser.getId());
    }

    @Test
    void findUserByUsername() {
        User testUser=new User();
        testUser.setUsername(TEST_USER_USERNAME);

        when(userRepository.findUserByUsername(anyString())).thenReturn(testUser);

        User checkUser=userService.findUserByUsername(TEST_USER_USERNAME);

        assertEquals(TEST_USER_USERNAME,checkUser.getUsername());
    }

    @Test
    void getAllUsers() {

        User testUser1=new User();
        testUser1.setUsername(TEST_USER_USERNAME);
        testUser1.setId(1L);

        User testUser2=new User();
        testUser2.setUsername(TEST_USER_USERNAME+4);
        testUser2.setId(2L);

        List<User> userList=Arrays.asList(testUser1,testUser2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> testList=userService.getAllUsers();

        assertEquals(userList.size(),testList.size());
    }
}