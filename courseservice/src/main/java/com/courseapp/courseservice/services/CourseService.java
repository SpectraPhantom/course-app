package com.courseapp.courseservice.services;

import com.courseapp.courseservice.model.Course;
import com.courseapp.courseservice.model.Enrollment;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course findCourseById(Long courseId);

    Course saveCourse(Course course);

    List<Course> trainerCourses(String trainerName);

    List<Enrollment> findAllByCourseId(Long courseId);

    Enrollment saveEnrollment(Enrollment enrollment);
}
