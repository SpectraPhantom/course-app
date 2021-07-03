package com.courseapp.courseservice.controllers;

import com.courseapp.courseservice.model.Course;
import com.courseapp.courseservice.model.Enrollment;
import com.courseapp.courseservice.services.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.courseapp.courseservice.controllers.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CourseControllerTest {

    private static final String BASE_API_URL = "/courses";

    @Mock
    CourseService courseService;

    @InjectMocks
    CourseController courseController;


    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(courseController).build();
    }

    @Test
    void getAllCourses() throws Exception {
        Course course1 = new Course();
        Course course2 = new Course();

        List<Course> courses = Arrays.asList(course1, course2);

        when(courseService.getAllCourses()).thenReturn(courses);

        mockMvc.perform(get(BASE_API_URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void saveCourse() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setName("Java 16");
        course.setTrainerName("Walls");

        when(courseService.saveCourse(any())).thenReturn(course);

        mockMvc.perform(post(BASE_API_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(course)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo(course.getName())));
    }

    @Test
    void trainerCourses() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setTrainerName("Thompson");

        List<Course> courseList = Collections.singletonList(course);

        when(courseService.trainerCourses(course.getTrainerName())).thenReturn(courseList);

        mockMvc.perform(get(BASE_API_URL + "/search/" + course.getTrainerName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)))
                .andExpect(jsonPath("$[0].trainerName", equalTo("Thompson")));

    }

    @Test
    void saveEnrollment() throws Exception {
        Course course = new Course();
        course.setId(1L);
        course.setTrainerName("Philips");


        Enrollment enrollment = new Enrollment();
        enrollment.setId(1L);
        enrollment.setCourse(course);
        enrollment.setUserId(1L);

        when(courseService.saveEnrollment(any())).thenReturn(enrollment);

        mockMvc.perform(post(BASE_API_URL + "/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(enrollment)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",equalTo(1)))
                .andExpect(jsonPath("$.course.id",equalTo(1)))
                .andExpect(jsonPath("$.course.trainerName",equalTo("Philips")));


    }
}