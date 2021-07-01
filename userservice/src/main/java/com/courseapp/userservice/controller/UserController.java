package com.courseapp.userservice.controller;

import com.courseapp.userservice.model.User;
import com.courseapp.userservice.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable String username) {
        return userService.findUserByUsername(username);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createNewUser(@RequestBody User user) {
        return userService.createNewUser(user);
    }


    @PostMapping("/names")
    @ResponseStatus(HttpStatus.CREATED)
    public List<String> getNamesOfUsers(@RequestBody List<Long> idList) {
        return userService.findUsers(idList);
    }

}
