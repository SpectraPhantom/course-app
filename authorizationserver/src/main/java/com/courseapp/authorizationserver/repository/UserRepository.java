package com.courseapp.authorizationserver.repository;

import com.courseapp.authorizationserver.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByUsername(String username);
}
