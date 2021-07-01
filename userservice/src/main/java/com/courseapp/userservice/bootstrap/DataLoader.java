package com.courseapp.userservice.bootstrap;

import com.courseapp.userservice.model.Role;
import com.courseapp.userservice.model.User;
import com.courseapp.userservice.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UserService userService;

    public DataLoader(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadUsers();
    }

    private void loadUsers() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setRole(Role.USER);

        userService.createNewUser(user);


        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole(Role.ADMIN);

        userService.createNewUser(admin);
    }
}
