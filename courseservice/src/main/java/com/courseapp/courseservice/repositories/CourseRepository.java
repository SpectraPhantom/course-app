package com.courseapp.courseservice.repositories;

import com.courseapp.courseservice.model.Course;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CourseRepository extends CrudRepository<Course,Long> {
    Course findByName(String name);
    List<Course> findByTrainerName(String trainerName);
}
