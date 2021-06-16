package com.courseapp.courseservice.controllers;

import com.courseapp.courseservice.clients.UserFeignClient;
import com.courseapp.courseservice.model.Course;
import com.courseapp.courseservice.model.Enrollment;
import com.courseapp.courseservice.services.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final UserFeignClient userFeignClient;

    public CourseController(CourseService courseService, UserFeignClient userFeignClient) {
        this.courseService = courseService;
        this.userFeignClient = userFeignClient;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Course> getAllCourses(){
        return courseService.getAllCourses();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course saveCourse(@RequestBody Course course){
        return courseService.saveCourse(course);
    }

    @GetMapping("/{trainername}")
    @ResponseStatus(HttpStatus.OK)
    public List<Course> trainerCourses(@PathVariable String trainername){
        return courseService.trainerCourses(trainername);
    }

    @PostMapping("/enroll")
    @ResponseStatus(HttpStatus.CREATED)
    public Enrollment saveEnrollment(@RequestBody Enrollment enrollment){
        enrollment.setCourse(courseService.findCourseById(enrollment.getCourse().getId()));
        return courseService.saveEnrollment(enrollment);
    }

    @GetMapping("/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> findStudentsOfCourse(@PathVariable Long courseId){
        List<Enrollment> enrollments=courseService.findAllByCourseId(courseId);
        List<Long> userIdList=enrollments.parallelStream().map(Enrollment::getUserId).collect(Collectors.toList());
        return userFeignClient.getUserNames(userIdList);
    }
}
