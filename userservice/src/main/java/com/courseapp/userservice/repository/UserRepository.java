package com.courseapp.userservice.repository;

import com.courseapp.userservice.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {

    User findUserByUsername(String username);

    @Query(value = "select u.name from user u where u.id in (:pIdList)",nativeQuery = true)
    List<String> findByIdList(@Param("pIdList") List<Long> idList);
}
