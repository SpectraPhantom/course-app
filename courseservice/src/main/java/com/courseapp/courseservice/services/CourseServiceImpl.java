package com.courseapp.courseservice.services;

import com.courseapp.courseservice.model.Course;
import com.courseapp.courseservice.model.Enrollment;
import com.courseapp.courseservice.repositories.CourseRepository;
import com.courseapp.courseservice.repositories.EnrollmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return (List<Course>) courseRepository.findAll();
    }

    @Override
    public Course saveCourse(Course course) {
        Course localCourse = courseRepository.findByName(course.getName());
        if (localCourse != null) {
            log.info("Course with this name: {} already exists!", course.getName());
        } else {
            localCourse = courseRepository.save(course);
        }
        return localCourse;
    }

    @Override
    public List<Course> trainerCourses(String trainerName) {
        return courseRepository.findByTrainerName(trainerName);
    }

    @Override
    public List<Enrollment> findAllByCourseId(Long courseId) {
        return enrollmentRepository.findAllByCourseId(courseId);
    }

    @Override
    public Enrollment saveEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Course findCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
}
