package com.courseapp.courseservice.repositories;

import com.courseapp.courseservice.model.Enrollment;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnrollmentRepository extends CrudRepository<Enrollment, Long> {

    List<Enrollment> findAllByCourseId(Long courseId);
}
