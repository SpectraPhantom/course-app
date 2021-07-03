package com.courseapp.courseservice.services;

import com.courseapp.courseservice.model.Course;
import com.courseapp.courseservice.model.Enrollment;
import com.courseapp.courseservice.repositories.CourseRepository;
import com.courseapp.courseservice.repositories.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceImplTest {

    CourseService courseService;

    @Mock
    CourseRepository courseRepository;

    @Mock
    EnrollmentRepository enrollmentRepository;

    private Course getTestCourse() {
        Course course = new Course();
        course.setName("Docker");
        course.setTrainerName("Thompson");
        return course;
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        courseService = new CourseServiceImpl(courseRepository, enrollmentRepository);
    }

    @Test
    void getAllCourses() {
        Course course1 = new Course();
        Course course2 = new Course();

        List<Course> courseList = Arrays.asList(course1, course2);

        when(courseRepository.findAll()).thenReturn(courseList);

        List<Course> testList = courseService.getAllCourses();

        assertEquals(courseList.size(), testList.size());
    }

    @Test
    void saveCourse() {
        Course course = new Course();
        course.setId(1L);
        course.setName("C++");

        when(courseRepository.save(any())).thenReturn(course);

        Course testCourse = courseService.saveCourse(course);

        assertEquals(course.getId(), testCourse.getId());
        assertEquals(course.getName(), testCourse.getName());
    }

    @Test
    void trainerCourses() {

        List<Course> courses = Collections.singletonList(getTestCourse());

        when(courseRepository.findByTrainerName(anyString())).thenReturn(courses);

        List<Course> courseList = courseService.trainerCourses(getTestCourse().getTrainerName());

        assertEquals(courses, courseList);
    }

    @Test
    void saveEnrollment() {
        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setUserId(1L);
        enrollment.setCourse(getTestCourse());

        when(enrollmentRepository.save(any())).thenReturn(enrollment);

        Enrollment testEnrollment=courseService.saveEnrollment(enrollment);

        assertEquals(enrollment.getCourse(),testEnrollment.getCourse());

    }

    @Test
    void findCourseById() {
        when(courseRepository.findById(anyLong())).thenReturn(Optional.of(getTestCourse()));

        Course course=courseService.findCourseById(1L);

        assertNotNull(course);
    }
}