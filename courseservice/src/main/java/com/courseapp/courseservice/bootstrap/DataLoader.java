package com.courseapp.courseservice.bootstrap;

import com.courseapp.courseservice.model.Course;
import com.courseapp.courseservice.services.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final CourseService courseService;

    public DataLoader(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Course course1 = new Course();
        course1.setName("Java 8 for Beginners");
        course1.setTrainerName("Thompson");
        course1.getTermList().add(LocalDate.of(2020, 5, 1));
        course1.getTermList().add(LocalDate.of(2020, 6, 21));

        courseService.saveCourse(course1);

        Course course2 = new Course();
        course2.setName("Docker for Java Developers");
        course2.setTrainerName("Walls");
        course2.getTermList().add(LocalDate.of(2021, 5, 4));

        courseService.saveCourse(course2);
    }
}
