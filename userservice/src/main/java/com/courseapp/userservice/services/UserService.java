package com.courseapp.userservice.services;

import com.courseapp.userservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User createNewUser(User user);

    User findUserByUsername(String username);

    List<String> findUsers(List<Long> idList);
}
