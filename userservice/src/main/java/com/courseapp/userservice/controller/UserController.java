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

    @GetMapping("/")
    public String login(){
        return "authenticated sucessfuly";
    }

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByUsername(@PathVariable String username){
        return userService.findUserByUsername(username);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @PostMapping()
    public User createNewUser(@RequestBody User user){
        return userService.createNewUser(user);
    }


    @GetMapping("/login")
    public ResponseEntity<?> getUser(Principal principal){
        //Principal principal = request.getUserPrincipal();
        if(principal == null || principal.getName() == null){
            //This means; logout will be successful. login?logout
            return new ResponseEntity<>(HttpStatus.OK);
        }
        //username = principal.getName()
        return ResponseEntity.ok(userService.findUserByUsername(principal.getName()));
    }

    @PostMapping("/names")
    public List<String> getNamesOfUsers(@RequestBody List<Long> idList){
        return userService.findUsers(idList);
    }

}
